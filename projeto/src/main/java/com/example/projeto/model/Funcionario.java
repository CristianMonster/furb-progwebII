package com.example.projeto.model;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFuncionario;
    @NotNull(message = "Nome do funcionário não pode ser nulo!")
    @Length(min = 5, max = 255, message = "Nome do funcionário deve ser entre 5 e 255 caracteres!")
    private String nmFuncionario;
    @Email
    @NotNull(message = "E-mail do funcionário deve ser informado!")
    private String email;

    @ManyToOne 
    @JoinColumn(name = "id_depto")
    private Departamento departamentoFunc;
}
