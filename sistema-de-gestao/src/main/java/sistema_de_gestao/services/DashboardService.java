package sistema_de_gestao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema_de_gestao.dtos.AlertaDTO;
import sistema_de_gestao.dtos.MetricasDTO;
import sistema_de_gestao.dtos.MovimentoRecenteDTO;
import sistema_de_gestao.entities.AlertaStock;
import sistema_de_gestao.entities.MovimentoStock;
import sistema_de_gestao.enums.TipoMovimento;
import sistema_de_gestao.repositories.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class DashboardService {

    @Autowired private ComponenteRepository componenteRepo;
    @Autowired private FornecedorRepository fornecedorRepo;
    @Autowired private MovimentoStockRepository movimentoRepo;
    @Autowired private AlertaStockRepository alertaRepo;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public MetricasDTO getMetricas() {
        LocalDateTime inicioDia = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime fimDia    = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);

        long totalComponentes  = componenteRepo.countByActivoTrue();
        long totalFornecedores = fornecedorRepo.countByActivoTrue();
        long alertasActivos    = alertaRepo.countByResolvidoFalse();
        long movimentosHoje    = movimentoRepo.countByDataMovBetween(inicioDia, fimDia);
        long entradasHoje      = movimentoRepo.countByTipoAndDataMovBetween(TipoMovimento.ENTRADA, inicioDia, fimDia);
        long saidasHoje        = movimentoRepo.countByTipoAndDataMovBetween(TipoMovimento.SAIDA, inicioDia, fimDia);

        return new MetricasDTO(totalComponentes, movimentosHoje,
                alertasActivos, totalFornecedores,
                entradasHoje, saidasHoje);
    }

    public List<MovimentoRecenteDTO> getMovimentosRecentes() {
        List<MovimentoStock> movimentos = movimentoRepo.findTop10ByOrderByDataMovDesc();
        List<MovimentoRecenteDTO> resultado = new ArrayList<>();

        for (MovimentoStock m : movimentos) {
            resultado.add(new MovimentoRecenteDTO(
                    m.getMov_cod(),
                    m.getTipo(),
                    m.getComponente().getCodigoStock(),
                    m.getComponente().getNome(),
                    m.getComponente().getDescricao(),
                    m.getQuantidade(),
                    m.getUtilizador().getNome(),
                    m.getDataMov().format(FORMATTER)
            ));
        }
        return resultado;
    }

    public List<AlertaDTO> getAlertasActivos() {
        List<AlertaStock> alertas = alertaRepo.findByResolvidoFalse();
        List<AlertaDTO> resultado = new ArrayList<>();

        for (AlertaStock a : alertas) {
            resultado.add(new AlertaDTO(
                    a.getAlertaId(),
                    a.getComponente().getCodigoStock(),
                    a.getComponente().getDescricao(),
                    a.getStockNow(),
                    a.getStockMin(),
                    a.getAlertData().format(FORMATTER)
            ));
        }
        return resultado;
    }
    public List<Map<String, Object>> getMovimentosPorDia() {
        LocalDateTime inicioSemana = LocalDateTime.now().minusDays(6)
                .withHour(0).withMinute(0).withSecond(0);

        List<Object[]> raw = movimentoRepo.findMovimentosPorDia(inicioSemana);
        List<Map<String, Object>> resultado = new ArrayList<>();

        for (Object[] row : raw) {
            Map<String, Object> item = new HashMap<>();
            item.put("data", row[0].toString());
            item.put("tipo", row[1].toString());
            item.put("quantidade", row[2]);
            resultado.add(item);
        }
        return resultado;
    }

    public List<Map<String, Object>> getComponentesPorCategoria() {
        List<Object[]> raw = componenteRepo.countPorCategoria();
        List<Map<String, Object>> resultado = new ArrayList<>();

        for (Object[] row : raw) {
            Map<String, Object> item = new HashMap<>();
            item.put("categoria", row[0] != null ? row[0].toString() : "Sem categoria");
            item.put("total", row[1]);
            resultado.add(item);
        }
        return resultado;
    }
}
