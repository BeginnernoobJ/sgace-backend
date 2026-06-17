package sistema_de_gestao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema_de_gestao.dtos.ComponenteRequestDTO;
import sistema_de_gestao.dtos.ComponenteResponseDTO;
import sistema_de_gestao.entities.Categoria;
import sistema_de_gestao.entities.Componente;
import sistema_de_gestao.entities.Fornecedor;
import sistema_de_gestao.repositories.CategoriaRepository;
import sistema_de_gestao.repositories.ComponenteRepository;
import sistema_de_gestao.repositories.FornecedorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComponenteService {

    @Autowired private ComponenteRepository componenteRepo;
    @Autowired private CategoriaRepository categoriaRepo;
    @Autowired private FornecedorRepository fornecedorRepo;
    @Autowired private HistoricoService historicoService;

    public List<ComponenteResponseDTO> listarTodos() {
        return componenteRepo.findByActivoTrue()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public ComponenteResponseDTO buscarPorCodigo(String codigo) {
        Componente c = componenteRepo.findById(codigo)
            .orElseThrow(() -> new RuntimeException("Componente não encontrado: " + codigo));
        return toDTO(c);
    }

    public List<ComponenteResponseDTO> pesquisar(String descricao) {
        return componenteRepo
            .findByDescricaoContainingIgnoreCaseAndActivoTrue(descricao)
            .stream().map(this::toDTO).collect(Collectors.toList());
    }



    public ComponenteResponseDTO criar(ComponenteRequestDTO dto) {
        if (componenteRepo.existsById(dto.getCodigoStock()))
            throw new RuntimeException("Código já existe: " + dto.getCodigoStock());

        if (dto.getPrecoUnitario().doubleValue() < 0)
            throw new RuntimeException("Preço não pode ser negativo");

        Categoria cat = categoriaRepo.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Fornecedor forn = fornecedorRepo.findById(dto.getCodigoFornecedor())
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

        Componente c = new Componente();
        c.setCodigoStock(dto.getCodigoStock());
        c.setNome(dto.getNome());
        c.setDescricao(dto.getDescricao());
        c.setPrecoUnitario(dto.getPrecoUnitario());
        c.setStockExistente(dto.getStockExistente() != null ? dto.getStockExistente() : 0);
        c.setStockMinimo(dto.getStockMinimo() != null ? dto.getStockMinimo() : 0);
        c.setUnidade(dto.getUnidade());
        c.setLocalizacaoFisica(dto.getLocalizacaoFisica());
        c.setCategoria(cat);
        c.setFornecedor(forn);
        c.setActivo(true);

        // guardar primeiro — só depois tens o objecto com ID
        Componente salvo = componenteRepo.save(c);

        // agora sim registar no histórico
        historicoService.registar(
                "Componente",
                salvo.getCodigoStock(),
                "CRIACAO",
                "Componente criado: " + salvo.getNome() +
                        " | Preco: " + salvo.getPrecoUnitario() +
                        " | Stock inicial: " + salvo.getStockExistente(),
                null
        );

        return toDTO(salvo);
    }

    public ComponenteResponseDTO actualizar(String codigo, ComponenteRequestDTO dto) {
        Componente c = componenteRepo.findById(codigo)
            .orElseThrow(() -> new RuntimeException("Componente não encontrado"));

        if (dto.getPrecoUnitario().doubleValue() < 0)
            throw new RuntimeException("Preço não pode ser negativo");

        Categoria cat = categoriaRepo.findById(dto.getCategoriaId())
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Fornecedor forn = fornecedorRepo.findById(dto.getCodigoFornecedor())
            .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

        c.setDescricao(dto.getDescricao());
        c.setPrecoUnitario(dto.getPrecoUnitario());
        c.setStockMinimo(dto.getStockMinimo());
        c.setUnidade(dto.getUnidade());
        c.setLocalizacaoFisica(dto.getLocalizacaoFisica());
        c.setCategoria(cat);
        c.setFornecedor(forn);

        Componente salvo = componenteRepo.save(c);

        historicoService.registar(
                "Componente",
                codigo,
                "ATUALIZACAO",
                "Componente actualizado: " + salvo.getDescricao() +
                        " | Novo preco: " + salvo.getPrecoUnitario() +
                        " | Stock minimo: " + salvo.getStockMinimo(),
                null
        );
        return toDTO(salvo);
    }

    public void desactivar(String codigo) {
        Componente c = componenteRepo.findById(codigo)
            .orElseThrow(() -> new RuntimeException("Componente não encontrado"));
        c.setActivo(false);
        componenteRepo.save(c);
        historicoService.registar(
                "Componente",
                codigo,
                "DESATIVACAO",
                "Componente desactivado: " + c.getDescricao(),
                null
        );
    }

    private ComponenteResponseDTO toDTO(Componente c) {
        return new ComponenteResponseDTO(
            c.getCodigoStock(),
            c.getNome(),
            c.getDescricao(),
            c.getPrecoUnitario(),
            c.getStockExistente(),
            c.getStockMinimo(),
            c.getUnidade(),
            c.getLocalizacaoFisica(),
            c.getActivo(),
            c.getCategoria() != null ? c.getCategoria().getNome() : "Sem categoria",
            c.getCategoria() != null ? c.getCategoria().getId() : null,
            c.getFornecedor() != null ? c.getFornecedor().getNome() : "Sem fornecedor",
            c.getFornecedor() != null ? c.getFornecedor().getCodigoFornecedor() : null
        );
    }
}
