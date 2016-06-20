package fr.lirmm.advanse.chv.definitionMining.uima.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.uima.UIMAFramework;
import org.apache.uima.UimaContext;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.fit.component.JCasCollectionReader_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Logger;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import fr.lirmm.advanse.chv.definitionMining.uima.type.TokenTreetagger;

public class CollectionReader extends JCasCollectionReader_ImplBase {

	Logger logger = UIMAFramework.getLogger(CollectionReader.class);

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

	@Override
	public void initialize(UimaContext context)
			throws ResourceInitializationException {
		super.initialize(context);
		documents = new ArrayList<File>(FileUtils.listFiles(dir,
				new String[] { "txt" }, false));
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
		String s = "";
		int begin = 0;
		int end = 0;
		
		String inputFile = f.getAbsolutePath();
		String outputFile = "src/main/resources/tmp/treetagger-tmp";
		Runtime r = Runtime.getRuntime();
		Process p = r
				.exec("/home/monordi/SOFTtreetagger/cmd/tree-tagger-french "
						+ inputFile);// + " > " + outputFile);
		try {
			p.waitFor();
			BufferedReader b = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String line = "";
			while ((line = b.readLine()) != null) {
				// System.out.println(line);
				s +=line+"\n";
				treetaggerToToken(line, begin, jcas);
				end = begin+line.length();
				begin = end+1;
			}
			b.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		{
			Runtime r2 = Runtime.getRuntime();
			Process p2 = r2
					.exec("/home/monordi/SOFTtreetagger/cmd/tagger-chunker-french "
							+ inputFile);// + " > " + outputFile);
			try {
				p2.waitFor();
				BufferedReader b2 = new BufferedReader(new InputStreamReader(
						p2.getInputStream()));
				String line2 = "";
				while ((line2 = b2.readLine()) != null) {
					System.out.println(line2);
				}
				b2.close();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Set JCas text
		jcas.setDocumentText(s);
		jcas.setDocumentLanguage(documentLanguage);

		// Initialize Token annotations
		// treetaggerToToken(s, jcas);

		// Iterate
		i++;
	}

	private TokenTreetagger treetaggerToToken(String text, int begin, JCas jcas) {
		String items[] = text.split("\t");
		TokenTreetagger t = new TokenTreetagger(jcas);
		t.setWord(items[0]);
		t.setPos(items[1]);
		t.setLemma(items[2]);
		t.setBegin(begin);
		t.setEnd(begin+text.length());
		t.addToIndexes();
		return t;
	}

	private void treetaggerToTokens(String text, JCas jcas) {
		String output[] = text.split("\n");
		for (String line : output) {
			String items[] = line.split("\t");
			TokenTreetagger t = new TokenTreetagger(jcas);
			t.setWord(items[0]);
			t.setPos(items[1]);
			t.setLemma(items[2]);
			t.addToIndexes();
		}
	}

}
