package com.example.barbearia_be.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("application")
public class ApplicationController {

    @GetMapping("/ping")
    public String ping() {
        return "Connection Ok";
    }
}
