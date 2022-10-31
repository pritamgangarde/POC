package com.xml.model;

import com.xml.util.XMLNodeType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NodeAttributeDetails {
	private String xpath="";
	private XMLNodeType type;
	private String attr="";
}
