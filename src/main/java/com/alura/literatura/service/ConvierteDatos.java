package com.alura.literatura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// Clase que implementa la interfaz IConvierteDatos
public class ConvierteDatos implements IConvierteDatos{
    // Instancia de ObjectMapper para la conversión de JSON
    private ObjectMapper objectMapper = new ObjectMapper();
    // Método para convertir una cadena JSON a un objeto de la clase especificada
    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            // Utiliza ObjectMapper para leer el JSON y convertirlo a un objeto de la clase especificada
            return objectMapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            // Lanza una RuntimeException si ocurre un error al procesar el JSON
            throw new RuntimeException(e);
        }
    }
}
