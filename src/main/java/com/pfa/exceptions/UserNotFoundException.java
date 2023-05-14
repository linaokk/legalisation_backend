package com.pfa.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends AbstractException {

    public UserNotFoundException() {
        super("FT0002: The client doesnt exist");
    }

    @Override
    public HttpStatus getHttpCode() {
        return HttpStatus.NOT_FOUND;
    }
}
