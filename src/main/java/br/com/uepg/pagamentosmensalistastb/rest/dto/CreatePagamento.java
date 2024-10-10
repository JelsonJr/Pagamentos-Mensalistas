package br.com.uepg.pagamentosmensalistastb.rest.dto;

import br.com.uepg.pagamentosmensalistastb.rest.dto.enums.Mes;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreatePagamento(
        @Positive(message = "O ano deve ser um número positivo.")
        Integer ano,

        Mes mes,

        @NotNull(message = "O valor deve ser informado.")
        @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero.")
        BigDecimal valor,

        @NotNull(message = "O jogador deve ser informado.")
        Long jogador
) {}