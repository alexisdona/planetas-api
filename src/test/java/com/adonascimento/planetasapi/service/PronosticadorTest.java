package com.adonascimento.planetasapi.service;

import com.adonascimento.planetasapi.domain.Clima;
import com.adonascimento.planetasapi.domain.TipoClima;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class PronosticadorTest {

    @Autowired
    private Pronosticador pronosticador;

    private List<Clima> climaExtendido;
    private Integer cantDiasLluvia;
    private Integer cantDiasOptimos;
    private Integer cantDiasSequia;

    private final Integer ANIOS = 10;

    @BeforeEach
    void setUp() {
        climaExtendido = pronosticador.generarClima(ANIOS);
    }



    @Test
    void getCantPeriodosSequia() {
        List<Clima> climaSeco = climaExtendido.stream().filter(dia->dia.getTipoClima().equals(TipoClima.SECO)).collect(Collectors.toList());
       // Assert.assertEquals


    }

    @Test
    void getCantPeriodosOptimos() {
    }

    @Test
    void getCantPeriodosLluviosos() {
    }

    @Test
    void getDiasLluviosos() {
    }

    @Test
    void getAndSaveClima() {
    }

    @Test
    void generarClima() {
    }
}