package br.com.uepg.pagamentosmensalistastb.repository;

import br.com.uepg.pagamentosmensalistastb.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    List<Pagamento> findByJogador_CodJogador(Long codJogador);
}
