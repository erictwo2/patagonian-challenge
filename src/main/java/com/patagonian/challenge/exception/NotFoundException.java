package com.patagonian.challenge.exception;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 4525601593943904653L;

    public NotFoundException(final String message) {
        super(message);
    }

}