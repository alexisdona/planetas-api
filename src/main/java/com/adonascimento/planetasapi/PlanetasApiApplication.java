package com.adonascimento.planetasapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class PlanetasApiApplication {

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(PlanetasApiApplication.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "8083"));
		app.run(args);
	}


}
