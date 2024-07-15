package com.alura.literatura.model;

import jakarta.persistence.*;
@Entity
@Table(name="libros")
public class BookBaseDatos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private AuthorBaseDatos autor;

    @Enumerated(EnumType.STRING)
    private Language idioma;

    private Integer numeroDeDescargas;

    // Constructor por defecto necesario para JPA
    public BookBaseDatos() {
    }

    // Constructor que utiliza un objeto DataBook para inicializar la entidad
    public BookBaseDatos(DataBook dataBook) {
        this.titulo = dataBook.titulo();
        this.idioma = dataBook.idioma().get(0);
        this.numeroDeDescargas = dataBook.numeroDeDescargas();
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public AuthorBaseDatos getAutor() {
        return autor;
    }

    public void setAutor(AuthorBaseDatos autor) {
        this.autor = autor;
    }

    public Language getIdioma() {
        return idioma;
    }

    public void setIdioma(Language idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    // Método toString para representación textual del objeto
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("---------- Libro -----------\n");
        sb.append("Título: ").append(titulo).append("\n");
        sb.append("Autor: ").append(autor != null ? autor.getNombre() : "Desconocido").append("\n");
        sb.append("Idioma: ").append(idioma).append("\n");
        sb.append("Número de descargas: ").append(numeroDeDescargas).append("\n");
        sb.append("****************************\n");
        return sb.toString();
    }
}

