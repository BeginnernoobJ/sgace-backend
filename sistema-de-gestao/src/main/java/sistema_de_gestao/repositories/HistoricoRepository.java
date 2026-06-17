package sistema_de_gestao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sistema_de_gestao.entities.Historico;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {

    List<Historico> findAllByOrderByDataHistoricoDesc();

    List<Historico> findByEntidadeOrderByDataHistoricoDesc(String entidade);

    List<Historico> findByUtilizadorIdOrderByDataHistoricoDesc(Integer utilizadorId);

    @Query("SELECT h FROM Historico h WHERE " +
            "(:entidade IS NULL OR h.entidade = :entidade) AND " +
            "(:operacao IS NULL OR h.operacao = :operacao) AND " +
            "(:inicio IS NULL OR h.dataHistorico >= :inicio) AND " +
            "(:fim IS NULL OR h.dataHistorico <= :fim) " +
            "ORDER BY h.dataHistorico DESC")
    List<Historico> filtrar(
            @Param("entidade") String entidade,
            @Param("operacao") String operacao,
            @Param("inicio")   LocalDateTime inicio,
            @Param("fim")      LocalDateTime fim
    );
}