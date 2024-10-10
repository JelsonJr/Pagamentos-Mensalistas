package br.com.uepg.pagamentosmensalistastb.controller;

import br.com.uepg.pagamentosmensalistastb.model.Pagamento;
import br.com.uepg.pagamentosmensalistastb.rest.dto.CreatePagamento;
import br.com.uepg.pagamentosmensalistastb.rest.dto.DadosPagamentoJogador;
import br.com.uepg.pagamentosmensalistastb.rest.dto.UpdatePagamento;
import br.com.uepg.pagamentosmensalistastb.service.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService service;

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<List<Pagamento>> getAll() {
        var pagamentos = this.service.getAll();

        return ResponseEntity.ok(pagamentos);
    }

    @GetMapping("/{codPagamento}")
    @Transactional(readOnly = true)
    public ResponseEntity<Pagamento> getByCodigoPagamento(@PathVariable Long codPagamento) {
        var pagamento = this.service.getByCodigoPagamento(codPagamento);

        return ResponseEntity.ok(pagamento);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Pagamento> create(@RequestBody @Valid CreatePagamento dadosPagamento, UriComponentsBuilder uriBuilder) {
        var savedPayment = this.service.create(dadosPagamento);
        var uri = uriBuilder.path("/pagamentos/{codPagamento}").buildAndExpand(savedPayment.getCodPagamento()).toUri();

        return ResponseEntity.created(uri).body(savedPayment);
    }

    @PutMapping("/{codPagamento}")
    @Transactional
    public ResponseEntity<Pagamento> update(@RequestBody @Valid UpdatePagamento dadosPagamento, @PathVariable Long codPagamento) {
        var savedPayment = this.service.update(dadosPagamento, codPagamento);

        return ResponseEntity.ok(savedPayment);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> deleteAll() {
       this.service.deleteAll();

        return ResponseEntity.noContent().build();
    }

        @DeleteMapping("/{codPagamento}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long codPagamento) {
       this.service.delete(codPagamento);

        return ResponseEntity.noContent().build();
    }
 }
