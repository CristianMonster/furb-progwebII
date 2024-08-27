package com.example.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.projeto.model.Funcionario;

//Segundo parametro do <> é qual o tipo da chave primária dele
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{

    @Query(value = "SELECT id_funcionario, nm_funcionario, email, id_depto FROM funcionario WHERE id_depto = :pIdDepto ORDER BY nm_funcionario", nativeQuery = true)
    public List<Funcionario> findByDepto(@Param("pIdDepto") Integer pIdDepto);
}
