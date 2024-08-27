package com.example.projeto.model;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentoDTO {
    private Integer idDepto;
    
    @NotEmpty(message = "Nome do departamento não pode ser vazio!")
    @NotNull(message = "Nome do departamento não pode ser nulo!")
    @Length(min=5, max=255, message="Nome do departamento deve ter entre 5 e 255 caracteres")
    private String nmDepto;
}
