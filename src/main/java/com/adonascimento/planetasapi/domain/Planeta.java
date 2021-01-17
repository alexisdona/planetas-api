package com.adonascimento.planetasapi.domain;


import com.adonascimento.planetasapi.Exceptions.PlanetaException;

public class Planeta {


    private String nombre;
    private double velocidadAngular;
    private double distanciaAlSol;
    private Punto punto;

    public Planeta(String nombre, double velocidadAngular, double distanciaAlSol) {
        this.nombre = nombre;
        this.velocidadAngular = velocidadAngular;
        this.distanciaAlSol = distanciaAlSol;
    }

    public double getDistanciaAlSol() {
        return distanciaAlSol;
    }

    public double getVelocidadAngular() {
        return Math.toRadians(velocidadAngular);
    }

    public double getAnguloBarrido(Integer dia) {
        this.validateParams(dia);
        return dia*this.getVelocidadAngular();
    }

    public Punto getPuntoActual(int dia) {
        this.validateParams(dia);
        return new Punto(Math.cos(getAnguloBarrido(dia))*getDistanciaAlSol(),Math.sin(getAnguloBarrido(dia))*getDistanciaAlSol());
    }

    private void validateParams(Integer dia) {
        if (dia == null) {
            throw new PlanetaException("Debe informar el dia para calcular el valor.");
        }
    }


}
