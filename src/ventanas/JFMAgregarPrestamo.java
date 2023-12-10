package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFMAgregarPrestamo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txCI;
	private JTextField txCodigoLibro;
	private JTextField txFechaprestamo;
	private JTextField txFechadevolucion;
	private List<Usuario> usuariosRegistrados;
    private List<Libro> librosRegistrados;
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFMAgregarPrestamo frame = new JFMAgregarPrestamo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFMAgregarPrestamo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(62, 95, 138));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usuariosRegistrados = obtenerListaUsuariosDesdeArchivo("Usuarios.txt");
        librosRegistrados = obtenerListaLibrosDesdeArchivo("LibrosGuardados.txt");
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(221, 131, 567, 396);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("AÑADIR PRESTAMO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(199, 11, 178, 14);
		panel_1.add(lblNewLabel);
		
		txCI = new JTextField();
		txCI.setBounds(334, 66, 140, 20);
		panel_1.add(txCI);
		txCI.setColumns(10);
		
		txCodigoLibro = new JTextField();
		txCodigoLibro.setBounds(334, 109, 140, 20);
		panel_1.add(txCodigoLibro);
		txCodigoLibro.setColumns(10);
		
		txFechaprestamo = new JTextField();
		txFechaprestamo.setBounds(334, 161, 140, 20);
		panel_1.add(txFechaprestamo);
		txFechaprestamo.setColumns(10);
		
		txFechadevolucion = new JTextField();
		txFechadevolucion.setBounds(334, 215, 140, 20);
		panel_1.add(txFechadevolucion);
		txFechadevolucion.setColumns(10);
		
		JLabel lblCI = new JLabel("Carnet de Identidad:");
		lblCI.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblCI.setBounds(69, 67, 178, 14);
		panel_1.add(lblCI);
		
		JLabel lblCodigolibro = new JLabel("Codigo Libo:");
		lblCodigolibro.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblCodigolibro.setBounds(93, 110, 166, 14);
		panel_1.add(lblCodigolibro);
		
		JLabel lblFechaprestamo = new JLabel("Fecha Prestamo(dd/mm/yyyy):");
		lblFechaprestamo.setFont(new Font("Rockwell", Font.BOLD, 18));
		lblFechaprestamo.setBounds(10, 162, 281, 14);
		panel_1.add(lblFechaprestamo);
		
		JLabel lblFechadevolucion = new JLabel("Fecha Devolución(dd/mm/yyyy):");
		lblFechadevolucion.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblFechadevolucion.setBounds(37, 216, 275, 14);
		panel_1.add(lblFechadevolucion);
		
		JButton btnagregarprestamo = new JButton("Agregar Prestamo");
		btnagregarprestamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de registrar este préstamo?", "Confirmación de registro",
                        JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                	guardarDatosEnArchivo();
                    JOptionPane.showMessageDialog(null, "Prestamo registrado.");
                    
                }
			}
		});
		btnagregarprestamo.setFont(new Font("Rockwell", Font.PLAIN, 18));
		btnagregarprestamo.setBounds(199, 340, 181, 31);
		panel_1.add(btnagregarprestamo);
		 
		JButton btnNewButton = new JButton("Atrás");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFMPrestamos framePrestamo = new JFMPrestamos();
                framePrestamo.setVisible(true);
                dispose();
			}
		});
		btnNewButton.setBounds(829, 592, 89, 23);
		contentPane.add(btnNewButton);
	}
	// Método para cargar usuarios desde el archivo
	private List<Usuario> obtenerListaUsuariosDesdeArchivo(String nombreArchivo) {
        List<Usuario> listaUsuario = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("CI: ")) {
                    int ci = Integer.parseInt(extraerValor(linea, "CI: "));
                    String nombre = extraerValor(reader.readLine(), "Nombre: ");
                    int celular = Integer.parseInt(extraerValor(reader.readLine(), "Celular: "));

                    Usuario usuario = new Usuario(ci, nombre, celular);
                    listaUsuario.add(usuario);

                    reader.readLine(); // Leer la línea de separación
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaUsuario;
    }
	 private String extraerValor(String linea, String etiqueta) {
	        return linea.substring(etiqueta.length()).trim();
	    }

    // Método para cargar libros desde el archivo
	 private List<Libro> obtenerListaLibrosDesdeArchivo(String nombreArchivo) {
	        List<Libro> listaLibros = new ArrayList<>();
	        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
	            String linea;
	            while ((linea = reader.readLine()) != null) {
	            
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
	// Método para buscar un usuario por CI
	    private Usuario buscarUsuarioPorCI(int ci) {
	        for (Usuario usuario : usuariosRegistrados) {
	            if (usuario.getCI() == ci) {
	                return usuario;
	            }
	        }
	        return null; // No se encontró el usuario
	    }

	    // Método para buscar un libro por código
	    private Libro buscarLibroPorISBN(String codigoLibro) {
	        for (Libro libro : librosRegistrados) {
	            if (libro.getIsbn().equals(codigoLibro)) {
	                return libro;
	            }
	        }
	        return null; // No se encontró el libro
	    }
	private void guardarDatosEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Prestamos.txt", true))) {
           
            int ci = Integer.parseInt(txCI.getText());
            String codigoLibro = txCodigoLibro.getText();
            String fechaprestamo = txFechaprestamo.getText();
            String fechadevolucion = txFechadevolucion.getText();
            String estado= "Pendiente";
            
            Usuario usuario = buscarUsuarioPorCI(Integer.parseInt(txCI.getText()));
            Libro libro = buscarLibroPorISBN(txCodigoLibro.getText());

            if (usuario != null && libro != null) {
            	writer.write("CI: " + ci + "\n");
                writer.write("Codigo Libro: " + codigoLibro + "\n");
                writer.write("Fecha prestamo: " + fechaprestamo + "\n");
                writer.write("Fecha devolucion: " + fechadevolucion + "\n");
                writer.write("Estado: " + estado + "\n");
                writer.write("------------------------------\n");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o libro no registrado. No se puede realizar el préstamo.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	private void limpiarCampos() {
        txCI.setText("");
        txCodigoLibro.setText("");
        txFechaprestamo.setText("");
        txFechadevolucion.setText("");
        
    }
}
