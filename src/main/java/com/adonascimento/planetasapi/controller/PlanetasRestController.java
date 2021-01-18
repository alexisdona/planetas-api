package com.adonascimento.planetasapi.controller;

import com.adonascimento.planetasapi.Exceptions.NoExisteInfoClimaException;
import com.adonascimento.planetasapi.Exceptions.PronosticadorException;
import com.adonascimento.planetasapi.dao.ClimaDAO;
import com.adonascimento.planetasapi.domain.Clima;
import com.adonascimento.planetasapi.dto.ClimaDTO;
import com.adonascimento.planetasapi.service.Pronosticador;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.util.List;


@RestController
@RequestMapping(value="/clima")
public class PlanetasRestController {

    private final Logger LOGGER = LoggerFactory.getLogger(PlanetasRestController.class);

    @Autowired
    private ClimaDAO climaDao;

    @Autowired
    private Pronosticador pronosticador;

    @GetMapping(value="cantPeriodosSequia")
    @ApiOperation(httpMethod = "GET", nickname = "Obtener la cantidad de periodos de sequia", value="Obtener la cantidad de periodos de sequía para los años informados como parámetro")
    public int getCantPeriodosSequia(int anios) {
        LOGGER.debug("Obteniendo la cantidad de períodos de sequía para los próximos {}",anios);
        try {
            return pronosticador.getCantPeriodosSequia(anios);
        }
        catch(RestClientException e) {
            LOGGER.error(e.getMessage());
            throw new PronosticadorException(e.getMessage());
        }

    }

    @GetMapping(value="cantPeriodosOptimos")
    @ApiOperation(httpMethod = "GET", nickname = "Obtener la cantidad de periodos óptimos", value="Obtener la cantidad de periodos óptimos para los años informados como parámetro")
    public int getCantPeriodosOptimos(int anios) {
        LOGGER.debug("Obteniendo la cantidad de períodos óptimos de presión y temperatura para los próximos {}",anios);
        try {
            return pronosticador.getCantPeriodosOptimos(anios);}
        catch (RestClientException e) {
            LOGGER.error(e.getMessage());
            throw new PronosticadorException(e.getMessage());
        }
    }

    @GetMapping(value="cantPeriodosLluvia")
    @ApiOperation(httpMethod = "GET", nickname = "Obtener la cantidad de periodos de lluvia", value="Obtener la cantidad de periodos de lluvia para los años informados como parámetro")
    public int getCantPeriodosLluvia(int anios) {
        LOGGER.debug("Obteniendo la cantidad de períodos de lluvia para los próximos {}",anios);
        try {
            return pronosticador.getCantPeriodosLluviosos(anios);
        }
        catch (RestClientException e) {
            LOGGER.error(e.getMessage());
            throw new PronosticadorException(e.getMessage());

        }
    }

    @GetMapping(value="/dia/{dia}")
    @ApiOperation(httpMethod = "GET", nickname = "Obtener el clima de un día", value="Obtener el clima de un día en particular")
    @ResponseBody
    public ResponseEntity<ClimaDTO> getClimaByDay(@PathVariable("dia") int dia) {
       LOGGER.debug("Obteniendo el clima para el dia {}",dia);
        try {
        Clima climaDelDia =  climaDao.findByDia(dia);
       if (climaDelDia != null) {
           ClimaDTO climaDTO = new ClimaDTO();
           climaDTO.setDia(climaDelDia.getDia());
           climaDTO.setTipoClima(climaDelDia.getTipoClima().name());
           climaDTO.setMilimetrosLlovidos(climaDelDia.getMilimetrosLlovidos());
           return new ResponseEntity<>(climaDTO, HttpStatus.OK);
       }
       else
       {   String message = "No existe el pronóstico calculado para el día "+String.valueOf(dia);
           LOGGER.error(message);
           throw new NoExisteInfoClimaException(message);
       }
       }
       catch (Exception e) {
           LOGGER.error(e.getMessage());
           throw new RuntimeException(e.getMessage());
       }

    }

    @GetMapping(value="/getDiaLluviosos")
    @ApiOperation(httpMethod = "GET", nickname = "Obtener los dias lluviosos ordenados descendentemente", value="Todos los dias lluviosos ordenados descentendemente por cantidad de milimetros llovidos")

    public ResponseEntity<List<Clima>> getDiasLluvia(Integer anios) {
        List<Clima> diasLluviosos = pronosticador.getDiasLluviosos(anios);
        return new ResponseEntity<>(diasLluviosos,HttpStatus.OK);

    }




}
