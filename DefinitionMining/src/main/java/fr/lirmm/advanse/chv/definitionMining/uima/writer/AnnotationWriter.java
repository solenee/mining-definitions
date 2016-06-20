package fr.lirmm.advanse.chv.definitionMining.uima.writer;

import java.util.Collection;
import java.util.List;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.util.Level;
import org.apache.uima.util.Logger;

import fr.lirmm.advanse.chv.definitionMining.uima.type.ChunkTreetagger;
import fr.lirmm.advanse.chv.definitionMining.uima.type.TokenTreetagger;


public class AnnotationWriter extends JCasAnnotator_ImplBase {

	Logger logger = UIMAFramework.getLogger(AnnotationWriter.class);
	public static final String LF = System.getProperty("line.separator");

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		logger.log(Level.INFO, "process");
		StringBuilder sb = new StringBuilder();
		sb.append("=== CAS ===");
		sb.append(LF);
		sb.append("-- Document Text --");
		sb.append(LF);
		sb.append(jcas.getDocumentText());
		sb.append(LF);
		sb.append("-- Annotation --");
		sb.append(LF);

		try {
			for (TokenTreetagger a : JCasUtil.select(jcas, TokenTreetagger.class)) {
				sb.append("[" + a.getType().getShortName() + "] ");
				sb.append("(" + a.getBegin() + ", " + a.getEnd() + ") ");
				sb.append("[CoveredText: "+a.getCoveredText()+"] ");
				//sb.append(a.getCoveredText());
				sb.append(LF);
			}
			sb.append(LF);
			for (ChunkTreetagger a : JCasUtil.select(jcas, ChunkTreetagger.class)) {
				sb.append("[" + a.getType().getShortName() + "] ");
				sb.append("(" + a.getBegin() + ", " + a.getEnd() + ") ");
				sb.append("[Text: "+a.getText()+"] ");
				sb.append("[TagType: "+a.getTagType()+"] ");
				sb.append("[CoveredText: "+a.getCoveredText()+"] ");
				//sb.append(a.getCoveredText());
				sb.append(LF);
			}
			sb.append(LF);
			
	        getContext().getLogger().log(Level.INFO, sb.toString());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("[Error in AnnotationWriter] sb = "+sb);
				throw new RuntimeException("Stop");
			}
	}

}
