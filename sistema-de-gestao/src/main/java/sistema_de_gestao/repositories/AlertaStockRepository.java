package sistema_de_gestao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema_de_gestao.entities.AlertaStock;
import java.util.List;

public interface AlertaStockRepository extends JpaRepository<AlertaStock, Long> {
    List<AlertaStock> findByResolvidoFalse();
    long countByResolvidoFalse();
}