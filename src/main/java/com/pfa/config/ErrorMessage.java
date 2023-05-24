package com.pfa.config;


import lombok.Getter;

import java.util.Date;

@Getter
public class ErrorMessage {

    private int statusCode;
    private Date timestamp;
    private String message;
    private String feature;

    public ErrorMessage(int statusCode, Date timestamp, String message, String feature) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.feature = feature;
    }


}