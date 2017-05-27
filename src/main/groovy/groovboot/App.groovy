package groovboot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync

/**
 * Spring Boot will detect a Controller class on the path and
 * automatically serve up content to client in the /public directory.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableAsync
class App {

  static main(String[] args) {
    SpringApplication.run(App.class, args)
  }

}