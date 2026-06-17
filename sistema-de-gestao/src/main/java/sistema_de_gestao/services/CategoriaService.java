package sistema_de_gestao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema_de_gestao.entities.Categoria;
import sistema_de_gestao.repositories.CategoriaRepository;
import sistema_de_gestao.repositories.ComponenteRepository;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepo;

    @Autowired
    private ComponenteRepository componenteRepo;

    @Autowired private HistoricoService historicoService;

    public List<Categoria> listarTodas() {
        return categoriaRepo.findByActivoTrue();
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada: " + id));
    }

    public Categoria criar(Categoria categoria) {
        if (categoriaRepo.existsByNomeIgnoreCase(categoria.getNome()))
            throw new RuntimeException("Categoria já existe: " + categoria.getNome());
        categoria.setActivo(true);

        Categoria salva = categoriaRepo.save(categoria);

        historicoService.registar(
                "Categoria",
                String.valueOf(salva.getId()),
                "CRIACAO",
                "Categoria criada: " + salva.getNome(),
                null
        );
        return salva;
    }

    public Categoria actualizar(Long id, Categoria dados) {
        Categoria c = buscarPorId(id);
        c.setNome(dados.getNome());
        c.setDescricao(dados.getDescricao());

        historicoService.registar(
                "Categoria",
                String.valueOf(id),
                "ATUALIZACAO",
                "Categoria actualizada: " + c.getNome(),
                null
        );
        return categoriaRepo.save(c);
    }

    public void desactivar(Long id) {
        Categoria c = buscarPorId(id);
        c.setActivo(false);
        historicoService.registar(
                "Categoria",
                String.valueOf(id),
                "DESATIVACAO",
                "Categoria desactivada: " + c.getNome(),
                null
        );
        categoriaRepo.save(c);
    }
}
