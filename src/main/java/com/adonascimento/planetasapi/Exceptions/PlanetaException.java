package com.adonascimento.planetasapi.Exceptions;

public class PlanetaException extends RuntimeException {
    String message;
    public PlanetaException(String message) {
        super(message);
    }
}
