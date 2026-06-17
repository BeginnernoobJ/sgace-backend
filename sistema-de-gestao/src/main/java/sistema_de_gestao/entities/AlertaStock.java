package sistema_de_gestao.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "alert_stock")
public class AlertaStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alerta_id")
    private Long alertaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "componente_id")
    private Componente componente;

    @Column(name = "stock_momento")
    private Integer stockNow;

    @Column(name = "stock_minimo")
    private Integer stockMin;

    @Column(name = "data_alerta")
    private LocalDateTime alertData = LocalDateTime.now();

    @Column(name = "resolvido")
    private Boolean resolvido = false;

    @Column(name = "dataresolucao")
    private LocalDateTime dataresolucao;

    public Long getAlertaId() {
        return alertaId;
    }
    public Componente getComponente() {
        return componente;
    }

    public void setComponente(Componente componente) {
        this.componente = componente;
    }

    public Integer getStockNow() {
        return stockNow;
    }

    public void setStockNow(Integer stockNow) {
        this.stockNow = stockNow;
    }

    public Integer getStockMin() {
        return stockMin;
    }

    public void setStockMin(Integer stockMin) {
        this.stockMin = stockMin;
    }

    public LocalDateTime getAlertData() {
        return alertData;
    }

    public void setAlertData(LocalDateTime alertData) {
        this.alertData = alertData;
    }

    public Boolean getResolvido() {
        return resolvido;
    }

    public void setResolvido(Boolean resolvido) {
        this.resolvido = resolvido;
    }

    public LocalDateTime getDataresolucao() {
        return dataresolucao;
    }
    public void setDataresolucao(LocalDateTime dataresolucao) {
        this.dataresolucao = dataresolucao;
    }
}