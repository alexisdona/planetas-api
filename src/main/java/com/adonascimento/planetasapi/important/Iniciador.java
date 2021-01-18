package com.adonascimento.planetasapi.important;

import com.adonascimento.planetasapi.domain.SistemaSolar;
import com.adonascimento.planetasapi.service.Pronosticador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Iniciador {

    private static final Logger LOGGER = LoggerFactory.getLogger(Iniciador.class);
    private static final Integer ANIOS=10;

    @Autowired
    private SistemaSolar sistemaSolar;

    @Autowired
    private Pronosticador pronosticador;

    @PostConstruct
    public void generarPronosticoExtendido() {
        LOGGER.debug("Inicio - Generando clima para los proximos 10 años");
        pronosticador.getAndSaveClima(ANIOS);

    }


}
