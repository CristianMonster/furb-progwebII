package com.example.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto.model.Funcionario;
import com.example.projeto.model.MensagemDTO;
import com.example.projeto.service.FuncionarioService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping(value = "/funcionario")
public class FuncionarioController {

    //@Autowired
    //private FuncionarioRepository funcRepository;

    @Autowired
    private FuncionarioService funcService;

    @GetMapping("/")
    public ResponseEntity<List<Funcionario>> findAll() {
        return funcService.findAll();
    }

    @GetMapping("/{pIdFuncionario}")
    public ResponseEntity<Funcionario> findById(@PathVariable Integer pIdFuncionario) {
        return funcService.findById(pIdFuncionario);
    }

    @PostMapping("/{pIdDepto}")
    public ResponseEntity<Funcionario> postFuncionario(@RequestBody Funcionario entity, @PathVariable Integer pIdDepto) {
        return (ResponseEntity<Funcionario>) funcService.insFunc(entity, pIdDepto);
    }
    
    @PutMapping("/{pIdFuncionario}")
    public ResponseEntity<Funcionario> putFuncionario(@PathVariable Integer pIdFuncionario, @RequestBody Funcionario entity) {
        return (ResponseEntity<Funcionario>) funcService.updFunc(pIdFuncionario, entity);
    }
    
    @DeleteMapping("/{pIdFuncionario}")
    public ResponseEntity<MensagemDTO> delFuncionario(@PathVariable Integer pIdFuncionario) {
        return funcService.delFunc(pIdFuncionario);
    }

    @GetMapping("/departamento/pIdDepto")
    public ResponseEntity<List<Funcionario>> findAllDpto(@PathVariable Integer pIdDepto) {
        return funcService.findAllDpto(pIdDepto);
    }
}
