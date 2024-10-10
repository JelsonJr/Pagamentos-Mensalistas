package br.com.uepg.pagamentosmensalistastb.service;

import br.com.uepg.pagamentosmensalistastb.model.Jogador;
import br.com.uepg.pagamentosmensalistastb.model.Pagamento;
import br.com.uepg.pagamentosmensalistastb.repository.JogadorRepository;
import br.com.uepg.pagamentosmensalistastb.repository.PagamentoRepository;
import br.com.uepg.pagamentosmensalistastb.rest.dto.CreateJogador;
import br.com.uepg.pagamentosmensalistastb.rest.dto.DadosPagamento;
import br.com.uepg.pagamentosmensalistastb.rest.dto.DadosPagamentoJogador;
import br.com.uepg.pagamentosmensalistastb.rest.dto.UpdateJogador;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository repository;

    public List<Jogador> getAll() {
        return this.repository.findAll();
    }

    public Jogador getByCodigoJogador(Long codJogador) {
        return this.repository.findByIdWithPagamentos(codJogador).orElseThrow(() -> new EntityNotFoundException("Jogador com código " + codJogador + " não encontrado"));
    }

    public DadosPagamentoJogador getByJogador(Long codJogador) {
        var jogador = this.repository.findByIdWithPagamentos(codJogador).orElseThrow(() -> new EntityNotFoundException("Jogador com código " + codJogador + " não encontrado"));

        return new DadosPagamentoJogador(jogador);
    }


    public Jogador save(@Valid CreateJogador jogador) {
        return this.repository.save(new Jogador(jogador));
    }

    public Jogador update(Long codJogador, UpdateJogador dadosJogador) {
        var jogador = this.repository.findById(codJogador).orElseThrow(() -> new EntityNotFoundException("Jogador com código " + codJogador + " não encontrado"));
        jogador.update(dadosJogador);

        return jogador;
    }

    public void deleteById(Long codJogador) {
        this.repository.deleteById(codJogador);
    }
}
