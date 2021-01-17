package com.adonascimento.planetasapi.Exceptions;

public class PronosticadorException extends RuntimeException {
    String message;
    public PronosticadorException(String message) {
        super(message);
    }
}
