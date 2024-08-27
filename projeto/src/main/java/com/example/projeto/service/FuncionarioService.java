package com.example.projeto.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.projeto.model.Departamento;
import com.example.projeto.model.Funcionario;
import com.example.projeto.model.MensagemDTO;
import com.example.projeto.repository.DepartamentoRepository;
import com.example.projeto.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired //faz conexão com o banco e todas as coisas necessarias
    private FuncionarioRepository funcRepository;

    @Autowired
    private DepartamentoRepository deptoRepository;

    public ResponseEntity<List<Funcionario>> findAll(){
        return ResponseEntity.ok().body(funcRepository.findAll());
    }

    public ResponseEntity<Funcionario> findById(Integer pIdFuncionario){
        Funcionario vFunc = funcRepository
            .findById(pIdFuncionario)
            .orElseThrow();
    
        return ResponseEntity.ok().body(vFunc);
    }

    public ResponseEntity<?> insFunc(@RequestBody Funcionario pFunc, Integer pIdDepto)
    {
        pFunc.setIdFuncionario(null);

        Departamento vDepto = deptoRepository.findById(pIdDepto).orElseThrow();
        
        pFunc.setDepartamentoFunc(vDepto);

        funcRepository.save(pFunc);
        if (pFunc.getDepartamentoFunc() != null) {
            URI vURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idFunc}").buildAndExpand(pFunc.getIdFuncionario()).toUri();
            return ResponseEntity.created(vURI).body(pFunc);
        } 
        
        return ResponseEntity.badRequest().body("Erro ao informar ");
        
    }

    public ResponseEntity<Funcionario> updFunc(Integer pIdFuncionario, Funcionario pFuncionario ) {
        Funcionario vFuncionario = funcRepository
            .findById(pIdFuncionario)
            .orElseThrow();
        if (pFuncionario.getNmFuncionario() != null) {
            vFuncionario.setNmFuncionario(pFuncionario.getNmFuncionario());
        }
        if (pFuncionario.getEmail() != null) {
            vFuncionario.setEmail(pFuncionario.getEmail());
        }
        if (pFuncionario.getDepartamentoFunc() != null) {
            vFuncionario.setDepartamentoFunc(pFuncionario.getDepartamentoFunc());
        }
        funcRepository.save(vFuncionario);
        
        return ResponseEntity.ok().body(vFuncionario);
    }

    public ResponseEntity<MensagemDTO> delFunc(Integer pIdFuncionario)
    {
        funcRepository.findById(pIdFuncionario).orElseThrow();

        deptoRepository.deleteById(pIdFuncionario);
        MensagemDTO vMensagemDTO = new MensagemDTO("OK", "Deletado com sucesso");
        return ResponseEntity.ok().body(vMensagemDTO);
    }

    public ResponseEntity<List<Funcionario>> findAllDpto(Integer pIdDepto){
        return ResponseEntity.ok().body(funcRepository.findByDepto(pIdDepto));
    }
}
