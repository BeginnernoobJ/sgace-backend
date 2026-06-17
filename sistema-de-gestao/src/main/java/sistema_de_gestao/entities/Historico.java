package sistema_de_gestao.entities;
import jakarta.persistence.*;
import sistema_de_gestao.enums.TipoOperacao;

import java.time.LocalDateTime;

@Entity
@Table(name = "hist_alteracao")
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alteracao_id")
    private Long alteracaoId;

    @Column(name = "entidade")
    private String entidade;

    @Column(name = "codigo_entidade")
    private String codEntidade;

    // tipo de operacao e um enum entao ainda vou pesquisar
    @Enumerated(EnumType.STRING)
    @Column(name = "operacao")
    private TipoOperacao operacao;

    @Column(name="descricao")
    private String descricao;

    @Column(name = "data")
    private LocalDateTime dataHistorico = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilizador_id")
    private User utilizador;

    public Long getAlteracaoId() {
        return alteracaoId;
    }
    public String getEntidade() {
        return entidade;
    }

    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }

    public String getCodEntidade() {
        return codEntidade;
    }

    public void setCodEntidade(String codEntidade) {
        this.codEntidade = codEntidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataHistorico() {
        return dataHistorico;
    }

    public void setDataHistorico(LocalDateTime dataHistorico) {
        this.dataHistorico = dataHistorico;
    }

    public TipoOperacao getOperacao() {
        return operacao;
    }

    public void setOperacao(TipoOperacao operacao) {
        this.operacao = operacao;
    }

    public User getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(User utilizador) {
        this.utilizador = utilizador;
    }
}
