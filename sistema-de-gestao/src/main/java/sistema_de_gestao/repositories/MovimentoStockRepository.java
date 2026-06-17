package sistema_de_gestao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sistema_de_gestao.entities.MovimentoStock;
import sistema_de_gestao.enums.TipoMovimento;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimentoStockRepository extends JpaRepository<MovimentoStock, Long> {

    long countByDataMovBetween(LocalDateTime inicio, LocalDateTime fim);

    long countByTipoAndDataMovBetween(TipoMovimento tipo, LocalDateTime inicio, LocalDateTime fim);

    List<MovimentoStock> findTop10ByOrderByDataMovDesc();

    List<MovimentoStock> findByComponenteCodigoStockOrderByDataMovDesc(String codigoStock);

    @Query("SELECT DATE(m.dataMov), m.tipo, SUM(m.quantidade) " +
            "FROM MovimentoStock m " +
            "WHERE m.dataMov >= :inicio " +
            "GROUP BY DATE(m.dataMov), m.tipo " +
            "ORDER BY DATE(m.dataMov)")
    List<Object[]> findMovimentosPorDia(LocalDateTime inicio);
}
