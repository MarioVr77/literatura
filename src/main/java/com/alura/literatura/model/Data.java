package com.alura.literatura.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
// Anotación para ignorar propiedades desconocidas durante la deserialización
@JsonIgnoreProperties(ignoreUnknown = true)
public record Data(
        // Anotaciones @JsonAlias para mapear los campos JSON a los campos del record
        @JsonAlias("results") List<DataBook> resultados,
        @JsonAlias("count") Double total
) {
}

