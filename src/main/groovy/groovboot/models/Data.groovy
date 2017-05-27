package groovboot.models

import groovboot.models.Answer

import java.util.concurrent.Future

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.AsyncResult
import org.springframework.stereotype.Repository

@Repository
public class Data {

  Log log = LogFactory.getLog(Data.class)

  @Async
  Future<List> answers(String question) {
    log.info(question)
  	String title = 'Field Offices handle scheduled interviews on non-asylum related applications.'
    String text = "Field Offices also provide limited information and customer services that supplement " +
      "those we provide through our website and by phone. We recommend you consider the " +
      "convenient service options we offer by web and by phone before you take the time to " +
      "go to a Field Office for service. If you do need to go to one of our Field Offices, to " +
      "serve you better most of the offices provide in-person services by appointment. To minimize " +
      "your wait time, we recommend you make an appointment before you go. You'll find more " +
      "information about appointments below and in our individual office profiles."
  	
    List answers = [new Answer(title: title,text: text)]
  	new AsyncResult<>(answers)
  }
  

}