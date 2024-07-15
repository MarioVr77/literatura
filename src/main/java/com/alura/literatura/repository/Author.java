package com.alura.literatura.repository;

import com.alura.literatura.model.AuthorBaseDatos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Anotación @Repository para indicar que esta interfaz es un componente de acceso a datos de Spring
@Repository
public interface Author extends JpaRepository<AuthorBaseDatos, Long> {

    // Método para encontrar un autor por su nombre, devolviendo un Optional
    Optional<Author> findByNombre(String nombre);

    // Consulta personalizada para obtener una lista de autores vivos en un año específico
    @Query("SELECT a FROM AuthorBaseDatos a WHERE a.anioDeNac <= :anio AND a.anioDeFall >= :anio")
    List<AuthorBaseDatos> listaAutoresVivos(Integer anio);
}

