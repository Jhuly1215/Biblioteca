package ventanas;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JFMLibrosDescripcion extends JFrame {
	private JTextField textAutor;
	private JTextField textISBN;
	private JTextField textTitulo, textGenero;
	JComboBox cboxGenero;
	private JTextField textAnioPublicacion;
	private JTextField textCantidad;
	JLabel lblFoto;
	private Libro libroActual;
	JButton btnGuardar;

    public JFMLibrosDescripcion(Libro libro) {
    	this.libroActual = libro;
    	
    	getContentPane().setBackground(new Color(62, 95, 138));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 700);
        getContentPane().setLayout(null);
        
        //informacion del libro
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBounds(143, 50, 730, 549);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);
        
        Font font=new Font("Rockwell", Font.PLAIN, 18);
        JLabel lblTitulo = new JLabel("Titulo");
        lblTitulo.setFont(font);
        lblTitulo.setBounds(343, 77, 46, 14);
        panel_1.add(lblTitulo);
        
        textTitulo = new JTextField();
        textTitulo.setEditable(false);
        textTitulo.setBackground(SystemColor.controlHighlight);
        textTitulo.setColumns(10);
        textTitulo.setBounds(430, 70, 270, 30);
        panel_1.add(textTitulo);
        
        JLabel lblautor = new JLabel("Autor");
        lblautor.setFont(font);
        lblautor.setBounds(343, 128, 46, 14);
        panel_1.add(lblautor);
        
        textAutor = new JTextField();
        textAutor.setEditable(false);
        textAutor.setBackground(SystemColor.controlHighlight);
        textAutor.setBounds(430, 120, 270, 30);
        textAutor.setColumns(10);
        panel_1.add(textAutor);
        
        JLabel lblGenero = new JLabel("Genero");
        lblGenero.setBounds(343, 228, 67, 14);
        lblGenero.setFont(font);
        panel_1.add(lblGenero);
        
        
        cboxGenero = new JComboBox();
        cboxGenero.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        cboxGenero.setBounds(430, 226, 140, 22);
        
        cboxGenero.addItem("Biografía");
        cboxGenero.addItem("Novela");
        cboxGenero.addItem("Soneto");
        cboxGenero.addItem("Carta");
        cboxGenero.addItem("Ensayo");
        cboxGenero.addItem("Artículo científico");
        
        textGenero = new JTextField();
        textGenero.setEditable(false);
        textGenero.setBackground(SystemColor.controlHighlight);
        textGenero.setBounds(430, 222, 270, 30);
        textGenero.setColumns(10);
        panel_1.add(textGenero);
        
        
        JLabel lblISBN = new JLabel("ISBN");
        lblISBN.setBounds(343, 178, 46, 14);
        lblISBN.setFont(font);
        panel_1.add(lblISBN);
        
        textISBN = new JTextField();
        textISBN.setEditable(false);
        textISBN.setBackground(SystemColor.controlHighlight);
        textISBN.setBounds(430, 170, 270, 30);
        textISBN.setColumns(10);
        panel_1.add(textISBN);
        
        JLabel lblAnioPublicacion = new JLabel("Año");
        lblAnioPublicacion.setBounds(343, 282, 67, 14);
        lblAnioPublicacion.setFont(font);
        panel_1.add(lblAnioPublicacion);
        
        textAnioPublicacion = new JTextField();
        textAnioPublicacion.setEditable(false);
        textAnioPublicacion.setColumns(10);
        textAnioPublicacion.setBackground(SystemColor.controlHighlight);
        textAnioPublicacion.setBounds(430, 276, 134, 30);
        panel_1.add(textAnioPublicacion);
        
        JLabel lblCantidad = new JLabel("Cantidad");
        lblCantidad.setFont(new Font("Rockwell", Font.PLAIN, 18));
        lblCantidad.setBounds(343, 332, 77, 14);
        panel_1.add(lblCantidad);
        
        textCantidad = new JTextField();
        textCantidad.setEditable(false);
        textCantidad.setColumns(10);
        textCantidad.setBackground(SystemColor.controlHighlight);
        textCantidad.setBounds(430, 331, 134, 30);
        panel_1.add(textCantidad);
        
        textTitulo.setText(libro.getTitulo());
        textAutor.setText(libro.getAutor());
        textISBN.setText(libro.getIsbn());
        textGenero.setText(libro.getGenero());
        textCantidad.setText(String.valueOf(libro.getCantidad()));
        textAnioPublicacion.setText(String.valueOf(libro.getAnioPublicacion()));
        //textGenero.setText(libro.getGenero());
        
        lblFoto = new JLabel();
        lblFoto.setBounds(41, 80, 250, 250);
        lblFoto.setIcon(new ImageIcon(libro.getRuta()));
        panel_1.add(lblFoto);
        
        JButton btnAgregarFoto = new JButton("Subir foto");
        btnAgregarFoto.setBounds(202, 338, 89, 23);
        btnAgregarFoto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de Imagen", "jpg", "jpeg", "png", "gif","jfif"));

                int seleccion = fileChooser.showOpenDialog(null);

                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    // Obtener la ruta del archivo seleccionado
                    String rutaArchivo = fileChooser.getSelectedFile().getPath();

                    // Mostrar la imagen en el JLabel
                    lblFoto.setIcon(new ImageIcon(rutaArchivo));
                }
            }
        });
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(41, 480, 186, 55);
        btnGuardar.setVisible(false); // Inicialmente, el botón es invisible
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de guardar los cambios?", "Confirmación de guardado", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    eliminarLibro(libroActual.getIsbn());
                    guardarDatosEnArchivo();
                    limpiarCampos();
                    btnGuardar.setVisible(false);
                    textTitulo.setEditable(false);
                    textAutor.setEditable(false);
                    textCantidad.setEditable(false);
                    textAnioPublicacion.setEditable(false);
                    textISBN.setEditable(false);
                    cboxGenero.setVisible(false);
                    textGenero.setVisible(true);
                }
            }
        });
        panel_1.add(btnGuardar);
        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(41, 483, 186, 55);
        btnEditar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		btnGuardar.setVisible(true);
        		btnEditar.setVisible(false);
        		panel_1.add(btnAgregarFoto);
        		textTitulo.setEditable(true);
                textAutor.setEditable(true);
                textCantidad.setEditable(true);
                textAnioPublicacion.setEditable(true);
                textISBN.setEditable(false);
                textGenero.setVisible(false);
                remove(textGenero);
                
        		btnAgregarFoto.setVisible(true);
        		panel_1.add(cboxGenero);
        		cboxGenero.setVisible(true);
        	}
        });
        panel_1.add(btnEditar);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(620, 10, 100, 50);
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar este libro?", "Confirmación de eliminación",
                        JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    eliminarLibro(libroActual.getIsbn());
                    limpiarCampos();
                    JOptionPane.showMessageDialog(null, "Libro eliminado.");
                }
            }
        });
        panel_1.add(btnEliminar);

        
        JLabel lblDescricpcion = new JLabel("Descripción");
        lblDescricpcion.setBounds(100, 22, 147, 14);
        lblDescricpcion.setFont(new Font("Tahoma", Font.BOLD, 16));
        panel_1.add(lblDescricpcion);       
              
        JButton btnAtras = new JButton("Atras");
        btnAtras.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JFMLibros frameLibros = new JFMLibros();
		        frameLibros.setVisible(true);
                dispose();
        	}
        });
        btnAtras.setBounds(860, 610, 89, 23);
        getContentPane().add(btnAtras);
    }
    private void guardarDatosEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("LibrosGuardados.txt", true))) {
            // Obtener datos de los campos
            String titulo = textTitulo.getText();
            String autor = textAutor.getText();
            String genero = cboxGenero.getSelectedItem().toString();
            String isbn = textISBN.getText();
            int anioPublicacion = Integer.parseInt(textAnioPublicacion.getText());
            int cantidad = Integer.parseInt(textCantidad.getText());
            String rutaImagen = lblFoto.getIcon() != null ? lblFoto.getIcon().toString() : "";

            writer.write("ISBN: " + isbn + "\n");
            writer.write("Título: " + titulo + "\n");
            writer.write("Autor: " + autor + "\n");
            writer.write("Género: " + genero + "\n");
            writer.write("Año de Publicación: " + anioPublicacion + "\n");
            writer.write("Cantidad: " + cantidad + "\n");
            writer.write("Ruta de la Imagen: " + rutaImagen + "\n");
            writer.write("------------------------------\n");


            limpiarCampos();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void eliminarLibro(String isbn) {
        // Obtener la lista de libros actual
        List<Libro> listaLibros = obtenerListaLibrosDesdeArchivo("LibrosGuardados.txt");

        // Buscar el libro con el ISBN proporcionado
        Optional<Libro> libroAEliminar = listaLibros.stream()
                .filter(libro -> libro.getIsbn().equals(isbn))
                .findFirst();

        // Si se encuentra el libro, eliminarlo de la lista
        libroAEliminar.ifPresent(libro -> listaLibros.remove(libro));

        // Guardar la lista actualizada en el archivo
        guardarListaLibrosEnArchivo(listaLibros);
    }
    private String extraerValor(String linea, String etiqueta) {
        return linea.substring(etiqueta.length()).trim();
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
    
                
                reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaLibros;
    }

    private void guardarListaLibrosEnArchivo(List<Libro> listaLibros) {
        // Guardar la lista de libros en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("LibrosGuardados.txt"))) {
            for (Libro libro : listaLibros) {
                writer.write("ISBN: " + libro.getIsbn() + "\n");
                writer.write("Título: " + libro.getTitulo() + "\n");
                writer.write("Autor: " + libro.getAutor() + "\n");
                writer.write("Género: " + libro.getGenero() + "\n");
                writer.write("Año de Publicación: " + libro.getAnioPublicacion() + "\n");
                writer.write("Cantidad: " + libro.getCantidad() + "\n");
                writer.write("Ruta de la Imagen: " + libro.getRuta() + "\n");
                writer.write("------------------------------\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void limpiarCampos() {
    	dispose();
    	JFMLibros frame = new JFMLibros();
        frame.setVisible(true);
        revalidate();
        repaint();
    }
}
