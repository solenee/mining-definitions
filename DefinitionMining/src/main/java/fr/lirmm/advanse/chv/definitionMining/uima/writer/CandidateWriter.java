package fr.lirmm.advanse.chv.definitionMining.uima.writer;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.util.Level;
import org.apache.uima.util.Logger;

import fr.lirmm.advanse.chv.definitionMining.uima.type.ChunkTreetagger;
import fr.lirmm.advanse.chv.definitionMining.uima.type.FocusEntity;
import fr.lirmm.advanse.chv.definitionMining.uima.type.TokenTreetagger;

public class CandidateWriter extends JCasAnnotator_ImplBase {

	Logger logger = UIMAFramework.getLogger(CandidateWriter.class);
	public static final String LF = System.getProperty("line.separator");

	private List<String> LIST_SYNONYMS_AUSSI =  Arrays.asList("aussi", "également",
			"parfois", "souvent", "communément", "couramment", "fréquemment",
			"généralement", "habituellement", "usuellement", "familièrement",
			"abusivement", "injustement", "incorrectement");
	
	private List<String> RELATIVE_PRONOUN = Arrays.asList("qui", "que", "dont");
	
	private boolean isEntry(FocusEntity entry, TokenTreetagger token) {
		return token.getLemma().equals(entry.getText())
				|| token.getWord().toLowerCase().equals(entry.getText());
	}
	
	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		FocusEntity entry = JCasUtil.selectSingle(jcas, FocusEntity.class);
		logger.log(Level.FINE, "process "+entry.getText());
		StringBuilder sb = new StringBuilder();
		// sb.append("=== CAS ===");
		// sb.append(LF);
		sb.append("-- Definition --");
		sb.append(LF);
		sb.append(entry.getText()+" : @def{"+entry.getDefinitionText()+"}");
		sb.append(LF);
		sb.append("-- Candidates --");
		sb.append(LF);
		
		StringBuilder sbOut = new StringBuilder();

		// TOKEN
		try {
			// List<TokenTreetagger> tokens =
			// (List<TokenTreetaggerjCas>)JCasUtil.select(jcas,
			// TokenTreetagger.class);

			// First candidate
			TokenTreetagger firstToken = JCasUtil.selectByIndex(jcas,
					TokenTreetagger.class, 0);
			if (firstToken != null && firstToken.getPos().equals("NOM")
					&& !isEntry(entry, firstToken)) {
				sb.append("Hypernym candidate [first][Token] : "
						+ firstToken.getLemma() + " (" + firstToken.getWord() + ")");
				sb.append(LF);
				sbOut.append(entry.getText()
						+" <is-a> "
						+firstToken.getLemma() + " (" + firstToken.getWord() + ")"
						+"[first][Token]"
						+"\n");
			}

			// 'On parle aussi|également|souvent de'
			for (TokenTreetagger token : JCasUtil.select(jcas,
					TokenTreetagger.class)) {
				// Discard non-Noun tokens
				if (token.getPos().equals("NOM") 
						&& !isEntry(entry, token) ) {					
					List<TokenTreetagger> opad = JCasUtil.selectPreceding(
							TokenTreetagger.class, token, 4);
					if (opad != null && opad.size() == 4
							&& opad.get(0).getLemma().equals("on")
							&& opad.get(1).getLemma().equals("parler")
							&& LIST_SYNONYMS_AUSSI.contains(opad.get(2).getLemma()) 
							&& opad.get(3).getLemma().equals("de")) {
						sb.append("Synonym candidate [OnParleAussiDe][Token] : "
								+ token.getLemma() + " (" + token.getWord() + ")");
						// while ()
						sb.append(LF);
						sbOut.append(entry.getText()
								+" <is> "
								+token.getLemma() + " (" + token.getWord() + ")"
								+"[OnParleAussiDe]"+"["+opad.get(2).getLemma()+"]"+"[Token]"
								+"\n");					
					}
				}
			}

			// 'appelé aussi' 
			for (TokenTreetagger token : JCasUtil.select(jcas,
					TokenTreetagger.class)) {
				// Discard non-Noun tokens
				if (token.getPos().equals("NOM") && !isEntry(entry, token)) {
					List<TokenTreetagger> aa = JCasUtil.selectPreceding(
							TokenTreetagger.class, token, 2);
					if (aa != null && aa.size() == 2
							&& aa.get(0).getLemma().equals("appeler")
							&& LIST_SYNONYMS_AUSSI.contains(aa.get(1).getLemma())
						) {
						sb.append("Synonym candidate [AppeleAussi][Token] : "
								+ token.getLemma() + " (" + token.getWord() + ")");
						// while ()
						sb.append(LF);
						sbOut.append(entry.getText()
								+" <is> "
								+token.getLemma() + " (" + token.getWord() + ")"
								+"[AppeleAussi]"+"["+aa.get(1).getLemma()+"]"+"[Token]"
								+"\n");
					}
				}
			}

			// 'aussi appelé' 
			for (TokenTreetagger token : JCasUtil.select(jcas,
					TokenTreetagger.class)) {
				// Discard non-Noun tokens
				if (token.getPos().equals("NOM") && !isEntry(entry, token)) {
					List<TokenTreetagger> opad = JCasUtil.selectPreceding(
							TokenTreetagger.class, token, 2);
					if (opad != null && opad.size() == 2
							&& LIST_SYNONYMS_AUSSI.contains(opad.get(0).getLemma())
							&& opad.get(1).getLemma().equals("appeler")
						) {
						sb.append("Synonym candidate [AussiAppele][Token] : "
								+ token.getLemma() + " (" + token.getWord() + ")");
						// while ()
						sb.append(LF);
						sbOut.append(entry.getText()
								+" <is> "
								+token.getLemma() + " (" + token.getWord() + ")"
								+"[AussiAppele]"+"["+opad.get(0).getLemma()+"]"+"[Token]"
								+"\n");
					}
				}
			}
			
			// '^<hypernym> qui|que|dont' Before relative pronoun
			for (TokenTreetagger token : JCasUtil.select(jcas,
					TokenTreetagger.class)) {
				// Discard non-Noun tokens
				if (RELATIVE_PRONOUN.contains(token.getLemma())) {
					List<TokenTreetagger> opad = JCasUtil.selectPreceding(
							TokenTreetagger.class, token, 1);
					if (opad != null && opad.size() == 1
							&& opad.get(0).getPos().equals("NOM")
							&& !isEntry(entry, opad.get(0))) {
						sb.append("Hypernym candidate [RELATIVE_PRONOUN]"+"["+token.getLemma()+"]"+"[Token] : "
								+ opad.get(0).getLemma() + " (" + opad.get(0).getWord() + ")");
						// while ()
						sb.append(LF);
						sbOut.append(entry.getText()
								+" <is-a> "
								+opad.get(0).getLemma() + " (" + opad.get(0).getWord() + ")"
								+"[RELATIVE_PRONOUN]"+"["+token.getLemma()+"]"+"[Token]"
								+"\n");
					}
				}
			}
			/*
			 * { sb.append("[" + a.getType().getShortName() + "] ");
			 * sb.append("(" + a.getBegin() + ", " + a.getEnd() + ") ");
			 * sb.append("[Word: "+a.getWord()+"]");
			 * sb.append("[POS: "+a.getPos()+"]");
			 * sb.append("[Lemma: "+a.getLemma()+"] ");
			 * sb.append("[CoveredText: "+a.getCoveredText()+"] ");
			 * //sb.append(a.getCoveredText()); sb.append(LF); }
			 */

			getContext().getLogger().log(Level.FINE, sb.toString());
			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[Error in CandidateWriter] sb = " + sb);
			throw new RuntimeException("Stop");
		}

		/*
		// CHUNK
		sb = new StringBuilder();
		try {
			// List<TokenTreetagger> tokens =
			// (List<TokenTreetagger>)JCasUtil.select(jcas,
			// TokenTreetagger.class);

			// First candidate
			ChunkTreetagger firstChunk = JCasUtil.selectByIndex(jcas,
					ChunkTreetagger.class, 1); // le premier(0), c'est un s
			if (firstChunk != null && firstChunk.getTagType().equals("NP")) {
				sb.append("Hypernym candidate [first][Chunk] : "
						+ firstChunk.getText());
				sb.append(LF);
			} else {
				if (firstChunk != null)
					logger.log(Level.FINE, 
							"firstChunk.getTagType().equals "
							+ firstChunk.getTagType() + "("
							+ firstChunk.getText() + ")");
			}

			// 'On parle aussi de' Before relative pronoun
			for (ChunkTreetagger chunk : JCasUtil.select(jcas,
					ChunkTreetagger.class)) {
				// Discard non-Noun tokens
				if (!chunk.getTagType().equals("NP")) {
					continue;
				}
				List<TokenTreetagger> opad = JCasUtil.selectPreceding(
						TokenTreetagger.class, chunk, 4);
				if (opad != null && opad.size() == 4
						&& opad.get(0).getLemma().equals("on")
						&& opad.get(1).getLemma().equals("parler")
						&& opad.get(2).getLemma().equals("aussi")
						&& opad.get(3).getLemma().equals("de")) {
					sb.append("Synonym candidate [OnParleAussiDe][Chunk] : "
							+ chunk.getText());
					// while ()
					sb.append(LF);
				}
			}

			// 'appelé aussi'
			for (ChunkTreetagger chunk : JCasUtil.select(jcas,
					ChunkTreetagger.class)) {
				// Discard non-Noun tokens
				if (!chunk.getTagType().equals("NP")) {
					continue;
				}
				List<TokenTreetagger> opad = JCasUtil.selectPreceding(
						TokenTreetagger.class, chunk, 2);
				if (opad != null && opad.size() == 2
						&& opad.get(0).getLemma().equals("appeler")
						&& opad.get(1).getLemma().equals("aussi")) {
					sb.append("Synonym candidate [AppeleAussi][Chunk] : "
							+ chunk.getText());
					// while ()
					sb.append(LF);
				}
			}
			
			// '^<hypernym> qui|que|dont' Before relative pronoun
			for (TokenTreetagger token : JCasUtil.select(jcas,
					TokenTreetagger.class)) {
				// Discard non-Noun tokens
				if (token.getLemma().equals("qui") 
						|| token.getLemma().equals("que")
						|| token.getLemma().equals("dont")) {
					List<ChunkTreetagger> opad = JCasUtil.selectPreceding(
							ChunkTreetagger.class, token, 1);
					if (opad != null && opad.size() == 1
							&& opad.get(0).getTagType().equals("NP") && opad.get(0).getBegin() == 0) {
						sb.append("Hypernym candidate [qui,que,dont][Chunk] : "
								+ opad.get(0).getText());
						// while ()
						sb.append(LF);
					}
				}
			}
			getContext().getLogger().log(Level.FINE, sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[Error in CandidateWriter] sb = " + sb);
			throw new RuntimeException("Stop");
		}
		*/
		
		getContext().getLogger().log(Level.FINE, "==================="+LF);
		
		System.out.println(sbOut.toString()+"==================="+LF);
		//FileUtils.write("output.res", data);
	}
 
}
