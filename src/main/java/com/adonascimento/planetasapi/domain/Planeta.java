package com.adonascimento.planetasapi.domain;


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

    public double getAnguloBarrido(int dia) {
        return dia*this.getVelocidadAngular();
    }

    public Punto getPuntoActual(int dia) {
        return new Punto(Math.cos(getAnguloBarrido(dia))*getDistanciaAlSol(),Math.sin(getAnguloBarrido(dia))*getDistanciaAlSol());
    }


}
