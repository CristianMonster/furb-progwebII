package com.example.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projeto.model.Funcionario;

//Segundo parametro do <> é qual o tipo da chave primária dele
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{

}
