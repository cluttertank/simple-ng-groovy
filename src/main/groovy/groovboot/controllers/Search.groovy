package groovboot.controllers

import groovboot.models.Data
import groovboot.services.NLP

import java.util.concurrent.Callable

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping('/api')
class Search {

  Log log = LogFactory.getLog(Search.class)
	
  //TODO Autowired not working right
  //@Autowired Data data
  Data data = new Data()

  /**
   * Callable controller method gets processed async in 
   * separate thread then returned to caller.
   */
  @RequestMapping(value='/answers/{question}',method=RequestMethod.GET)
  Callable<List> search(@PathVariable('question') String question) {
    new Callable<List>() {
      List call() {
		  NLP nlp = new NLP()
		  List<String> tokens
		  
		  /* TODO - add the notion of a corpus to Data here so we can start working on integration of a search framework, 
		   * replacing the below with parsing question into question' and the set of {answers} into {answers'} and then feeding them to search
		   */
		  //data.answers(question).get()
		  
		  //give me a list of answers
		  nlp.getTokens(question).get()	
		  	  
		    
		  
      }
    }
  }

}