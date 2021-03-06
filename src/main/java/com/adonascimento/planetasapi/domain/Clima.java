package com.adonascimento.planetasapi.domain;

import javax.persistence.*;

@Entity
@Table(name="clima")
public class Clima implements Comparable<Clima>{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private int dia;

    @Enumerated(EnumType.STRING)
    @Column(name="tipo_clima")
    private TipoClima tipoClima;

    @Column(name="milimetros_llovidos")
    private double milimetrosLlovidos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }


    public TipoClima getTipoClima() {
        return tipoClima;
    }

    public void setTipoClima(TipoClima tipoClima) {
        this.tipoClima = tipoClima;
    }

    public double getMilimetrosLlovidos() {
        return milimetrosLlovidos;
    }

    public void setMilimetrosLlovidos(double milimetrosLlovidos) {
        this.milimetrosLlovidos = milimetrosLlovidos;
    }

    @Override
    public int compareTo(Clima c) {
        if (milimetrosLlovidos > c.getMilimetrosLlovidos()) {
            return -1;
        }
        if (milimetrosLlovidos < c.getMilimetrosLlovidos()) {
            return 1;
        }
        return 0;
    }
}
