package sistema_de_gestao.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fornecedor")
public class Fornecedor {

    @Id
    @Column(name = "cod_fornecedor")
    private String codigoFornecedor;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "localizacao")
    private String localizacao;

    @Column(name = "activo")
    private Boolean activo = true;

    public String getCodigoFornecedor() { return codigoFornecedor; }
    public void setCodigoFornecedor(String c) { this.codigoFornecedor = c; }
    public String getNome() { return nome; }
    public void setNome(String n) { this.nome = n; }
    public String getEmail() { return email; }
    public void setEmail(String e) { this.email = e; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String t) { this.telefone = t; }
    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String l) { this.localizacao = l; }
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean a) { this.activo = a; }
}