package br.com.fiap.profit.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    private String nome;

    @NotNull
    private String duracao;

    @NotBlank
    @Size(min = 50)
    private String descricao;

    @NotNull
    private String link;

    protected Curso(){}

    public Curso(Long id, String nome, String duracao, String descricao, String link) {
        this.id = id;
        this.nome = nome;
        this.duracao = duracao;
        this.descricao = descricao;
        this.link = link;
    }

    @Override
    public String toString() {
        return "Curso [id=" + id + ", nome=" + nome + ", duracao=" + duracao + ", descricao=" + descricao + ", link="
                + link + "]";
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDuracao() {
        return duracao;
    }
    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
