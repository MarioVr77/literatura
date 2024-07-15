package com.alura.literatura.principal;

import com.alura.literatura.model.*;
import com.alura.literatura.repository.Author;
import com.alura.literatura.repository.Book;
import com.alura.literatura.service.ConsumoAPI;
import com.alura.literatura.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
public class Principal {

    // Instancia de Scanner para leer la entrada del usuario
    Scanner teclado = new Scanner(System.in);

    // Instancia de ConsumoAPI para obtener datos de la API
    private ConsumoAPI consumoAPI = new ConsumoAPI();

    // URL base de la API
    private static final String URL_BASE = "https://gutendex.com/books/?search=";

    // Instancia de ConvierteDatos para convertir JSON a objetos
    private ConvierteDatos conversorDatos = new ConvierteDatos();

    // Listas para almacenar libros y autores
    private List<BookBaseDatos> libros;
    private List<AuthorBaseDatos> autores;

    // Repositorios de libros y autores
    private Book book;
    private Author author;

    // Constructor que inyecta los repositorios usando @Autowired
    @Autowired
    public Principal(Book book, Author author) {
        this.book = book;
        this.author = author;
    }

    // Método para mostrar el menú de opciones y manejar la entrada del usuario

    public void mostrarMenuOpciones() {
        var opcion = -1;
        Scanner teclado = new Scanner(System.in);

        while (opcion != 0) {
            var menu = """
                1 - Buscar libro por título
                2 - Listar libros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos en un determinado año
                5 - Listar libros por idioma
                0 - Salir
                """;
            System.out.println(menu);
            System.out.println("Elija la opción a través de su número");

            // Validación de entrada
            while (!teclado.hasNextInt()) {
                System.out.println("Por favor, ingrese un número válido.");
                teclado.next(); // Limpiar el buffer de entrada
            }
            opcion = teclado.nextInt();
            teclado.nextLine(); // Limpiar el buffer de nueva línea

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Gracias por usar la aplicación");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
        teclado.close();
    }


    private Data getDatos() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese el nombre del libro que desea buscar: ");
        var nombreLibro = teclado.nextLine();

        try {
            // Reemplazar espacios en blanco con %20 en la URL
            var url = URL_BASE + nombreLibro.replace(" ", "%20");

            var json = consumoAPI.obtenerDatos(url);
            Data datos = conversorDatos.obtenerDatos(json, Data.class);
            return datos;
        } catch (Exception e) {
            System.out.println("Error al obtener los datos del libro: " + e.getMessage());
            return null;
        }
    }



    // Método para buscar un libro por su título
    private void buscarLibroPorTitulo() {
        Data datos = getDatos();
        if (datos != null && !datos.resultados().isEmpty()) {
            DataBook primerLibro = datos.resultados().get(0);

            BookBaseDatos libro = new BookBaseDatos(primerLibro);

            Optional<BookBaseDatos> libroExiste = book.findByTitulo(libro.getTitulo());
            if (libroExiste.isPresent()) {
                System.out.println("\nNo se puede registrar el mismo libro más de una vez\n");
            } else {
                if (!primerLibro.autor().isEmpty()) {
                    DataAuthor autor = primerLibro.autor().get(0);
                    AuthorBaseDatos autor1 = new AuthorBaseDatos(autor);
                    Optional<Author> autorOptional = author.findByNombre(autor1.getNombre());

                    if (autorOptional.isPresent()) {
                        AuthorBaseDatos autorExiste = (AuthorBaseDatos) autorOptional.get();
                        libro.setAutor(autorExiste);
                        book.save(libro);
                    } else {
                        AuthorBaseDatos autorNuevo = author.save(autor1);
                        libro.setAutor(autorNuevo);
                        book.save(libro);
                    }

                    Integer numeroDescargas = libro.getNumeroDeDescargas() != null ? libro.getNumeroDeDescargas() : 0;
                    System.out.println("----- Libro -----");
                    System.out.printf("Título: %s%nAutor: %s%nIdioma: %s%nNúmero de descargas: %s%n",
                            libro.getTitulo(), autor1.getNombre(), libro.getIdioma(), libro.getNumeroDeDescargas());
                    System.out.println("-----------------\n");
                } else {
                    System.out.println("Sin autor");
                }
            }
        } else {
            System.out.println("Libro no encontrado");
        }
    }

    // Método para listar todos los libros registrados
    private void listarLibrosRegistrados() {
        libros = book.findAll();
        libros.stream().forEach(System.out::println);
    }

    // Método para listar todos los autores registrados
    private void listarAutoresRegistrados() {
        autores = author.findAll();
        autores.stream().forEach(System.out::println);
    }

    // Método para listar autores vivos en un año específico
    private void listarAutoresVivos() {
        System.out.println("Ingrese el año vivo de autor(es) que deseas buscar ");
        var anio = teclado.nextInt();
        autores = author.listaAutoresVivos(anio);
        autores.stream().forEach(System.out::println);
    }

    // Método para listar libros por idioma
    private void listarLibrosPorIdioma() {
        String menuIdioma = """
                Ingrese el idioma para buscar los libros: 
                es- Español
                en- Ingles
                fr- Frances 
                pt- Portugues
                """;

        System.out.println(menuIdioma);
        String idiomaBuscado = teclado.nextLine();
        Language idioma = null;

        switch (idiomaBuscado) {
            case "es":
                idioma = Language.valueOf("ESPAÑOL");
                break;
            case "en":
                idioma = Language.valueOf("INGLES");
                break;
            case "fr":
                idioma = Language.valueOf("FRANCES");
                break;
            case "pt":
                idioma = Language.valueOf("PORTUGUES");
                break;
            default:
                System.out.println("Entrada inválida.");
                return;
        }
        buscarPorIdioma(idioma);
    }

    // Método para buscar libros por idioma específico
    private void buscarPorIdioma(Language language) {
        libros = book.findByIdioma(language);
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados");
        } else {
            libros.stream().forEach(System.out::println);
        }
    }
}
