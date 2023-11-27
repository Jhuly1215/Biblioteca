package ventanas;

public class Libro {
	private String isbn, titulo, autor, genero, ruta;
    private int anioPublicacion;
    private int cantidad;

    public Libro(String isbn, String titulo, String autor, String genero, int anioPublicacion, int cantidad, String ruta) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.anioPublicacion = anioPublicacion;
        this.cantidad = cantidad;
        this.ruta=ruta;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Código: " + isbn + "\n" +
               "Título: " + titulo + "\n" +
               "Autor: " + autor + "\n" +
               "Género: " + genero + "\n" +
               "Año de Publicación: " + anioPublicacion + "\n" +
               "Cantidad: " + cantidad + "\n" +
               "Disponible: " + (disponible() ? "Sí" : "No");
    }

    public boolean disponible() {
        return cantidad > 0;
    }

    public void prestarLibro() {
        if (disponible()) {
            cantidad--;
        } else {
            System.out.println("El libro no está disponible para préstamo.");
        }
    }

    public void devolverLibro() {
        cantidad++;
    }
    public boolean contieneValor(String valor) {
        // Verificar si algún atributo del libro contiene el valor de búsqueda
        return titulo.toLowerCase().contains(valor.toLowerCase()) ||autor.toLowerCase().contains(valor.toLowerCase()) || isbn.toLowerCase().contains(valor.toLowerCase()) ||genero.toLowerCase().contains(valor.toLowerCase());
    }
}


