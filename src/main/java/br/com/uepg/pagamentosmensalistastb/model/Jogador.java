package br.com.uepg.pagamentosmensalistastb.model;

import br.com.uepg.pagamentosmensalistastb.rest.dto.CreateJogador;
import br.com.uepg.pagamentosmensalistastb.rest.dto.UpdateJogador;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "jogadores")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codJogador;

    @Temporal(TemporalType.DATE)
    private Date dataNasc;
    private String nome;
    private String email;

    @OneToMany(mappedBy = "jogador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Pagamento> pagamentos;

    public Jogador(CreateJogador jogador) {
        this.dataNasc = jogador.dataNasc();
        this.email = jogador.email();
        this.nome = jogador.nome();
    }

    public void update(UpdateJogador dadosJogador) {
        this.nome = dadosJogador.nome();
        this.email = dadosJogador.email();
        this.dataNasc = dadosJogador.dataNasc();
    }
}