package com.example.projeto.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class Teste {

    @GetMapping("/")
    public String olaMundo() {
        return "Olá, mundo!";
    }

    @GetMapping("/teste")
    public String olaMundo2() {
        return "Olá, mundo2!";
    }
    
    @PostMapping("/")
    public String olaMundoPOST() {
        return "Olá, mundo via POST!";
    }

    @PutMapping("/")
    public String olaMundoPUT() {
        return "Olá, mundo via PUT!";
    }

    @DeleteMapping("/")
    public String olaMundoDELETE() {
        return "Olá, mundo via DELETE!";
    }

    @GetMapping("/teste/{pParametro}")
    public String olaMundoParametro(@PathVariable Integer pParametro) {
        return "Você passou "+pParametro+" como parâmetro!";
    }
    
    @GetMapping(value = "/geraJSON", produces = "application/json")
    public String geraJSON() {
        return "{ \"nome\" : \"Marcos\" }";
    }
}
