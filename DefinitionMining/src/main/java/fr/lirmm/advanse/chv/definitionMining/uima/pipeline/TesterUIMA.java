package fr.lirmm.advanse.chv.definitionMining.uima.pipeline;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReader;

import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.dkpro.core.treetagger.TreeTaggerChunker;
import fr.lirmm.advanse.chv.definitionMining.uima.annotator.MarkerAnnotator;
import fr.lirmm.advanse.chv.definitionMining.uima.reader.BasicCollectionReader;
import fr.lirmm.advanse.chv.definitionMining.uima.reader.ChunckCollectionReader;
import fr.lirmm.advanse.chv.definitionMining.uima.reader.CollectionReader;
import fr.lirmm.advanse.chv.definitionMining.uima.writer.AnnotationWriter;
import fr.lirmm.advanse.chv.definitionMining.uima.writer.CandidateWriter;

public class TesterUIMA {

	public static void main(String[] args) throws UIMAException, IOException {

		// Run in UIMA pipeline
		ChunckCollectionReader reader = (ChunckCollectionReader) createReader(ChunckCollectionReader.class,
				CollectionReader.PARAM_DIRECTORY_NAME,
				"/home/monordi/stage-lirmm/definitionMining/src/test/resources/fr/INCa");
		

//		AnalysisEngine treetaggerChunker =
//				 createEngine(TreeTaggerChunker.class);
		
		AnalysisEngine annotationWriter =
				 createEngine(AnnotationWriter.class);
		
		AnalysisEngine candidateWriter =
				 createEngine(CandidateWriter.class);

		// Build model : training
		SimplePipeline.runPipeline(reader, candidateWriter);
	}
}
