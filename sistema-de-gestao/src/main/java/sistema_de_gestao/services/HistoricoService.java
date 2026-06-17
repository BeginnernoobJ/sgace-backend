package sistema_de_gestao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema_de_gestao.dtos.HistoricoDTO;
import sistema_de_gestao.entities.Historico;
import sistema_de_gestao.entities.User;
import sistema_de_gestao.enums.TipoOperacao;
import sistema_de_gestao.repositories.HistoricoRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepo;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public List<HistoricoDTO> listarTodos() {
        return historicoRepo.findAllByOrderByDataHistoricoDesc()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<HistoricoDTO> filtrar(String entidade, String operacao,
                                      String inicio, String fim) {
        LocalDateTime dtInicio = inicio != null && !inicio.isEmpty()
                ? LocalDateTime.parse(inicio + "T00:00:00") : null;
        LocalDateTime dtFim = fim != null && !fim.isEmpty()
                ? LocalDateTime.parse(fim + "T23:59:59") : null;

        return historicoRepo.filtrar(
                entidade != null && !entidade.isEmpty() ? entidade : null,
                operacao != null && !operacao.isEmpty() ? operacao : null,
                dtInicio, dtFim
        ).stream().map(this::toDTO).collect(Collectors.toList());
    }

    // chamado internamente por outros services
    public void registar(String entidade, String codigoEntidade,
                         String operacao, String descricao,
                         User utilizador) {
        Historico h = new Historico();
        h.setEntidade(entidade);
        h.setCodEntidade(codigoEntidade);
        h.setOperacao(TipoOperacao.valueOf(operacao));
        h.setDescricao(descricao);
        h.setDataHistorico(LocalDateTime.now()); // data atual
        h.setUtilizador(utilizador);             // associar utilizador
        historicoRepo.save(h);
    }

    private HistoricoDTO toDTO(Historico h) {
        String operacao = h.getOperacao() != null ? h.getOperacao().name() : "--";
        String dataHora = h.getDataHistorico() != null ? h.getDataHistorico().format(FORMATTER) : "--";
        String nomeUtil = h.getUtilizador() != null ? h.getUtilizador().getNome() : "Sistema";

        return new HistoricoDTO(
                h.getAlteracaoId(),
                h.getEntidade(),
                h.getCodEntidade(),
                operacao,
                h.getDescricao(),
                dataHora,
                nomeUtil
        );
    }
}