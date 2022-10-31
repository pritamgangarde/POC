package com.xml;

import java.util.ArrayList;
import java.util.List;

import com.xml.model.ConfigXML;
import com.xml.model.NodeAttributeDetails;
import com.xml.model.XMLDifferences;
import com.xml.service.XMLCompareService;
import com.xml.service.impl.XMLCompareServiceImpl;

public class CompareXMLXPaths {

	public static void main(String[] args) throws Exception {
		XMLCompareService xmlCompareService = new XMLCompareServiceImpl();
		List<ConfigXML> listConfigXML = new ArrayList<>();
		listConfigXML.add(new ConfigXML("class->student->id", "A"));
		listConfigXML.add(new ConfigXML("class->student->age", "N"));

		List<NodeAttributeDetails> listNodeAttributeDetails = xmlCompareService
				.convertConfigToNodeAttributeDetails(listConfigXML);

		List<XMLDifferences> listXMLDifferences = xmlCompareService.compareSpecificNodeAttribute(
				"D:\\workspace\\Files\\Student1.xml", "D:\\workspace\\Files\\Student2.xml", listNodeAttributeDetails);

		System.err.println(listXMLDifferences.toString());

		System.err.println("---------------------------------------------");

		listConfigXML.removeAll(listConfigXML);

		listConfigXML.add(new ConfigXML("class->student->age", "N"));
		listConfigXML.add(new ConfigXML("class->student->id", "A"));
		String actualXML = xmlCompareService.removeNode("D:\\workspace\\Files\\Student1.xml", listNodeAttributeDetails);
		String stubXML = xmlCompareService.removeNode("D:\\workspace\\Files\\Student1.xml", listNodeAttributeDetails);


		System.err.println("---------------------------------------------");
		System.err.println("actualXML->" + actualXML);
		System.err.println("stubXML->" + stubXML);
		
		xmlCompareService.compareFullXML(actualXML, stubXML);
	}

}
