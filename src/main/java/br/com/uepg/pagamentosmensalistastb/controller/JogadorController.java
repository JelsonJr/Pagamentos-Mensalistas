package br.com.uepg.pagamentosmensalistastb.controller;

import br.com.uepg.pagamentosmensalistastb.model.Jogador;
import br.com.uepg.pagamentosmensalistastb.rest.dto.CreateJogador;
import br.com.uepg.pagamentosmensalistastb.rest.dto.DadosPagamentoJogador;
import br.com.uepg.pagamentosmensalistastb.rest.dto.UpdateJogador;
import br.com.uepg.pagamentosmensalistastb.service.JogadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {

    @Autowired
    private JogadorService service;

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<List<Jogador>> getAll() {
        var jogadores = this.service.getAll();

        return ResponseEntity.ok(jogadores);
    }

    @GetMapping("/{codJogador}")
    @Transactional(readOnly = true)
    public ResponseEntity<Jogador> getByCodigo(@PathVariable Long codJogador) {
        var jogador = this.service.getByCodigoJogador(codJogador);

        return ResponseEntity.ok(jogador);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Jogador> create(@RequestBody @Valid CreateJogador jogador, UriComponentsBuilder uriBuilder) {
        var savedJogador = this.service.save(jogador);
        var uri = uriBuilder.path("/jogadores/{codJogador}").buildAndExpand(savedJogador.getCodJogador()).toUri();

        return ResponseEntity.created(uri).body(savedJogador);
    }

    @PutMapping("/{codJogador}")
    @Transactional
    public ResponseEntity<Jogador> update(@PathVariable Long codJogador, @RequestBody @Valid UpdateJogador jogador) {
        var updatedJogador = this.service.update(codJogador, jogador);
        return ResponseEntity.ok(updatedJogador);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{codJogador}/pagamentos")
    @Transactional(readOnly = true)
    public ResponseEntity<DadosPagamentoJogador> getByJogador(@PathVariable Long codJogador) {
        var pagamentos = this.service.getByJogador(codJogador);

        return ResponseEntity.ok(pagamentos);
    }
}
