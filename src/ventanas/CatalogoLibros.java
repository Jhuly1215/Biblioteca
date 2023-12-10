package ventanas;

import java.util.Collections;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;


public class CatalogoLibros {
    Map<String, Libro> librosPorTitulo;
    Map<String, Libro> librosPorAutor;
    Map<String, Libro> librosPorISBN;
    Map<String, Libro> librosPorGenero;
    Map<String, Integer> existencias;

    List<Libro> listaLibros;

    public CatalogoLibros(List<Libro> libros) {
        this.librosPorTitulo = new HashMap<>();
        this.librosPorAutor = new HashMap<>();
        this.librosPorISBN = new HashMap<>();
        this.librosPorGenero = new HashMap<>();
        this.listaLibros = new LinkedList<>();
        for (Libro libro : libros) {
            agregarLibro(libro);
        }
    }

    public void agregarLibro(Libro libro) {
        librosPorISBN.put(libro.getIsbn(), libro);
        librosPorTitulo.put(libro.getTitulo(), libro);
        librosPorAutor.put(libro.getAutor(), libro);
        librosPorGenero.put(libro.getGenero(), libro);
    }

    public List<Libro> buscarPorISBN(String isbn) {
        List<Libro> resultados = new ArrayList<>();
        librosPorISBN.forEach((clave, libro) -> {
            if (libro.getIsbn().equalsIgnoreCase(isbn)) {
                resultados.add(libro);
            }
        });

        return resultados;
    }
    public List<Libro> buscarPorTitulo(String titulo) {
        List<Libro> resultados = new ArrayList<>();

        librosPorTitulo.forEach((clave, libro) -> {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                resultados.add(libro);
            }
        });

        return resultados;
    }
    public List<Libro> buscarPorAutor(String autor) {
        List<Libro> resultados = new ArrayList<>();

        librosPorAutor.forEach((clave, libro) -> {
            if (libro.getAutor().equalsIgnoreCase(autor)) {
                resultados.add(libro);
            }
        });

        return resultados;
    }
    public List<Libro> buscarPorGenero(String genero) {
        List<Libro> resultados = new ArrayList<>();

        librosPorGenero.forEach((clave, libro) -> {
            if (libro.getGenero().equalsIgnoreCase(genero)) {
                resultados.add(libro);
            }
        });

        return resultados;
    }
    public List<Libro> buscarEnTodos(String valor) {
        List<Libro> resultados = new ArrayList<>();

        listaLibros.forEach(libro -> {
            if (libro.contieneValor(valor)) {
                resultados.add(libro);
            }
        });

        return resultados;
    }
    public void agregarExistencias(String codigoLibro, int cantidad) {
        Libro libro = librosPorISBN.get(codigoLibro);

        if (libro != null) {
            existencias.put(codigoLibro, existencias.getOrDefault(codigoLibro, 0) + cantidad);
            libro.devolverLibro(); // Ajustamos la cantidad de libros
            System.out.println("Existencias agregadas con éxito.");
        } else {
            System.out.println("Libro no encontrado.");
        }
    }
    public List<Libro> buscarLibrosPorAtributo(String atributo, String valor) {
        Map<String, Libro> mapaBusqueda = null;

        switch (atributo.toLowerCase()) {
            case "titulo":
                mapaBusqueda = librosPorTitulo;
                break;
            case "autor":
                mapaBusqueda = librosPorAutor;
                break;
            case "isbn":
                mapaBusqueda = librosPorISBN;
                break;
            case "genero":
                mapaBusqueda = librosPorGenero;
                break;
            default:
                System.out.println("Atributo no válido. Las opciones son: titulo, autor, isbn, genero.");
                return Collections.emptyList();
        }

        List<Libro> librosEncontrados = new ArrayList<>();
        mapaBusqueda.forEach((clave, libro) -> {
            if (libro.contieneValor(valor)) {
                librosEncontrados.add(libro);
            }
        });

        return librosEncontrados;
    }


    // Nuevo método para mostrar la disponibilidad de un libro
    public void mostrarDisponibilidad(String codigoLibro) {
        Libro libro = librosPorISBN.get(codigoLibro);

        if (libro != null) {
            int disponibles = existencias.getOrDefault(codigoLibro, 0);
            System.out.println("Disponibilidad del libro: " + (libro.disponible() ? "Disponible" : "No disponible"));
            System.out.println("Existencias totales: " + disponibles);
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

}