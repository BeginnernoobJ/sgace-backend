package sistema_de_gestao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sistema_de_gestao.entities.Componente;
import sistema_de_gestao.entities.MovimentoStock;
import sistema_de_gestao.entities.User;
import sistema_de_gestao.enums.TipoMovimento;
import sistema_de_gestao.repositories.AlertaStockRepository;
import sistema_de_gestao.repositories.ComponenteRepository;
import sistema_de_gestao.repositories.MovimentoStockRepository;
import sistema_de_gestao.repositories.UserRepository;
import sistema_de_gestao.entities.AlertaStock;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimentoStockService {

    @Autowired private MovimentoStockRepository movimentoRepo;
    @Autowired private ComponenteRepository componenteRepo;
    @Autowired private UserRepository userRepo;
    @Autowired private AlertaStockRepository alertaRepo;
    @Autowired private HistoricoService historicoService;

    public List<MovimentoStock> listarTodos() {
        return movimentoRepo.findAll();
    }

    public List<MovimentoStock> listarPorComponente(String codigoStock) {
        return movimentoRepo.findByComponenteCodigoStockOrderByDataMovDesc(codigoStock);
    }

    @Transactional
    public MovimentoStock registar(String codigoStock, String tipo,
                                   Integer quantidade, String observacao,
                                   Integer utilizadorId) {

        Componente comp = componenteRepo.findById(codigoStock)
                .orElseThrow(() -> new RuntimeException("Componente não encontrado"));

        if (quantidade <= 0)
            throw new RuntimeException("Quantidade deve ser maior que zero");

        TipoMovimento tipoMov = TipoMovimento.valueOf(tipo);

        if (tipoMov == TipoMovimento.SAIDA) {
            if (quantidade > comp.getStockExistente())
                throw new RuntimeException(
                        "Stock insuficiente. Disponível: " + comp.getStockExistente()
                );
            comp.setStockExistente(comp.getStockExistente() - quantidade);
        } else {
            comp.setStockExistente(comp.getStockExistente() + quantidade);
        }

        componenteRepo.save(comp);

        // verificar se stock ficou abaixo do mínimo
        if (comp.getStockExistente() <= comp.getStockMinimo()) {
            AlertaStock alerta = new AlertaStock();
            alerta.setComponente(comp);
            alerta.setStockNow(comp.getStockExistente());
            alerta.setStockMin(comp.getStockMinimo());
            alerta.setAlertData(LocalDateTime.now());
            alerta.setResolvido(false);
            alertaRepo.save(alerta);
        }

        User utilizador = userRepo.findById(utilizadorId)
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));

        MovimentoStock mov = new MovimentoStock();
        mov.setComponente(comp);
        mov.setTipo(tipoMov);
        mov.setQuantidade(quantidade);
        mov.setObservacao(observacao);
        mov.setDataMov(LocalDateTime.now());
        mov.setUtilizador(utilizador);

        MovimentoStock Mov = movimentoRepo.save(mov);
        historicoService.registar(
                "Movimento",
                String.valueOf(mov.getMov_cod()),
                "CRIACAO",
                "Movimento " + tipoMov.name() +
                        " | Componente: " + comp.getCodigoStock() +
                        " | Nome: " + comp.getNome() +
                        " | Descricao: " + comp.getDescricao() +
                        " | Quantidade: " + quantidade +
                        " | Stock apos movimento: " + comp.getStockExistente(),
                utilizador
        );
        return Mov;
    }
}