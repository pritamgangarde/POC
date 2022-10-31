package com.xml.service.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.Difference;

import com.xml.model.ConfigXML;
import com.xml.model.NodeAttributeDetails;
import com.xml.model.XMLDifferences;
import com.xml.service.XMLCompareService;
import com.xml.util.XMLNodeType;

@Service
public class XMLCompareServiceImpl implements XMLCompareService {

	@Override
	public List<XMLDifferences> compareSpecificNodeAttribute(String actualXML, String stubXML,
			List<NodeAttributeDetails> listNodeAttribute)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();

		DocumentBuilder db = dbf.newDocumentBuilder();
		Document actulXmlDocumentObj = db.parse(actualXML);
		Document stubXmlDocumentObj = db.parse(stubXML);
		XPathFactory xpf = XPathFactory.newInstance();
		XPath xpath = xpf.newXPath();
		List<XMLDifferences> listXMLDifferences = new ArrayList<>();
		for (NodeAttributeDetails nodeAttributeDetails : listNodeAttribute) {
			switch (nodeAttributeDetails.getType()) {
			case NODE:
				String actualValue = (String) xpath.evaluate(nodeAttributeDetails.getXpath(), actulXmlDocumentObj,
						XPathConstants.STRING);
				String expectedValue = (String) xpath.evaluate(nodeAttributeDetails.getXpath(), stubXmlDocumentObj,
						XPathConstants.STRING);
				if (!actualValue.equals(expectedValue)) {
					listXMLDifferences
							.add(new XMLDifferences(nodeAttributeDetails.getXpath(), actualValue, expectedValue));
				}
				break;

			case ATTRIBUTE:
				String actualIdValue = (String) xpath.evaluate(nodeAttributeDetails.getXpath(), actulXmlDocumentObj,
						XPathConstants.STRING);
				String expectedIdValue = (String) xpath.evaluate(nodeAttributeDetails.getXpath(), stubXmlDocumentObj,
						XPathConstants.STRING);
				if (!actualIdValue.equals(expectedIdValue)) {
					listXMLDifferences
							.add(new XMLDifferences(nodeAttributeDetails.getXpath(), actualIdValue, expectedIdValue));
				}
				break;

			default:
				System.out.println("Invalid Type");
				break;
			}

		}
		return listXMLDifferences;

	}

	@Override
	public List<NodeAttributeDetails> convertConfigToNodeAttributeDetails(List<ConfigXML> listConfigXML) {
		List<NodeAttributeDetails> listNodeAttributeDetails = new ArrayList<>();
		listConfigXML.forEach(configXML -> {
			NodeAttributeDetails nodeAttributeDetails = new NodeAttributeDetails();
			String[] xmlPath = configXML.getPath().split("->");
			getNodeTypeAndXpath(configXML.getType(), nodeAttributeDetails, xmlPath);
			listNodeAttributeDetails.add(nodeAttributeDetails);
		});

		return listNodeAttributeDetails;
	}

	public String removeNode(String xml, List<NodeAttributeDetails> listNodeAttribute)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException,
			TransformerException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();

		DocumentBuilder db = dbf.newDocumentBuilder();
		Document documentXML = db.parse(xml);
		XPathFactory xpf = XPathFactory.newInstance();
		XPath xpath = xpf.newXPath();
		for (NodeAttributeDetails nodeAttributeDetails : listNodeAttribute) {
			switch (nodeAttributeDetails.getType()) {
			case NODE:
				Node nodeActualXpath = (Node) xpath.evaluate(nodeAttributeDetails.getXpath(), documentXML,
						XPathConstants.NODE);
				nodeActualXpath.getParentNode().removeChild(nodeActualXpath);
				break;

			case ATTRIBUTE:
				String path = nodeAttributeDetails.getXpath().replace("/@" + nodeAttributeDetails.getAttr(), "");
				Node actualNode = (Node) xpath.compile(path).evaluate(documentXML, XPathConstants.NODE);
				Element eElement = (Element) actualNode;
				eElement.removeAttribute(nodeAttributeDetails.getAttr());

				break;

			default:
				System.out.println("Invalid Type");
				break;
			}
		}
		return documentString(documentXML);

	}

	public String documentString(Document newDoc) throws TransformerException {
		DOMSource domSource = new DOMSource(newDoc);
		Transformer transformer = TransformerFactory.newDefaultInstance().newTransformer();
		StringWriter sw = new StringWriter();
		StreamResult sr = new StreamResult(sw);
		transformer.transform(domSource, sr);
		return sw.toString();
	}

	private void prepareXpathForNodeAttribute(NodeAttributeDetails nodeAttributeDetails, String[] xmlPath) {
		for (int i = 0; i < xmlPath.length; i++) {
			if (xmlPath[i] != null) {
				if (xmlPath.length - 1 == i) {
					nodeAttributeDetails.setXpath(nodeAttributeDetails.getXpath() + "/@" + xmlPath[i].trim());
				} else
					nodeAttributeDetails.setXpath(nodeAttributeDetails.getXpath() + "/" + xmlPath[i].trim());
			}
		}
		if (xmlPath.length > 0) {
			nodeAttributeDetails.setAttr(xmlPath[xmlPath.length - 1]);
		}
	}

	private void getNodeTypeAndXpath(String type, NodeAttributeDetails nodeAttributeDetails, String[] xmlPath) {
		switch (type) {
		case "N":
			nodeAttributeDetails.setType(XMLNodeType.NODE);
			for (String path : xmlPath) {
				if (path != null)
					nodeAttributeDetails.setXpath(nodeAttributeDetails.getXpath() + "/" + path.trim());
			}
			break;
		case "A":
			nodeAttributeDetails.setType(XMLNodeType.ATTRIBUTE);
			prepareXpathForNodeAttribute(nodeAttributeDetails, xmlPath);
			break;

		default:
			break;
		}
	}

	@Override
	public void compareFullXML(String actualXML, String stubXML) {
		Diff myDiff = DiffBuilder.compare(actualXML).withTest(stubXML).ignoreComments().ignoreWhitespace()
				.ignoreElementContentWhitespace().build();
		Iterator<Difference> iter = myDiff.getDifferences().iterator();
		int size = 0;
		while (iter.hasNext()) {
			Difference difference = iter.next();
			System.err.println(difference.getComparison().getControlDetails().getXPath() + "<-->"
					+ difference.getComparison().getControlDetails().getValue());

			System.err.println(difference.getComparison().getTestDetails().getXPath() + "<-->"
					+ difference.getComparison().getTestDetails().getValue());
			System.out.println("------------------------------------------");
			size++;
		}
		System.err.println("-->" + size);

	}

}
