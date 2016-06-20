package fr.lirmm.advanse.chv.definitionMining.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.XStream;

import fr.lirmm.advanse.chv.definitionMining.crawler.Definition;


public class Glossary2Xml {

	/*public static String generateRecipes(Recipe recipe, String filename) throws IOException{

		List<Recipe> recipes = new ArrayList<Recipe>();

		XStream xstream = XStreamFactory.createXStream();
		String x = xstream.toXML(recipe);
		x = removeLogger(x);
		File yourFile = new File(filename);
		if(!yourFile.exists()) {
		    yourFile.createNewFile();
		} 
		PrintStream ps = new PrintStream(filename);
		ps.println(x);
		ps.close();
		return x;
	}*/
	
	public static void saveGlossary(Map<String, List<Definition>> glossary, String filename) throws IOException{


		XStream xstream = XStreamFactory.createXStream();
		String x = xstream.toXML(glossary);
		x = removeLogger(x);
		File yourFile = new File(filename);
		if(!yourFile.exists()) {
		    yourFile.createNewFile();
		} 
		PrintStream ps = new PrintStream(filename);
		ps.println(x);
		ps.close();
	}

	/**
	 * To make the xml file more readable remove the logger elements
	 * that are'nt needed
	 * @param x
	 * @return
	 */
	private static String removeLogger(String x) {
		StringBuffer buffer = new StringBuffer();
		String[] lines=x.split("\n");
		boolean loggerFound=false;
		for(String l:lines){
			if(l.trim().startsWith("<logger>")){
				loggerFound=true;
			}
			if(!loggerFound){
				buffer.append(l);
				buffer.append("\n");
			}else{
				if(l.trim().startsWith("</logger>")){
					loggerFound=false;
				}
			}
		}

		return buffer.toString();
	}

}
