package com.auth.app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Value("${app.test}")
    private String test;

    @Value("${security.jwt.secret-key}")
    private String key;

    @GetMapping("/")
    public String getEnvVariables() {
        System.out.println(test);
        return test + " " + key;
    }
}
