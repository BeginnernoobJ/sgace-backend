package sistema_de_gestao.dtos;

public class AlertaDTO {
    private Long id;
    private String codigoComponente;
    private String descricaoComponente;
    private Integer stockActual;
    private Integer stockMinimo;
    private String dataAlerta;

    public AlertaDTO(Long id, String codigoComponente, String descricaoComponente,
                     Integer stockActual, Integer stockMinimo, String dataAlerta) {
        this.id = id;
        this.codigoComponente = codigoComponente;
        this.descricaoComponente = descricaoComponente;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.dataAlerta = dataAlerta;
    }

    public Long getId() { return id; }
    public String getCodigoComponente() { return codigoComponente; }
    public String getDescricaoComponente() { return descricaoComponente; }
    public Integer getStockActual() { return stockActual; }
    public Integer getStockMinimo() { return stockMinimo; }
    public String getDataAlerta() { return dataAlerta; }
}
