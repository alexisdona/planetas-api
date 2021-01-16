package com.adonascimento.planetasapi.controller;

import com.adonascimento.planetasapi.Exceptions.NoExisteInfoClimaException;
import com.adonascimento.planetasapi.dao.ClimaDAO;
import com.adonascimento.planetasapi.domain.Clima;
import com.adonascimento.planetasapi.dto.ClimaDTO;
import com.adonascimento.planetasapi.service.Pronosticador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value="/clima")
public class PlanetasRestController {

    @Autowired
    private ClimaDAO climaDao;

    @Autowired
    private Pronosticador pronosticador;

    @GetMapping(value="cantPeriodosSequia")
    public int getCantPeriodosSequia(int anios) {

        return pronosticador.getCantPeriodosSequia(anios);
    }

    @GetMapping(value="cantPeriodosOptimos")
    public int getCantPeriodosOptimos(int anios) {
        return pronosticador.getCantPeriodosOptimos(anios);
    }

    @GetMapping(value="cantPeriodosLluvia")
    public int getCantPeriodosLluvia(int anios) {
        return pronosticador.getCantPeriodosLluviosos(anios);
    }

    @GetMapping(value="generarPronosticoExtendido")
    public ResponseEntity<List<Clima>> generarPronosticoExtendido(int anios) {
         return ResponseEntity.ok(pronosticador.getClima(anios));
    }

    @GetMapping(value="/{dia}")
    @ResponseBody
    public ResponseEntity<ClimaDTO> getClimaByDay(@PathVariable("dia") int dia) {
       Clima climaDelDia =  climaDao.findByDia(dia);
       if (climaDelDia != null) {
           ClimaDTO climaDTO = new ClimaDTO();
           climaDTO.setDia(climaDelDia.getDia());
           climaDTO.setTipoClima(climaDelDia.getTipoClima().name());
           climaDTO.setMilimetrosLlovidos(climaDelDia.getMilimetrosLlovidos());
           return new ResponseEntity<>(climaDTO, HttpStatus.OK);
       }
       else
       {   throw new NoExisteInfoClimaException("No existe el pronostico calculado para el día "+String.valueOf(dia));
       }

    }


}
