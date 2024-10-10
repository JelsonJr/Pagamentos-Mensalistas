package br.com.uepg.pagamentosmensalistastb.rest.dto;

import br.com.uepg.pagamentosmensalistastb.model.Pagamento;
import br.com.uepg.pagamentosmensalistastb.rest.dto.enums.Mes;

import java.math.BigDecimal;

public record DadosPagamento(
        Long codPagamento,
        Mes mes,
        Integer ano,
        BigDecimal valor
) {

    public DadosPagamento(Pagamento pagamento) {
        this(pagamento.getCodPagamento(), pagamento.getMes(), pagamento.getAno(), pagamento.getValor());
    }
}
