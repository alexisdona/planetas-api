package com.adonascimento.planetasapi.Exceptions;

public class SistemaSolarException extends RuntimeException {
    String message;
    public SistemaSolarException(String message) {
        super(message);
    }
}
