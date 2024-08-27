package com.example.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projeto.model.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {

}
