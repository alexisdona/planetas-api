package com.adonascimento.planetasapi;

import com.adonascimento.planetasapi.service.Pronosticador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Collections;

@SpringBootApplication
public class PlanetasApiApplication {

	@Autowired
	private static Pronosticador pronosticador;

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(PlanetasApiApplication.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "8083"));
		app.run(args);
		//pronosticador.getClima(10);

	}



}
