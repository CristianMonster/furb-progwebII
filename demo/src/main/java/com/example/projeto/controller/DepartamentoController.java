package com.example.projeto.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.projeto.model.Departamento;
import com.example.projeto.repository.DepartamentoRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping(value = "/departamento")
public class DepartamentoController {

    @Autowired
    private DepartamentoRepository deptoRep;
    
    @GetMapping()
    public ResponseEntity<List<Departamento>> findAll() {
        List<Departamento> vDtops = deptoRep.findAll();
        return ResponseEntity.ok().body(vDtops);
    }

    @GetMapping("/{pIdDtpo}")
    public ResponseEntity<Departamento> findById(@PathVariable Integer pIdDtpo) {
        Departamento vDpto = deptoRep.findById(pIdDtpo).orElseThrow();
        return ResponseEntity.ok().body(vDpto);
        
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Departamento> insDepto(@RequestBody Departamento entity) {
        entity.setIdDpto(null);
        Departamento savedDepartamento = deptoRep.save(entity);
        URI url = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{idDepto}")
            .buildAndExpand(savedDepartamento.getIdDpto())
            .toUri();

            
        return ResponseEntity.created(url).body(savedDepartamento);
    }

    @PutMapping("/{idDepto}")
    public ResponseEntity<Departamento> putMethodName(@PathVariable Integer idDepto, @RequestBody Departamento entity) {
        //TODO: process PUT request
        Departamento dpAtual = deptoRep.findById(idDepto).orElseThrow();
        dpAtual.setNomeDpto(entity.getNomeDpto());
        if (entity.getEmail() != null) {
            dpAtual.setEmail(entity.getEmail());
        } else {
            return ResponseEntity.badRequest().body(null);
        }
        deptoRep.save(dpAtual);        
        return ResponseEntity.ok().body(dpAtual);
    }
    
    @DeleteMapping("/{idDepto}")
    public ResponseEntity<Void> deleteDepto(@PathVariable Integer idDepto) {
        return deptoRep.findById(idDepto).map(d -> {deptoRep.delete(d); 
            return ResponseEntity.noContent().<Void>build();}).orElse(ResponseEntity.notFound().build());
    }
}
