# Proyecto CHALLENGE LIBRERIA

Este proyecto es un desafío parte del programa Oracle Next Education y Alura Latam. El objetivo es experimentar directamente el papel de un desarrollador back-end en el día a día, creando una aplicación con conexión a una base de datos relacional, donde se tendrá la oportunidad de aplicar conceptos avanzados de Java y Spring, como el consumo de APIs externas, la persistencia de datos, entre otros.

## Tabla de Contenidos

- [Requisitos](#requisitos)
- [Funcionalidad](#funcionalidad)
- [Repositorios](#repositorios)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Estructura del Proyecto](#estructura-del-proyecto)

## Requisitos

Para ejecutar este proyecto necesitas:

- Java 11 o superior
- Spring Framework
- PostgreSQL

## Funcionalidad

El programa ejecuta las siguientes funcionalidades:

- **Buscar libro por título:** Realiza una búsqueda en la API externa y guarda el libro en la base de datos si es encontrado.
- **Mostrar libros registrados:** Lista todos los libros registrados en la base de datos.
- **Mostrar autores registrados:** Lista todos los autores registrados en la base de datos.
- **Mostrar autores vivos en un determinado año:** Lista los autores que vivieron en un año específico.
- **Listar libros por idioma:** Lista los libros registrados en la base de datos según su idioma.

## Repositorios

Los repositorios utilizados son:

- **LibroRepository:** Interfaz para las operaciones CRUD sobre los libros.
- **AutorRepository:** Interfaz para las operaciones CRUD sobre los autores y consultas personalizadas.

## Tecnologías Utilizadas

- Spring Framework
- PostgreSQL
- Streams y Lambdas en Java
- JPQL (Java Persistence Query Language)
- Consultas Derivadas (Derived Queries)
- Jackson para Deserialización
- IntelliJ
- Visual Studio Code

## Estructura del Proyecto

proyecto-challenge-libreria

├── src

│   ├── main

│   │   ├── java

│   │   │   └── com

│   │   │       └── ejemplo

│   │   │           └── libreria

│   │   │               ├── controller

│   │   │               ├── model

│   │   │               ├── repository

│   │   │               └── service

│   │   └── resources

│   │       ├── application.properties

│   │       └── static

│   └── test

│       └── java

│           └── com

│               └── ejemplo

│                   └── libreria

└── pom.xml


