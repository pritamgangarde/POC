package com.xml.service;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.xml.model.ConfigXML;
import com.xml.model.NodeAttributeDetails;
import com.xml.model.XMLDifferences;

public interface XMLCompareService {
	public List<XMLDifferences> compareSpecificNodeAttribute(String actualXML, String stubXML,
			List<NodeAttributeDetails> listNodeAttribute)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException;

	public List<NodeAttributeDetails> convertConfigToNodeAttributeDetails(List<ConfigXML> listConfigXML);

	public String removeNode(String xml, List<NodeAttributeDetails> listNodeAttribute)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException;

	public String documentString(Document newDoc) throws TransformerException;
	
	public void compareFullXML(String actualXML,String stubXML);
}
