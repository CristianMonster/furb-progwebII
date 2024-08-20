package com.example.projeto.model;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartamentoDTO {
    
    private Integer idDpto;
    
    @NotEmpty(message = "nome do derpartamento nao pode ser vazio")
    @NotNull(message = "nome do derpartamento nao pode ser nullo")
    @Length(min = 5, max = 255, message = "msg length")
    private String nmDepto;
}
