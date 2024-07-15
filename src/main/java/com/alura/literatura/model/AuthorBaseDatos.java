package com.alura.literatura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class AuthorBaseDatos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    private Integer anioDeNac;
    private Integer anioDeFall;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BookBaseDatos> libros;

    // Constructor por defecto necesario para JPA
    public AuthorBaseDatos() {
    }

    // Constructor que utiliza un objeto DataAuthor para inicializar la entidad
    public AuthorBaseDatos(DataAuthor dataAuthor) {
        this.nombre = dataAuthor.nombreAuthor();
        this.anioDeNac = dataAuthor.anioDeNac();
        this.anioDeFall = dataAuthor.anioDeFall();
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnioDeNac() {
        return anioDeNac;
    }

    public void setAnioDeNac(Integer anioDeNac) {
        this.anioDeNac = anioDeNac;
    }

    public Integer getAnioDeFall() {
        return anioDeFall;
    }

    public void setAnioDeFall(Integer anioDeFall) {
        this.anioDeFall = anioDeFall;
    }

    public List<BookBaseDatos> getLibros() {
        return libros;
    }

    public void setLibros(List<BookBaseDatos> libros) {
        this.libros = libros;
    }

    // Método toString para representación textual del objeto
    @Override
    public String toString() {
        StringBuilder librosStr = new StringBuilder();
        librosStr.append("Libros: ");

        for (int i = 0; i < libros.size(); i++) {
            librosStr.append(libros.get(i).getTitulo());
            if (i < libros.size() - 1) {
                librosStr.append(", ");
            }
        }

        return String.format("----- Autor -----%n" +
                        "Nombre: %s%n" +
                        "%s%n" +
                        "Fecha de Nacimiento: %s%n" +
                        "Fecha de Fallecimiento: %s%n" +
                        "-----------------%n",
                nombre,
                librosStr.toString(),
                anioDeNac,
                anioDeFall);
    }
}

