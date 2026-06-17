package sistema_de_gestao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema_de_gestao.entities.Fornecedor;

import java.util.List;

public interface FornecedorRepository extends JpaRepository<Fornecedor, String> {

    List<Fornecedor> findByActivoTrue();

    long countByActivoTrue();
}