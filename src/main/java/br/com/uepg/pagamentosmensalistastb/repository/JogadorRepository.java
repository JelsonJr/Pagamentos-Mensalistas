package br.com.uepg.pagamentosmensalistastb.repository;

import br.com.uepg.pagamentosmensalistastb.model.Jogador;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    @EntityGraph(attributePaths = "pagamentos")
    @Query("SELECT j FROM Jogador j WHERE j.codJogador = :codJogador")
    Optional<Jogador> findByIdWithPagamentos(Long codJogador);
}
