package sistema_de_gestao.dtos;

import java.math.BigDecimal;

public class ComponenteResponseDTO {
    private String codigoStock;
    private String nome;
    private String descricao;
    private BigDecimal precoUnitario;
    private Integer stockExistente;
    private Integer stockMinimo;
    private String unidade;
    private String localizacaoFisica;
    private Boolean activo;
    private String nomeCategoria;
    private Long categoriaId;
    private String nomeFornecedor;
    private String codigoFornecedor;

    public ComponenteResponseDTO(String codigoStock, String nome, String descricao,
                                  BigDecimal precoUnitario, Integer stockExistente,
                                  Integer stockMinimo, String unidade,
                                  String localizacaoFisica, Boolean activo,
                                  String nomeCategoria, Long categoriaId,
                                  String nomeFornecedor, String codigoFornecedor) {
        this.codigoStock = codigoStock;
        this.nome = nome;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.stockExistente = stockExistente;
        this.stockMinimo = stockMinimo;
        this.unidade = unidade;
        this.localizacaoFisica = localizacaoFisica;
        this.activo = activo;
        this.nomeCategoria = nomeCategoria;
        this.categoriaId = categoriaId;
        this.nomeFornecedor = nomeFornecedor;
        this.codigoFornecedor = codigoFornecedor;
    }

    public String getCodigoStock() { return codigoStock; }
    public String getNome(){ return nome;}
    public String getDescricao() { return descricao; }
    public BigDecimal getPrecoUnitario() { return precoUnitario; }
    public Integer getStockExistente() { return stockExistente; }
    public Integer getStockMinimo() { return stockMinimo; }
    public String getUnidade() { return unidade; }
    public String getLocalizacaoFisica() { return localizacaoFisica; }
    public Boolean getActivo() { return activo; }
    public String getNomeCategoria() { return nomeCategoria; }
    public Long getCategoriaId() { return categoriaId; }
    public String getNomeFornecedor() { return nomeFornecedor; }
    public String getCodigoFornecedor() { return codigoFornecedor; }
}