package br.ufg.normas.modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "normas")
public class Norma implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private String descricao;

    @Column
    private String dtcadastro;

    @Column
    private String arquivo;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDtcadastro() {
        return dtcadastro;
    }

    public void setDtcadastro(String dtcadastro) {
        this.dtcadastro = dtcadastro;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Norma norma = (Norma) o;
        return Objects.equals(id, norma.id) &&
                Objects.equals(nome, norma.nome) &&
                Objects.equals(descricao, norma.descricao) &&
                Objects.equals(dtcadastro, norma.dtcadastro) &&
                Objects.equals(arquivo, norma.arquivo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nome, descricao, dtcadastro, arquivo);
    }
}
