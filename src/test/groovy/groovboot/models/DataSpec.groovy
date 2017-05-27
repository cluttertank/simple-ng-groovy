package groovboot.models

import groovboot.models.Data

import spock.lang.Specification

class DataSpec extends Specification {
  
  def 'asking a question returns an answer'() {
    given: 'we have a question'
    def data = new Data()
    def question = 'what color is sky'

    when: 'we ask the question'
    def answers = data.answers('what color is sky')

    then: 'we get at least one answer'
    answers.get().each {
      assert it.title
      assert it.text 
    }
  }

}