package br.com.fiap.profit.models;

import java.time.LocalTime;

public class Curso {

    private Integer id;
    private String nome;
    private String duracao;
    private String descricao;
    private String link;

    @Override
    public String toString() {
        return "Curso [id=" + id + ", nome=" + nome + ", duracao=" + duracao + ", descricao=" + descricao + ", link="
                + link + "]";
    }

    public Curso(Integer id, String nome, String duracao, String descricao, String link) {
        this.id = id;
        this.nome = nome;
        this.duracao = duracao;
        this.descricao = descricao;
        this.link = link;
    }

    public Curso(int id2, String nome2, String duracao2, String descricao2, String link2) {
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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

    

}
