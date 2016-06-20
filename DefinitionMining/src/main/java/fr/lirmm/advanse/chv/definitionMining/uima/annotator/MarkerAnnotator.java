package fr.lirmm.advanse.chv.definitionMining.uima.annotator;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.util.Level;
import org.apache.uima.util.Logger;

import fr.lirmm.advanse.chv.definitionMining.uima.type.TokenTreetagger;

public class MarkerAnnotator extends JCasAnnotator_ImplBase {

	Logger logger = UIMAFramework.getLogger(MarkerAnnotator.class);
	public static final String LF = System.getProperty("line.separator");
	
	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		logger.log(Level.FINE, "process");
		StringBuilder sb = new StringBuilder();
		sb.append("=== CAS ==="); sb.append(LF);
		sb.append("-- Document Text --"); sb.append(LF);
		sb.append(jcas.getDocumentText()); sb.append(LF);
		sb.append("-- TokenTreetagger --"); sb.append(LF);
/*
		try {
		for (TokenTreetagger a : JCasUtil.select(jcas, TokenTreetagger.class)) {
			sb.append("[" + a.getType().getShortName() + "] ");
			sb.append("(" + a.getBegin() + ", " + a.getEnd() + ") ");
			sb.append("[Word: "+a.getWord()+"]");
			sb.append("[POS: "+a.getPos()+"]");
			sb.append("[Lemma: "+a.getLemma()+"] ");
			sb.append("[CoveredText: "+a.getCoveredText()+"] ");
			//sb.append(a.getCoveredText());
			sb.append(LF);
		}
		sb.append(LF);
		
        getContext().getLogger().log(Level.FINE, sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[Error in MarkerAnnotation] sb = "+sb);
			throw new RuntimeException("Stop");
		}
	*/	
	}

}
