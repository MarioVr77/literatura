package com.alura.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

// Anotación para ignorar propiedades desconocidas durante la deserialización
@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBook(
        // Anotaciones @JsonAlias para mapear los campos JSON a los campos del record
        @JsonAlias("id") Long id,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DataAuthor> autor,
        @JsonAlias("languages") List<Language> idioma,
        @JsonAlias("download_count") Integer numeroDeDescargas
) {
}

