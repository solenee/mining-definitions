package fr.lirmm.advanse.chv.definitionMining.uima.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.apache.commons.io.FileUtils;
import org.apache.uima.UIMAFramework;
import org.apache.uima.UimaContext;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.fit.component.JCasCollectionReader_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Level;
import org.apache.uima.util.Logger;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import fr.lirmm.advanse.chv.definitionMining.uima.type.ChunkTreetagger;
import fr.lirmm.advanse.chv.definitionMining.uima.type.FocusEntity;
import fr.lirmm.advanse.chv.definitionMining.uima.type.TokenTreetagger;
import fr.lirmm.advanse.chv.definitionMining.uima.utils.TreetaggerChunkTag;

public class ChunckCollectionReader extends JCasCollectionReader_ImplBase {

	Logger logger = UIMAFramework.getLogger(ChunckCollectionReader.class);

	public static String TREETAGGER_NO_LEMMA_FOUND = "<unknown>";
	
	public static final String DOCUMENT_LANGUAGE = "DOCUMENT_LANGUAGE";

	// @ConfigurationParameter(name = DOCUMENT_LANGUAGE,
	// mandatory = false, defaultValue = "fr")
	private String documentLanguage = "fr";

	public static final String PARAM_DIRECTORY_NAME = "DirectoryName";
	@ConfigurationParameter(name = PARAM_DIRECTORY_NAME, description = "The name of the directory of text files to be read", mandatory = true)
	private File dir;

	/** Documents list */
	private List<File> documents;

	private int i = 0;

	private static List<String> ACRONYM_EXCEPTIONS = Arrays.asList("TEDC", "CHC", "PAC", "PET_Scan", "TDM", "TEP");
	private static List<String> NON_NOUN_ITEMS = Arrays.asList("chimiosensible", "malin", "endogène", "anesthésiant",
			"co-antalgique", "antalgique", "infiltrant", "agoniste", "lymphatique",
			
			"adjuvante", "adjuvant", "aigu", "bénin", "palliatif",
			"néoadjuvante", "néoadjuvant", "cancérogène", "nociceptif", "neuropathique", "sporadique", "immunitaire",
			"standard", "pleural", "paramédical", "axillaire", "stérile", "psychologique", "curatif", "");
	
	@Override
	public void initialize(UimaContext context)
			throws ResourceInitializationException {
		super.initialize(context);
		List<File> files = new ArrayList<File>(FileUtils.listFiles(dir,
				new String[] { "txt" }, false));
		documents = filterInputDocuments(files);
	}

	public boolean hasNext() throws IOException, CollectionException {
		return i < documents.size();
	}

	public Progress[] getProgress() {
		return new Progress[] { new ProgressImpl(i, documents.size(),
				Progress.ENTITIES) };
	}

	@Override
	public void getNext(JCas jcas) throws IOException, CollectionException {
		File f = documents.get(i);
		logger.log(Level.FINE, f.getName());
		//System.out.println(f.getName());
		String s = "";
		int begin = 0;
		int end = 0;
		
		// Add entry in JCas
		FocusEntity term = new FocusEntity(jcas);
		String termName = f.getName()
				.substring(0, f.getName().indexOf(".txt"))
				.replace("®", "")
				.replace("_", " ")
				.trim();
		term.setText(termName);
		term.setDefinitionText(FileUtils.readFileToString(f));
		term.addToIndexes();
		
		
		// Perform POS Tagging
		String inputFile = f.getAbsolutePath();
		
//		String outputFile = "src/main/resources/tmp/treetagger-tmp";
		Runtime r = Runtime.getRuntime();
		Process p = r
				.exec("/home/monordi/SOFTtreetagger/cmd/tagger-chunker-french "
						+ inputFile);
		try {
			p.waitFor();
			BufferedReader b = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String line = "";
			Stack<TreetaggerChunkTag> filo = new Stack<TreetaggerChunkTag>();
			while ((line = b.readLine()) != null) {
				//System.out.println(line);
				if (line.startsWith("</")) {
					TreetaggerChunkTag tagToRemove = filo.pop();
					tagToRemove.setEnd(end);
					treetaggerToChunk(tagToRemove.getTag(), tagToRemove.getText(), tagToRemove.getBegin(), tagToRemove.getEnd(),jcas);
					if (!filo.isEmpty()) {
						TreetaggerChunkTag newHead = filo.pop();
						newHead.setText(newHead.getText()+" "+tagToRemove.getText());
						filo.push(newHead);
					}
					logger.log(Level.FINE, tagToRemove.toString());
				} else if (line.startsWith("<")) {
					TreetaggerChunkTag tag = new TreetaggerChunkTag();
					tag.setTag(line.substring(1, line.length()-1));
					tag.setBegin(begin);
					tag.setEnd(begin);
					filo.push(tag);
				} else {
					
					s +=line+"\n";
					TokenTreetagger token = treetaggerToToken(line, begin, jcas);
					end = begin+line.length();
					begin = end+1;
					
					TreetaggerChunkTag tag = filo.pop();
					tag.setText(tag.getText()+" "+token.getWord());
					filo.push(tag);
				}
			}
			b.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Set JCas text
		jcas.setDocumentText(s);
		jcas.setDocumentLanguage(documentLanguage);

		// Iterate
		i++;
	}

	private TokenTreetagger treetaggerToToken(String text, int begin, JCas jcas) {
		String items[] = text.split("\t");
		TokenTreetagger t = new TokenTreetagger(jcas);
		t.setWord(items[0]);
		t.setPos(items[1]);
		String lemma = items[2];
		if (lemma.equals(TREETAGGER_NO_LEMMA_FOUND)) {
			lemma = items[0];
			//System.out.println("=======================Lemma not found for : "+lemma);
		}
		t.setLemma(lemma);
		t.setBegin(begin);
		t.setEnd(begin+text.length());
		t.addToIndexes();
		return t;
	}
	
	private ChunkTreetagger treetaggerToChunk(String tag, String text, int begin, int end, JCas jcas) {
		ChunkTreetagger t = new ChunkTreetagger(jcas);
		t.setText(text);
		t.setTagType(tag);
		t.setBegin(begin);
		t.setEnd(end);
		t.addToIndexes();
		return t;
	}

	private ArrayList<File> filterInputDocuments(List<File> files) {
		ArrayList<File> docs = new ArrayList<File>();
		int nb_nonNoun = 0;
		int nb_acronym = 0;
		System.out.println("=========");
		System.out.println(files.size()+" definitions submitted");
		System.out.println("=========");
		StringBuffer vocab = new StringBuffer();
		for (File f : files) {
			String input = f.getName().substring(0, f.getName().indexOf(".txt"));
			int upperCase = 0;
			for (int k = 0; k < input.length(); k++) {
			    /**
			     * The methods isUpperCase(char ch) and isLowerCase(char ch) of the Character
			     * class are static so we use the Class.method() format; the charAt(int index)
			     * method of the String class is an instance method, so the instance, which,
			     * in this case, is the variable `input`, needs to be used to call the method.
			     **/
			    // Check for uppercase letters.
			    if (Character.isUpperCase(input.charAt(k))) upperCase++;
			}
			if (upperCase >= 2 && !ACRONYM_EXCEPTIONS.contains(input)) {
				nb_acronym ++;
				StringBuffer sb = new StringBuffer();
				sb.append(input+ ": ");
				try {
					sb.append(FileUtils.readFileToString(f));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					logger.log(Level.FINE, sb.toString());
				}
			} else {
				if (NON_NOUN_ITEMS.contains(input)) {
					nb_nonNoun ++;
				} else {
					docs.add(f);
				vocab.append(
						input.replace("®", "")
						.replace("_", " ")
						.trim() + "\n");
				}
			}
		}
		System.out.println("=========");
		System.out.println(nb_acronym +" acronyms removed (see exceptions)");
		System.out.println(nb_nonNoun +" adjectives removed");
		System.out.println("=========");
		System.out.println("=========");
		System.out.println(docs.size()+" definitions to analyze");
		System.out.println("=========");
		
		// Save vocabulary list
		File fVocab = new File("cleanINCa.txt");
		if(!fVocab.exists() && !fVocab.isDirectory()) { 
			try {
				System.out.println("Writing vocabulary");
				FileUtils.write(fVocab, vocab.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		return docs;
	}
	
	@Deprecated
	private void treetaggerToTokens(String text, JCas jcas) {
		String output[] = text.split("\n");
		for (String line : output) {
			String items[] = line.split("\t");
			TokenTreetagger t = new TokenTreetagger(jcas);
			t.setWord(items[0]);
			t.setPos(items[1]);
			String lemma = items[2];
			if (lemma.equals(TREETAGGER_NO_LEMMA_FOUND)) {
				lemma = items[0];
				System.out.println("=======================Lemma not found for : "+lemma);
			}
			t.setLemma(lemma);
			t.addToIndexes();
		}
	}

}
