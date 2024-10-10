package br.com.uepg.pagamentosmensalistastb.erros;

public record ResponseError(
        String error,
        String message
) {
}
