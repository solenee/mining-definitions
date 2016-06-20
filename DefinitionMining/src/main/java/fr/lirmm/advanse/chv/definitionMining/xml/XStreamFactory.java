package fr.lirmm.advanse.chv.definitionMining.xml;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.thoughtworks.xstream.XStream;

import fr.lirmm.advanse.chv.definitionMining.crawler.Definition;

public class XStreamFactory {
	public static XStream createXStream() {
		//define alias so the xml file can be read easier
		XStream xstream = new XStream();
		
		xstream.alias("Glossary", TreeMap.class);
		xstream.alias("Definition", Definition.class);
		
		xstream.alias("ArrayList", ArrayList.class);
		xstream.alias("List", List.class);
		

		return xstream;
	}
}