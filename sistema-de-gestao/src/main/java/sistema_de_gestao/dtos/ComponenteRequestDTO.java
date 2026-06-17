package sistema_de_gestao.dtos;

public class ComponenteRequestDTO {
    private String codigoStock;
    private String nome;
    private String descricao;
    private java.math.BigDecimal precoUnitario;
    private Integer stockExistente;
    private Integer stockMinimo;
    private String unidade;
    private String localizacaoFisica;
    private Long categoriaId;
    private String codigoFornecedor;

    public String getCodigoStock() { return codigoStock; }
    public void setCodigoStock(String c) { this.codigoStock = c; }
    public void setNome(String nome ){ this.nome = nome; }
    public String getNome(){ return nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String d) { this.descricao = d; }
    public java.math.BigDecimal getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(java.math.BigDecimal p) { this.precoUnitario = p; }
    public Integer getStockExistente() { return stockExistente; }
    public void setStockExistente(Integer s) { this.stockExistente = s; }
    public Integer getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(Integer s) { this.stockMinimo = s; }
    public String getUnidade() { return unidade; }
    public void setUnidade(String u) { this.unidade = u; }
    public String getLocalizacaoFisica() { return localizacaoFisica; }
    public void setLocalizacaoFisica(String l) { this.localizacaoFisica = l; }
    public Long getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Long c) { this.categoriaId = c; }
    public String getCodigoFornecedor() { return codigoFornecedor; }
    public void setCodigoFornecedor(String c) { this.codigoFornecedor = c; }
}