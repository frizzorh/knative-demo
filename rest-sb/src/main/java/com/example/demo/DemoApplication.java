package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoApplication {

	@Value("${HOSTNAME}")
	String hostname;
  
	public class Greet {

		private String greetings;

		public Greet(String greetings) {
			this.greetings = greetings;
		}

		public String getGreetings() {
			return greetings;
		}
	}

	@RestController
	public class GreetController {

		@GetMapping("/")
		public Greet greet(@RequestParam(value = "name", defaultValue = "Spring Boot") String name) {
			return new Greet("Hello " + name + " from " + hostname + "!");
		}

	}

	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
