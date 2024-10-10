package br.com.uepg.pagamentosmensalistastb.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.util.Date;

public record CreateJogador(
        @NotBlank(message = "O nome não pode ser vazio.")
        String nome,

        @Email(message = "O email deve ser válido.")
        @NotBlank(message = "O email não pode ser vazio.")
        String email,

        @NotNull(message = "A data de nascimento não pode ser nula.")
        @Past(message = "A data de nascimento precisa ser uma data passada.")
        Date dataNasc
) {
}