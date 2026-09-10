package br.edu.ifsp.biblioteca.domain;

import jdk.internal.editor.external.ExternalEditor;

import java.util.ArrayList;
import java.util.List;

public class Livro {
    private Long id;
    private String isbn;
    private String titulo;
    private Integer anoPublicacao;
    private List<Exemplar> listaDeExeplares;
    private List<Autor> autores = new ArrayList<>();

    public Livro(String isbn, String titulo, Integer anoPublicacao){
        this.isbn = isbn;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.listaDeExeplares = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getIsbn(){
        return isbn;
    }

    public String getTitulo(){
        return titulo;
    }

    public Integer getAnoPublicacao(){
        return anoPublicacao;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setIsbn(String isbn){
        this.isbn = isbn;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public void setAnoPublicacao(Integer anoPublicacao){
        this.anoPublicacao = anoPublicacao;
    }

    public void adicionarExemplar(Exemplar item){
        this.listaDeExeplares.add(item);
    }

    public void adicionarAutor(Autor autor){
        this.autores.add(autor);
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", anoPublicacao=" + anoPublicacao +
                '}';
    }
}
