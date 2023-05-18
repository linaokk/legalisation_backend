package com.pfa.exceptions;

import org.springframework.http.HttpStatus;


public class UsernameAlreadyTakenException extends AbstractException {

    public UsernameAlreadyTakenException() {
        super("FT0001: The username is already taken");
    }

    @Override
    public HttpStatus getHttpCode() {
        return HttpStatus.CONFLICT;
    }
}
