package sistema_de_gestao.dtos;

public class MetricasDTO {
    private long totalComponentes;
    private long movimentosHoje;
    private long alertasActivos;
    private long totalFornecedores;
    private long entradasHoje;
    private long saidasHoje;

    public MetricasDTO(long totalComponentes, long movimentosHoje,
                       long alertasActivos, long totalFornecedores,
                       long entradasHoje, long saidasHoje) {
        this.totalComponentes = totalComponentes;
        this.movimentosHoje = movimentosHoje;
        this.alertasActivos = alertasActivos;
        this.totalFornecedores = totalFornecedores;
        this.entradasHoje = entradasHoje;
        this.saidasHoje = saidasHoje;
    }

    public long getTotalComponentes() { return totalComponentes; }
    public long getMovimentosHoje() { return movimentosHoje; }
    public long getAlertasActivos() { return alertasActivos; }
    public long getTotalFornecedores() { return totalFornecedores; }
    public long getEntradasHoje() { return entradasHoje; }
    public long getSaidasHoje() { return saidasHoje; }
}
