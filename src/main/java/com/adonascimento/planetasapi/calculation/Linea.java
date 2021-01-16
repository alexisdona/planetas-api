package com.adonascimento.planetasapi.calculation;

import com.adonascimento.planetasapi.domain.Punto;
import org.springframework.stereotype.Service;

@Service
public class Linea {



    public double calcularPendiente(Punto punto1, Punto punto2) {
        double deltaX = punto2.getPosicionX()-punto1.getPosicionX();
        double deltaY = punto2.getPosicionY()-punto1.getPosicionY();
        return deltaY/deltaX;
    }

    public boolean perteneceARecta(Punto punto,double interseccionY,double pendiente) {
        return punto.getPosicionY() == pendiente * punto.getPosicionX() + interseccionY;
    }
}
