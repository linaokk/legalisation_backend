package com.pfa.controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class PingController {

   @GetMapping ("/ping")
    public String ping(){
        return  "pong";
    }

}
