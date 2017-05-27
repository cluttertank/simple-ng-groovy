package groovboot.services

import java.util.concurrent.Future

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Service

import edu.stanford.nlp.ling.CoreLabel
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation
import edu.stanford.nlp.pipeline.Annotation
import edu.stanford.nlp.pipeline.StanfordCoreNLP
import edu.stanford.nlp.util.CoreMap
import groovboot.models.Answer
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.AsyncResult

//wrap the stanford nlp code with simple AP
@Service
class NLP {

	Log log = LogFactory.getLog(NLP.class)

	private Annotation document

	/**
	 * sets up the pipeline and parses internal document.
	 * @param question
	 */
	private void parse(String text){
		//setup pipeline, directly from StanfordNLP documentation
		Properties props = new Properties()
		props.put "annotators", "tokenize, ssplit"
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props)

		//wrap question in an Annotation instance (required by CoreNLP), and run it through the pipeline
		document = new Annotation(text)
		pipeline.annotate document
	}

	/**
	 * return all tokens of a parsed text
	 * @return all tokens
	 */
	Future<List> getTokens(String text) {

		parse(text)

		List<CoreMap> sentences = document?.get(SentencesAnnotation)
		def result = []

		log.info "pre parsed entry: $document"

		// nested loop through to add all tokens from all sentences of the input to the new list
		sentences.each {  CoreMap sentence ->
			sentence.get(TokensAnnotation).each {  CoreLabel token ->
				result.push(token.get(TextAnnotation))
			}
		}

		log.info "resulting token list: ${result.toListString()}"

		//put the result of the tokenization into an answer for now
		Answer answer = new Answer(title: "tokens", text: result.toListString())
		//return list of one answer, also just for now as we expect to expand this class later anyway this will be rewritten
		new AsyncResult<>([answer])

	}

}
