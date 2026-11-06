package br.edu.ifsp.biblioteca.domain;



import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String isbn;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(name = "ano_publicacao")
    private Integer anoPublicacao;

    @OneToMany(
            mappedBy = "livro",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch =  FetchType.LAZY
    )
    private List<Exemplar> listaDeExeplares = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores = new ArrayList<>();

    public Livro() {

    }

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

    public List<Autor> getAutores() {
        return this.autores;
    }

    public List<Exemplar> getListaDeExeplares(){
        return this.listaDeExeplares;
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

    public List<Exemplar> getExemplares() {
        return listaDeExeplares;
    }
}
