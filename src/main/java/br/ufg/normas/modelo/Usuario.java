package br.ufg.normas.modelo;


import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq", initialValue = 1 ,allocationSize = 1)
@Table(name = "usuarios")
public class Usuario implements Serializable {

    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    private Long id;

    @Expose
    @Column
    private String nome;

    @Expose
    @Column
    private String sobrenome;

    @Expose
    @Column(nullable = false)
    private String email;

    @Expose
    @Column
    private String senha;

    @Expose
    private String confirmacaoSenha;

    @Expose
    @Column
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;

    @Expose
    @Temporal(TemporalType.DATE)
    @Column
    private Date dataInicioAdmin;

    @Expose
    @Temporal(TemporalType.DATE)
    @Column
    private Date dataFimAdmin;

    @Expose
    @Column
    private Situacao situacao = Situacao.ATIVO;

    @Expose
    @Column
    private TipoPapel papel = TipoPapel.USUARIO;



    /*

    private String tokenDesbloqueio;


    private String tokenRedefinirSenha;



    public boolean administrador = false;


    private int numTentativas = 0;
    */


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {

        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataInicioAdmin() {
        return dataInicioAdmin;
    }

    public void setDataInicioAdmin(Date dataInicioAdmin) {
        this.dataInicioAdmin = dataInicioAdmin;
    }

    public Date getDataFimAdmin() {
        return dataFimAdmin;
    }

    public void setDataFimAdmin(Date dataFimAdmin) {
        this.dataFimAdmin = dataFimAdmin;
    }


    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    public void setConfirmacaoSenha(String confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }

    public TipoPapel getPapel() {
        return papel;
    }

    public void setPapel(TipoPapel papel) {
        this.papel = papel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) &&
                Objects.equals(nome, usuario.nome) &&
                Objects.equals(sobrenome, usuario.sobrenome) &&
                Objects.equals(email, usuario.email) &&
                Objects.equals(senha, usuario.senha) &&
                //Objects.equals(confirmacaoSenha, usuario.confirmacaoSenha) &&
                Objects.equals(dataCadastro, usuario.dataCadastro) &&
                Objects.equals(dataInicioAdmin, usuario.dataInicioAdmin) &&
                Objects.equals(dataFimAdmin, usuario.dataFimAdmin) &&
                situacao == usuario.situacao;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nome, sobrenome, email, senha,  dataCadastro, dataInicioAdmin, dataFimAdmin, situacao);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", situacao=" + situacao +
                ", papel=" + papel +
                '}';
    }
}