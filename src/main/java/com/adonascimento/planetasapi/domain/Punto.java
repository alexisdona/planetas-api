package com.adonascimento.planetasapi.domain;



public class Punto
{
    private double posicionX;
    private double posicionY;

    public Punto(double posicionX, double posicionY) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    public double getPosicionX() {
        return posicionX;
    }


    public double getPosicionY() {
        return posicionY;
    }

}
