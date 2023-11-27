package ventanas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;

public class panelLibrito extends JPanel {
	
	public panelLibrito(Libro libro) {
		setLayout(null);
		Font font1= new Font("Times New Roman", Font.BOLD, 18);
		Font font2= new Font("Times New Roman", Font.PLAIN, 15);
		JLabel labelTitulo = new JLabel("Título: " + libro.getTitulo());
		labelTitulo.setFont(font1);
		labelTitulo.setBounds(294, 34, 505, 28);
		add(labelTitulo);
        JLabel labelISBN = new JLabel("ISBN: " + libro.getIsbn());
        labelISBN.setFont(font2);
        labelISBN.setBounds(294, 218, 180, 14);
        add(labelISBN);
        JLabel labelAutor = new JLabel("Autor: " + libro.getAutor());
        labelAutor.setFont(font2);
        labelAutor.setBounds(294, 87, 250, 14);
        add(labelAutor);
        JLabel labelGenero = new JLabel("Género: " + libro.getGenero());
        labelGenero.setBounds(294, 135, 180, 14);
        labelGenero.setFont(font2);
        add(labelGenero);
        JLabel labelAnio = new JLabel("Año publicación: " + libro.getAnioPublicacion());
        labelAnio.setBounds(294, 176, 227, 14);
        labelAnio.setFont(font2);
        add(labelAnio);
        JLabel labelFoto = new JLabel();
        labelFoto.setBounds(23, 23, 250, 250);
        labelFoto.setIcon(new ImageIcon(libro.getRuta()));
        add(labelFoto);
        
		
	}
	private List<Libro> obtenerListaLibrosDesdeArchivo(String nombreArchivo) {
        List<Libro> listaLibros = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Procesar cada línea del archivo y crear un objeto Libro
                // Puedes personalizar esta lógica según el formato real de tu archivo
                String isbn = extraerValor(linea, "ISBN:");
                String titulo = extraerValor(reader.readLine(), "Título:");
                String autor = extraerValor(reader.readLine(), "Autor:");
                String genero = extraerValor(reader.readLine(), "Género:");
                int anioPublicacion = Integer.parseInt(extraerValor(reader.readLine(), "Año de Publicación:"));
                int cantidad = Integer.parseInt(extraerValor(reader.readLine(), "Cantidad:"));
                String rutaImagen = extraerValor(reader.readLine(), "Ruta de la Imagen:");

                Libro libro = new Libro(isbn, titulo, autor, genero, anioPublicacion, cantidad, rutaImagen);
                listaLibros.add(libro);

                // Leer la línea en blanco
                reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaLibros;
    }
    private String extraerValor(String linea, String etiqueta) {
        return linea.substring(etiqueta.length()).trim();
    }
}
