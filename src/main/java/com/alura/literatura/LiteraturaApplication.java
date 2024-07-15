package com.alura.literatura;

import com.alura.literatura.principal.Principal;
import com.alura.literatura.repository.Author;
import com.alura.literatura.repository.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// Clase principal anotada con @SpringBootApplication para indicar que es una aplicación Spring Boot
@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {
	// Inyectar automáticamente instancias de Author y Book usando la anotación @Autowired de Spring
	@Autowired
	private Author author;
	@Autowired
	private Book book;

	// Método principal para iniciar la aplicación Spring Boot

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}
	public void run(String... args) throws Exception {

		Principal principal = new Principal(book, author);
		principal.mostrarMenuOpciones();
	}

}
