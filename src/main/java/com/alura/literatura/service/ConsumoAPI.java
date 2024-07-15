package com.alura.literatura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// Clase para consumir APIs
public class ConsumoAPI {

    // Método para obtener datos desde una URL
    public String obtenerDatos(String url) {
        // Crear una instancia de HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Construir una solicitud HTTP con la URL proporcionada
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        // Variable para almacenar la respuesta HTTP
        HttpResponse<String> response = null;

        try {
            // Enviar la solicitud y obtener la respuesta como cadena
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            // Manejar excepción de entrada/salida lanzando RuntimeException
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            // Manejar excepción de interrupción lanzando RuntimeException
            throw new RuntimeException(e);
        }

        // Obtener el cuerpo de la respuesta (JSON) como cadena
        String json = response.body();
        return json;
    }
}

