package br.com.uepg.pagamentosmensalistastb.model;

import br.com.uepg.pagamentosmensalistastb.rest.dto.CreatePagamento;
import br.com.uepg.pagamentosmensalistastb.rest.dto.UpdatePagamento;
import br.com.uepg.pagamentosmensalistastb.rest.dto.enums.Mes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagamentos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codPagamento;

    @Enumerated(EnumType.ORDINAL)
    private Mes mes;

    @ManyToOne
    @JoinColumn(name = "cod_jogador", nullable = false)
    private Jogador jogador;

    private Integer ano;
    private BigDecimal valor;

    public Pagamento(CreatePagamento dadosPagamento, Jogador jogador) {
        this.jogador = jogador;
        this.mes = dadosPagamento.mes() != null ? dadosPagamento.mes() : Mes.fromNumero(LocalDateTime.now().getMonth().getValue());
        this.valor = dadosPagamento.valor();
        this.ano = dadosPagamento.ano() != null ? dadosPagamento.ano() : LocalDateTime.now().getYear() ;
    }

    public void update(UpdatePagamento dadosPagamento) {
        this.ano = dadosPagamento.ano();
        this.mes = dadosPagamento.mes();
        this.valor = dadosPagamento.valor();
    }
}
