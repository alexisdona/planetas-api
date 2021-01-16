package com.adonascimento.planetasapi.service;

import com.adonascimento.planetasapi.dao.ClimaDAO;
import com.adonascimento.planetasapi.domain.Clima;
import com.adonascimento.planetasapi.domain.SistemaSolar;
import com.adonascimento.planetasapi.domain.TipoClima;
import com.adonascimento.planetasapi.dto.ClimaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Pronosticador {

    private static final int DIAS_POR_ANIO = 360;

    @Autowired
    private SistemaSolar sistemaSolar;

    @Autowired
    private ClimaDAO climaDao;

    public int getCantPeriodosSequia(int anios) {
        int cantidadPeriodosSequia=0;
        int dias = DIAS_POR_ANIO*anios;
        for (int i=0;i<dias;i++) {
            if (this.sistemaSolar.esDiaSeco(i)){
                cantidadPeriodosSequia++;
            }

        }
        return cantidadPeriodosSequia;
    }

    public int getCantPeriodosOptimos(int anios) {
        int cantidadPeriodosOptimos=0;
        int dias = DIAS_POR_ANIO*anios;
        for (int i=0;i<dias;i++) {
            if (this.sistemaSolar.esDiaOptimo(i)){
                cantidadPeriodosOptimos++;
            }

        }
        return cantidadPeriodosOptimos;
    }

    public int getCantPeriodosLluviosos(int anios) {
        int cantidadPeriodosLluvia=0;
        int dias = DIAS_POR_ANIO*anios;

        for (int i=0;i<dias;i++) {
            if ((!sistemaSolar.esDiaSeco(i)&&!sistemaSolar.esDiaOptimo(i))&&sistemaSolar.esDiaLluvioso(i)){
                cantidadPeriodosLluvia++;
            }

        }
        return cantidadPeriodosLluvia;
    }

    public List<Clima> getClima(int anios) {
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
            climaDao.save(clima);
        }
      return climaExtendido;
    }



}
