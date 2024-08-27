package com.example.projeto.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.projeto.model.Departamento;
import com.example.projeto.repository.DepartamentoRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping(value = "/departamento")
public class DepartamentoController {

    @Autowired
    private DepartamentoRepository deptoRep;

    @GetMapping("/")
    public ResponseEntity<List<Departamento>> findAll() {
        List<Departamento> vDeptos = deptoRep.findAll();
        return ResponseEntity.ok().body(vDeptos);
    }

    @GetMapping("/{pIdDepto}")
    public ResponseEntity<Departamento> findById(@PathVariable Integer pIdDepto) {
        Departamento vDepto = deptoRep
            .findById(pIdDepto)
            .orElseThrow();
        return ResponseEntity.ok().body(vDepto);
    }
    
    @PostMapping
    public ResponseEntity<Departamento> insDepto(@RequestBody Departamento pDepto) {
        pDepto.setIdDepto(null);
        deptoRep.save(pDepto);
        URI vURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idDepto}").buildAndExpand(pDepto.getIdDepto()).toUri();
        return ResponseEntity.created(vURI).body(pDepto);
    }

    @PutMapping("/{pIdDepto}")
    public ResponseEntity<Departamento> updDepto(@PathVariable Integer pIdDepto, @RequestBody Departamento pDepto) {
        Departamento vDeptoAtual = deptoRep
            .findById(pIdDepto)
            .orElseThrow();
        if (pDepto.getNmDepto() != null) {
            vDeptoAtual.setNmDepto(pDepto.getNmDepto());
        }
        if (pDepto.getEmail() != null) {
            vDeptoAtual.setEmail(pDepto.getEmail());
        }
        deptoRep.save(vDeptoAtual);
        
        return ResponseEntity.ok().body(vDeptoAtual);
    }

    @DeleteMapping("/{pIdDepto}")
    public ResponseEntity<String> delDepto(@PathVariable @Valid Integer pIdDepto) {
        deptoRep
            .findById(pIdDepto)
            .orElseThrow();
        
        deptoRep.deleteById(pIdDepto);
        return ResponseEntity.ok().body("{ \"status\" : \"Departamento deletado com sucesso!\" }");
    }
}
