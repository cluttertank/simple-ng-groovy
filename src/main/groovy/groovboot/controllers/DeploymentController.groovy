package groovboot.controllers

import groovboot.models.Deployment
import groovy.json.JsonSlurper
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

import java.util.concurrent.Callable

@RestController
@RequestMapping("/apps")
class DeploymentController {

    Log log = LogFactory.getLog(DeploymentController.class)

    @RequestMapping(method=RequestMethod.GET)
    Callable<List> getApps() {
        (Callable) {
            [ "app1", "app2", "app3" ]
        }
    }

    @RequestMapping(value='/{app}/deployments',method=RequestMethod.GET)
    Callable<Object> getAppDeployments(@PathVariable('app') String app) {
        (Callable) {
            def file = "/config/" + app + ".json";
            new JsonSlurper().parseText(this.getClass().getResource( file ).text)
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value='/{app}/deployments',method=RequestMethod.POST)
    Callable<Object> createDeployment(@PathVariable('app') String app,
                                      @RequestBody Deployment deployment) {
        (Callable) { null }
    }

}