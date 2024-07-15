package com.alura.literatura.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Anotación para ignorar propiedades desconocidas durante la deserialización
@JsonIgnoreProperties(ignoreUnknown = true)
public record DataAuthor(
        // Anotaciones @JsonAlias para mapear los campos JSON a los campos del record
        @JsonAlias("name") String nombreAuthor,
        @JsonAlias("birth_year") Integer anioDeNac,
        @JsonAlias("death_year") Integer anioDeFall
) {
}

