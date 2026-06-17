package sistema_de_gestao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema_de_gestao.entities.Fornecedor;
import sistema_de_gestao.repositories.FornecedorRepository;

import java.util.List;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepo;
    @Autowired private HistoricoService historicoService;

    public List<Fornecedor> listarTodos() {
        return fornecedorRepo.findByActivoTrue();
    }

    public Fornecedor buscarPorCodigo(String codigo) {
        return fornecedorRepo.findById(codigo)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado: " + codigo));
    }

    public Fornecedor criar(Fornecedor fornecedor) {
        if (fornecedorRepo.existsById(fornecedor.getCodigoFornecedor()))
            throw new RuntimeException("Código já existe: " + fornecedor.getCodigoFornecedor());
        fornecedor.setActivo(true);
        Fornecedor salvo = fornecedorRepo.save(fornecedor);
        historicoService.registar(
                "Fornecedor",
                salvo.getCodigoFornecedor(),
                "CRIACAO",
                "Fornecedor criado: " + salvo.getNome() +
                        " | Email: " + salvo.getEmail() +
                        " | Localizacao: " + salvo.getLocalizacao(),
                null
        );
        return salvo;
    }

    public Fornecedor actualizar(String codigo, Fornecedor dados) {
        Fornecedor f = buscarPorCodigo(codigo);
        f.setNome(dados.getNome());
        f.setEmail(dados.getEmail());
        f.setTelefone(dados.getTelefone());
        f.setLocalizacao(dados.getLocalizacao());
        Fornecedor salvo = fornecedorRepo.save(f);
        historicoService.registar(
                "Fornecedor",
                salvo.getCodigoFornecedor(),
                "CRIACAO",
                "Fornecedor criado: " + salvo.getNome() +
                        " | Email: " + salvo.getEmail() +
                        " | Localizacao: " + salvo.getLocalizacao(),
                null
        );
        return salvo;
    }

    public void desactivar(String codigo) {
        Fornecedor f = buscarPorCodigo(codigo);
        f.setActivo(false);
        historicoService.registar(
                "Fornecedor",
                codigo,
                "DESATIVACAO",
                "Fornecedor desactivado: " + f.getNome(),
                null
        );
        fornecedorRepo.save(f);
    }
}