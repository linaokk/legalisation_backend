package com.pfa.exceptions;

import org.springframework.http.HttpStatus;

public abstract class AbstractException extends Exception {

    public AbstractException(String message) {
        super(message);
    }

    public abstract HttpStatus getHttpCode();

}
