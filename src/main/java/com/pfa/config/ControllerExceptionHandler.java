package com.pfa.config;


import com.pfa.exceptions.ReactiveException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ResponseBody
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ReactiveException.class)
    public ResponseEntity<?> reactiveException(ReactiveException ex, WebRequest request) {
        HttpStatus httpStatus = ex.getFeature().getHttpStatus();
        ErrorMessage errorMessage = new ErrorMessage(
                httpStatus.value(),
                new Date(),
                ex.getMessage(),
                ex.getFeature().name()
        );

        return new ResponseEntity<>(errorMessage, httpStatus);
    }

}
