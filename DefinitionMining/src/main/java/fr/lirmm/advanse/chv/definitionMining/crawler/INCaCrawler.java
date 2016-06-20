package fr.lirmm.advanse.chv.definitionMining.crawler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import fr.lirmm.advanse.chv.definitionMining.xml.Glossary2Xml;

public class INCaCrawler {

	private static WebClient webClient;
	
	private static SortedMap<String, List<Definition>> glossary = Collections.synchronizedSortedMap(new TreeMap<String, List<Definition>>());

	public static void main(String[] args) throws SQLException, IOException,
			InterruptedException, ClassNotFoundException {

		char firstchar = 'C';
		int asciA = (int) firstchar;
		
		 
		//for (int i = asciA; i < asciA + 26; i++) {
			char c = 'K'; //(char) i;
			Document document = GetHTML(
					"http://www.e-cancer.fr/Dictionnaire/"+c, 0);
			PrintWriter outfile = new PrintWriter(new BufferedWriter(
					new FileWriter("INCa_"+c)));
			// + c, 0);
			Elements panels = document.select("div.hidden-sm");
			for (Element eltp : panels) {
				// Get dictionnary item
				Elements items = eltp.select("li.glossaire-item");
				for (Element item : items) {
					// Elements Groupe =
					// forum.getElementsByTag("glossaire-item");
					// System.out.println("le forum est: "+Groupe);
					// for(Element groupe : Groupe){
					String term = item.text();
					System.out.println("glossaire-item : " + item.tag() + "\t"
							+ term);
					outfile.write(term+"\t%\t");
					
					// Get item's definition
					Element eltA = item.select("a").first();
					String url = eltA.absUrl("href");
					System.out.println(url);
					System.out.println("url : "+url);
					Document definitionDoc = GetHTML(url, 0);
					Elements definition = definitionDoc.select("div.ezxmltext_content");
					for (Element def : definition) {
						String text = def.text();
						System.out.println("text : "+text);
						addDefinition(term, text, IGlossary.INCA);
						outfile.write(text+"\n%\n");
					}
				}
			}
			outfile.close();
			Glossary2Xml.saveGlossary(glossary, "testK.xml");
		//}
		System.out.println("Fin d'éxécution");
	}

	public static Document GetHTML(String link, int nbt)
			throws InterruptedException, IOException {
		try {
			webClient = new WebClient();
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			final HtmlPage page = webClient.getPage(link);
			// System.out.println(page.getTitleText().equals("HtmlUnit - Welcome to HtmlUnit"));//Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit",
			// page.getTitleText());

			final String pageAsXml = page.asXml();
			//System.out.println(pageAsXml.contains("<li class=\"glossaire-item\"")); // Assert.assertTrue(pageAsXml.contains("<body class=\"composite\">"));
			//System.out.println(pageAsXml.contains("<html")); // Assert.assertTrue(pageAsXml.contains("<body class=\"composite\">"));
			//final String pageAsText = page.asText();
			// System.out.println(">\n"+pageAsXml);

			Document document = Jsoup.parse(pageAsXml);
			document.setBaseUri("http://www.e-cancer.fr/");
			return document;
		} catch (IOException ex) {
			nbt++;
			System.out.println("Exception dans GET numéro: " + nbt + " and "
					+ link);
			Thread.sleep(1000 * (int) Math.pow(2, nbt));
			return GetHTML(link, nbt);
		}
	}
	
	public static void addDefinition(String term, String text, String source) {
		Definition def = new Definition();
		def.setText(text);
		def.setSource(source);
		
		List<Definition> ldef = glossary.get(term);
		if (ldef.isEmpty()) {
			ldef = new ArrayList<Definition>();
		}
		ldef.add(def);
		glossary.put(term, ldef);
	}

}
