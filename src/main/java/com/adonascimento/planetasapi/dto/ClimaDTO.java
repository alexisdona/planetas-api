package com.adonascimento.planetasapi.dto;

import java.io.Serializable;

public class ClimaDTO implements Serializable {

    private int dia;
    private String tipoClima;
    private double milimetrosLlovidos;

    public ClimaDTO() {}

    public ClimaDTO(int dia, String tipoClima, double milimetrosLlovidos) {
        this.dia= dia;
        this.tipoClima = tipoClima;
        this.milimetrosLlovidos = milimetrosLlovidos;
    }

    public long getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getTipoClima() {
        return tipoClima;
    }

    public void setTipoClima(String tipoClima) {
        this.tipoClima = tipoClima;
    }

    public double getMilimetrosLlovidos() {
        return milimetrosLlovidos;
    }

    public void setMilimetrosLlovidos(double milimetrosLlovidos) {
        this.milimetrosLlovidos = milimetrosLlovidos;
    }
}
