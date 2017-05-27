package groovboot.services

import groovboot.services.NLP
import spock.lang.Specification

class NLPSpec extends Specification {
  def 'getting tokens for a text parses and returns an answer with tokenized text'() {
    given: 'we have a text'
    def NLP = new NLP()
    def text = "the quick brown fox jumped over the lazy dog."

    when: 'we parse the text'
    def answers = NLP.getTokens(text)

    then: 'we get a properly tokenized answer'
    answers.get().each {
      assert it.title
      assert it.text
      assert it.text == "[the, quick, brown, fox, jumped, over, the, lazy, dog, .]"
    }
  }
}
