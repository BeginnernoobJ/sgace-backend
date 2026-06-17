package sistema_de_gestao.dtos;

import sistema_de_gestao.enums.TipoMovimento;

public class MovimentoRecenteDTO {
    private Long id;
    private TipoMovimento tipo;
    private String codigoComponente;
    private String nomeComponente;
    private String descricaoComponente;
    private Integer quantidade;
    private String nomeUtilizador;
    private String dataHora;

    public MovimentoRecenteDTO(Long id, TipoMovimento tipo, String codigoComponente, String nomeComponente,
                               String descricaoComponente, Integer quantidade,
                               String nomeUtilizador, String dataHora) {
        this.id = id;
        this.tipo = tipo;
        this.codigoComponente = codigoComponente;
        this.nomeComponente = nomeComponente;
        this.descricaoComponente = descricaoComponente;
        this.quantidade = quantidade;
        this.nomeUtilizador = nomeUtilizador;
        this.dataHora = dataHora;
    }

    public Long getId() { return id; }
    public TipoMovimento getTipo() { return tipo; }
    public String getCodigoComponente() { return codigoComponente; }
    public String getNomeComponente(){ return nomeComponente; }
    public String getDescricaoComponente() { return descricaoComponente; }
    public Integer getQuantidade() { return quantidade; }
    public String getNomeUtilizador() { return nomeUtilizador; }
    public String getDataHora() { return dataHora; }
}
