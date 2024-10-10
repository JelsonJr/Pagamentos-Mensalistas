package br.com.uepg.pagamentosmensalistastb.service;

import br.com.uepg.pagamentosmensalistastb.model.Pagamento;
import br.com.uepg.pagamentosmensalistastb.repository.JogadorRepository;
import br.com.uepg.pagamentosmensalistastb.repository.PagamentoRepository;
import br.com.uepg.pagamentosmensalistastb.rest.dto.CreatePagamento;
import br.com.uepg.pagamentosmensalistastb.rest.dto.UpdatePagamento;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private JogadorRepository jogadorRepository;

    public List<Pagamento> getAll() {
        return this.repository.findAll();
    }

    public Pagamento getByCodigoPagamento(Long codPagamento) {
        return this.repository
                .findById(codPagamento)
                .orElseThrow(() -> new EntityNotFoundException("Pagamento com código " + codPagamento + " não encontrado."));
    }

    public Pagamento create(@Valid CreatePagamento dadosPagamento) {
        var jogador = this.jogadorRepository
                .findByIdWithPagamentos(dadosPagamento.jogador())
                .orElseThrow(() -> new EntityNotFoundException("Jogador com código " + dadosPagamento.jogador() + " não encontrado."));

        var pagamento = new Pagamento(dadosPagamento, jogador);
        jogador.getPagamentos().add(pagamento);

        return this.repository.save(pagamento);
    }

    public Pagamento update(UpdatePagamento dadosPagamento, Long codPagamento) {
        var pagamento = this.repository.findById(codPagamento).orElseThrow(() -> new EntityNotFoundException("Pagamento com código " + codPagamento + " não encontrado."));
        var codJogador = dadosPagamento.jogador();

        pagamento.update(dadosPagamento);

        if(!pagamento.getJogador().getCodJogador().equals(codJogador)) {
            var novoJogador = this.jogadorRepository.findByIdWithPagamentos(codJogador).orElseThrow(() -> new EntityNotFoundException("Jogador com código " + dadosPagamento.jogador() + " não encontrado."));
            var antigoJogador = this.jogadorRepository.findByIdWithPagamentos(pagamento.getJogador().getCodJogador()).orElseThrow();

            novoJogador.getPagamentos().add(pagamento);
            antigoJogador.getPagamentos().remove(pagamento);

            pagamento.setJogador(novoJogador);
        }

        return pagamento;
    }

    public void delete(Long codPagamento) {
        this.repository.deleteById(codPagamento);
    }

    public void deleteAll() {
        this.repository.deleteAll();
    }
}
