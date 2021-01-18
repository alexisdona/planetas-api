package com.adonascimento.planetasapi.service;

import com.adonascimento.planetasapi.Exceptions.PronosticadorException;
import com.adonascimento.planetasapi.domain.Clima;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PronosticadorTest {

    private static final int ANIOS = 10;

    @Autowired
    private Pronosticador pronosticador;

    private List<Clima> climaExtendido = new ArrayList<>();

    @Before
    public void setUp() {
        climaExtendido = pronosticador.generarClima(ANIOS);
    }


    @Test
    public void getCantPeriodosSequia_ok() {
        Integer cantPeriodosSequia = pronosticador.getCantPeriodosSequia(ANIOS);
        Assert.assertEquals(Integer.valueOf(1181),Integer.valueOf(cantPeriodosSequia));
    }




}
