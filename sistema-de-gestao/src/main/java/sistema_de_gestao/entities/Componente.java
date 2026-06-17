package sistema_de_gestao.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "componente")
public class Componente {

    @Id
    @Column(name = "cod_stock")
    private String codigoStock;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "preco_unit", nullable = false)
    private BigDecimal precoUnitario;

    @Column(name = "stock_existente")
    private Integer stockExistente = 0;

    @Column(name = "stock_minimo")
    private Integer stockMinimo = 0;

    @Column(name = "unidade")
    private String unidade;

    @Column(name = "localizacao_fisica")
    private String localizacaoFisica;

    @Column(name = "ativo")
    private Boolean activo = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    public String getCodigoStock() { return codigoStock; }
    public void setCodigoStock(String c) { this.codigoStock = c; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String d) { this.descricao = d; }
    public String getNome(){ return nome; }
    public void setNome(String nome){ this.nome = nome;}
    public BigDecimal getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(BigDecimal p) { this.precoUnitario = p; }
    public Integer getStockExistente() { return stockExistente; }
    public void setStockExistente(Integer s) { this.stockExistente = s; }
    public Integer getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(Integer s) { this.stockMinimo = s; }
    public String getUnidade() { return unidade; }
    public void setUnidade(String u) { this.unidade = u; }
    public String getLocalizacaoFisica() { return localizacaoFisica; }
    public void setLocalizacaoFisica(String l) { this.localizacaoFisica = l; }
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean a) { this.activo = a; }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria c) { this.categoria = c; }
    public Fornecedor getFornecedor() { return fornecedor; }
    public void setFornecedor(Fornecedor f) { this.fornecedor = f; }
}