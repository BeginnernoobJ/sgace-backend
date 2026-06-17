package sistema_de_gestao.dtos;

import sistema_de_gestao.enums.TipoOperacao;

public class HistoricoDTO {
    private Long id;
    private String entidade;
    private String codigoEntidade;
    private String operacao;
    private String descricao;
    private String dataHora;
    private String nomeUtilizador;

    public HistoricoDTO() {}
    public HistoricoDTO(Long id, String entidade, String codigoEntidade,
                        String operacao, String descricao,
                        String dataHora, String nomeUtilizador) {
        this.id = id;
        this.entidade = entidade;
        this.codigoEntidade = codigoEntidade;
        this.operacao = operacao;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.nomeUtilizador = nomeUtilizador;
    }

    public Long getId() { return id; }
    public String getEntidade() { return entidade; }
    public String getCodigoEntidade() { return codigoEntidade; }
    public String getOperacao() { return operacao; }
    public String getDescricao() { return descricao; }
    public String getDataHora() { return dataHora; }
    public String getNomeUtilizador() { return nomeUtilizador; }
}