package com.alura.literatura.repository;

import com.alura.literatura.model.BookBaseDatos;
import com.alura.literatura.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Anotación @Repository para indicar que esta interfaz es un componente de acceso a datos de Spring
@Repository
public interface Book extends JpaRepository<BookBaseDatos, Long> {

    // Método para encontrar un libro por su título, devolviendo un Optional
    Optional<BookBaseDatos> findByTitulo(String titulo);

    // Método para encontrar una lista de libros por su idioma
    List<BookBaseDatos> findByIdioma(Language language);
}

