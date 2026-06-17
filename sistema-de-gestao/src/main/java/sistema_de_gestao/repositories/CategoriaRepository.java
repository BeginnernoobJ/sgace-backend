package sistema_de_gestao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema_de_gestao.entities.Categoria;
import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findByActivoTrue();

    Optional<Categoria> findByNomeIgnoreCase(String nome);

    boolean existsByNomeIgnoreCase(String nome);
}
