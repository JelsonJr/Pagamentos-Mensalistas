package br.com.uepg.pagamentosmensalistastb.rest.dto;

import br.com.uepg.pagamentosmensalistastb.model.Jogador;

import java.util.List;

public record DadosPagamentoJogador(
        Jogador jogador,
        List<DadosPagamento> pagamentos
) {

    public DadosPagamentoJogador(Jogador jogador) {
        this(jogador, jogador.getPagamentos().stream().map(DadosPagamento::new).toList());
    }
}
