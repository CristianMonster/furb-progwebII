package com.example.projeto.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/teste")
public class Teste {

        @GetMapping("/testeGet")
        public String getMethodName1() {
            return new String("Ola mundo");
        }
        
        @PostMapping("/post")
        public String postMethodName() {
            //TODO: process POST request
            
            return "entity";
        }

        @GetMapping("/testeGet/{param}")
        public String getAlgumaCoisa(@PathVariable Integer param) {
            return new String("parametro informado: " + param);
        }
        
        @GetMapping(value = "/geraJson", produces = MediaType.APPLICATION_JSON_VALUE)
        public String getMethodName() {
            return "{\"nome\": teste}";
        }
        
        
}
