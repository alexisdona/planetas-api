package com.adonascimento.planetasapi.service;

import com.adonascimento.planetasapi.Exceptions.PronosticadorException;
import com.adonascimento.planetasapi.dao.ClimaDAO;
import com.adonascimento.planetasapi.domain.Clima;
import com.adonascimento.planetasapi.domain.SistemaSolar;
import com.adonascimento.planetasapi.domain.TipoClima;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Component
public class Pronosticador {

    private final Logger LOGGER = LoggerFactory.getLogger(Pronosticador.class);
    private static final int DIAS_POR_ANIO = 360;

    @Autowired
    private SistemaSolar sistemaSolar;

    @Autowired
    private ClimaDAO climaDao;

    public int getCantPeriodosSequia(int anios) {
        LOGGER.debug("Obteniendo la cantidad de periodos de sequía en {} años",anios);
        this.validarCompletado(anios);
        int cantidadPeriodosSequia=0;
        int dias = DIAS_POR_ANIO * anios;
            for (int i = 0; i < dias; i++) {
                if (this.sistemaSolar.esDiaSeco(i)) {
                    cantidadPeriodosSequia++;
                }
            }
        return cantidadPeriodosSequia;
    }

    public int getCantPeriodosOptimos(int anios) {
        LOGGER.debug("Obteniendo la cantidad de periodos óptimos en {} años",anios);
        this.validarCompletado(anios);
        int cantidadPeriodosOptimos = 0;
        int dias = DIAS_POR_ANIO * anios;
            for (int i = 0; i < dias; i++) {
                if (this.sistemaSolar.esDiaOptimo(i)) {
                    cantidadPeriodosOptimos++;
                }

            }
        return cantidadPeriodosOptimos;
    }

    public int getCantPeriodosLluviosos(int anios) {
        LOGGER.debug("Obteniendo la cantidad de periodos lluviosos en {} años",anios);
        this.validarCompletado(anios);
        int cantidadPeriodosLluvia=0;
        int dias = DIAS_POR_ANIO*anios;
        for (int i=0;i<dias;i++) {
            if ((!sistemaSolar.esDiaSeco(i)&&!sistemaSolar.esDiaOptimo(i))&&sistemaSolar.esDiaLluvioso(i)){
                cantidadPeriodosLluvia++;
            }

        }
        return cantidadPeriodosLluvia;
    }
    public List<Clima> getDiasLluviosos(int anios) {
        return this.generarClima(anios).stream().filter(dia->dia.getTipoClima().equals(TipoClima.LLUVIA)).sorted().collect(Collectors.toList());
    }
    public void getAndSaveClima(int anios) {
        climaDao.saveAll(this.generarClima(anios));
    }

    public List<Clima> generarClima(int anios) {
        LOGGER.debug("Obteniendo el pronóstico extendido de clima para {} años ",anios);
        this.validarCompletado(anios);
        List<Clima> climaExtendido = new ArrayList<>();
        int dias = DIAS_POR_ANIO*anios;
        for (int i=0;i<dias;i++) {
            Clima clima = new Clima();
            clima.setDia(i+1);
            if(sistemaSolar.esDiaSeco(i)) {
                clima.setTipoClima(TipoClima.SECO);
            }
            else if (sistemaSolar.esDiaLluvioso(i)) {
                clima.setTipoClima(TipoClima.LLUVIA);
                clima.setMilimetrosLlovidos(sistemaSolar.getMilimetrosLluvia(i));
            }
            else if (sistemaSolar.esDiaOptimo(i)) {
                clima.setTipoClima(TipoClima.OPTIMO);
            }
            else {
                clima.setTipoClima(TipoClima.DESCONOCIDO);

            }
            climaExtendido.add(clima);
        }
      return climaExtendido;
    }

    private void validarCompletado(int anios) {
        if (Integer.valueOf(anios)==null) {
            LOGGER.error("Error en la obtención de datos para calcular la cantidad de periodos. ");
            throw new PronosticadorException("Debe completar la cantidad de años a procesar.");
        }
    }

}