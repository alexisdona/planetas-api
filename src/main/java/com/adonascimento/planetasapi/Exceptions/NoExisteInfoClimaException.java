package com.adonascimento.planetasapi.Exceptions;

public class NoExisteInfoClimaException extends RuntimeException {
    private String message;
    public NoExisteInfoClimaException (String message) {
        super(message);}
}
