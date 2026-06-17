package sistema_de_gestao.entities;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;
import sistema_de_gestao.enums.TipoMovimento;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimentostock")
public class MovimentoStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movimento_id")
    private Long mov_cod;

    // definir o tipo de movimento Entrada/Saida que foi criado como uma enum
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoMovimento tipo;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "data_movimento")
    private LocalDateTime dataMov = LocalDateTime.now();

    @Column(name = "observacao")
    private String observacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "componente_id")
    private Componente componente;

    public TipoMovimento getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimento tipo) {
        this.tipo = tipo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilizador_id")
    private User utilizador;

    public Long getMov_cod() {
        return mov_cod;
    }


    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getDataMov() {
        return dataMov;
    }

    public void setDataMov(LocalDateTime dataMov) {
        this.dataMov = dataMov;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Componente getComponente() {
        return componente;
    }

    public void setComponente(Componente componente) {
        this.componente = componente;
    }

    public User getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(User utilizador) {
        this.utilizador = utilizador;
    }
}
