package fr.lirmm.advanse.chv.definitionMining.uima.pipeline;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReader;
import static org.apache.uima.fit.factory.ExternalResourceFactory.createExternalResourceDescription;

import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.resource.ResourceInitializationException;

import fr.lirmm.advanse.chv.definitionMining.uima.annotator.MarkerAnnotator;
import fr.lirmm.advanse.chv.definitionMining.uima.reader.ChunckCollectionReader;
import fr.lirmm.advanse.chv.definitionMining.uima.reader.CollectionReader;
import fr.lirmm.advanse.chv.definitionMining.uima.writer.CandidateWriter;

public class DefinitionMiningINCa {

	public static void main(String[] args) throws UIMAException, IOException {

		// Run in UIMA pipeline
//		CollectionReader reader = (CollectionReader) createReader(CollectionReader.class,
//				CollectionReader.PARAM_DIRECTORY_NAME,
//				"/home/monordi/stage-lirmm/definitionMining/src/test/resources/fr/INCa");
		ChunckCollectionReader reader = (ChunckCollectionReader) createReader(ChunckCollectionReader.class,
				ChunckCollectionReader.PARAM_DIRECTORY_NAME,
				"/home/monordi/stage-lirmm/definitionMining/src/test/resources/glossaire/INCa");
		AnalysisEngine markerAnnotator =
		 createEngine(MarkerAnnotator.class);
		AnalysisEngine candidateWriter =
				 createEngine(CandidateWriter.class);

		// Build model : training
		SimplePipeline.runPipeline(reader, //markerAnnotator);//
				candidateWriter);
	}

}
