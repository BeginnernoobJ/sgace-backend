package sistema_de_gestao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sistema_de_gestao.entities.Componente;

public interface ComponenteRepository extends JpaRepository<Componente, String> {

    long countByActivoTrue();

    List<Componente> findByActivoTrue();
    List<Componente> findByNome(String nome);

    List<Componente> findByDescricaoContainingIgnoreCaseAndActivoTrue(String descricao);

    List<Componente> findByCategoriaIdAndActivoTrue(Long categoriaId);

    List<Componente> findByFornecedorCodigoFornecedorAndActivoTrue(String codigoFornecedor);

    @Query("SELECT c.categoria.nome, COUNT(c) FROM Componente c WHERE c.activo = true GROUP BY c.categoria.nome")
    List<Object[]> countPorCategoria();

    @Query("SELECT c FROM Componente c WHERE c.activo = true AND c.stockExistente <= c.stockMinimo")
    List<Componente> findComponentesAbaixoDoStockMinimo();
}