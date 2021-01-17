package com.adonascimento.planetasapi.Exceptions;

public class CalcularPendienteException extends RuntimeException {
    public CalcularPendienteException() {
        super("Debe informar los dos puntos para calcular la pendiente");
    }
}
