package ventanas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;
public class JFMReservas extends JFrame {
    private JTable tableReservas;
    private LinkedList<Reserva> listaReservas;
    private List<Usuario> usuariosRegistrados;
    private List<Libro> librosRegistrados;
    
    
    public JFMReservas() {
    	usuariosRegistrados = obtenerListaUsuariosDesdeArchivo("Usuarios.txt");
        librosRegistrados = obtenerListaLibrosDesdeArchivo("LibrosGuardados.txt");
        
        getContentPane().setBackground(new Color(62, 95, 138));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 700);
        getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(48, 122, 800, 400);
        getContentPane().add(scrollPane);

        tableReservas = new JTable();
        tableReservas.setFocusable(false);
        //tableReservas.setEnabled(false);
        tableReservas.setRowSelectionAllowed(true);
        tableReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tableReservas.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "Cod Reserva","Usuario", "Libro", "Fecha de reserva"
                }
        ));
        scrollPane.setViewportView(tableReservas);

        JLabel lblPrestamo = new JLabel("Reservas");
        lblPrestamo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblPrestamo.setBounds(51, 64, 101, 14);
        getContentPane().add(lblPrestamo);

        JButton btnAgregar = new JButton("Agregar reserva");
        btnAgregar.setBounds(60, 581, 150, 23);
        getContentPane().add(btnAgregar);
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFMReservasRegistrar frame=new JFMReservasRegistrar();
                frame.setVisible(true);
                dispose();
            }
        });

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tableReservas.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    int confirmacion = JOptionPane.showConfirmDialog(
                            null,
                            "¿Está seguro de eliminar esta reserva?",
                            "Confirmación de eliminación",
                            JOptionPane.YES_NO_OPTION);

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        // Obtener el PrID de la fila seleccionada
                        int prId = (int) tableReservas.getValueAt(filaSeleccionada, 0);

                        // Llamar al método eliminarReserva
                        eliminarReserva(prId);

                        // Actualizar la visualización de la tabla
                        cargarReservasDesdeArchivo("Reservas.txt");
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Seleccione una fila para eliminar",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnEliminar.setBounds(637, 581, 150, 23);
        getContentPane().add(btnEliminar);

        JButton btnAtras = new JButton("Atras");
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFMPantallaInicio pantallaInicio = new JFMPantallaInicio();
                pantallaInicio.setVisible(true);
                dispose();
            }
        });
        btnAtras.setBounds(860, 610, 89, 23);
        getContentPane().add(btnAtras);
        
        cargarReservasDesdeArchivo("Reservas.txt");
    }
    private void eliminarReserva(int prId) {
        // Obtener la lista de reservas actual
        List<Reserva> listaReservas = obtenerListaReservasDesdeArchivo("Reservas.txt");

        // Buscar la reserva con el ID proporcionado
        Optional<Reserva> reservaAEliminar = listaReservas.stream()
                .filter(reserva -> reserva.getCodigoReserva() == prId)
                .findFirst();

        // Si se encuentra la reserva, eliminarla de la lista
        reservaAEliminar.ifPresent(reserva -> listaReservas.remove(reserva));

        // Guardar la lista actualizada en el archivo
        guardarListaReservasEnArchivo(listaReservas, "Reservas.txt");
    }

    private void guardarListaReservasEnArchivo(List<Reserva> listaReservas, String nombreArchivo) {
        // Guardar la lista de reservas en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Reserva reserva : listaReservas) {
                writer.write("PrID: " + reserva.getCodigoReserva() + "\n");
                writer.write("CI: " + reserva.getUsuario().getCI() + "\n");
                writer.write("Codigo Libro: " + reserva.getLibro().getIsbn() + "\n");
                writer.write("Fecha reserva: " + reserva.getFechaReserva() + "\n");
                writer.write("------------------------------\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String extraerValor(String linea, String etiqueta) {
        return linea.substring(etiqueta.length()).trim();
    }
    private void cargarReservasDesdeArchivo(String nombreArchivo) {
        listaReservas = obtenerListaReservasDesdeArchivo(nombreArchivo);

        DefaultTableModel modelo = (DefaultTableModel) tableReservas.getModel();
        modelo.setRowCount(0); // Limpiar la tabla antes de cargar nuevos datos

        for (Reserva reserva : listaReservas) {
            Object[] fila = new Object[modelo.getColumnCount()];
            fila[0]=reserva.getCodigoReserva();
            fila[1] = reserva.getUsuario(); // CI
            fila[2] = reserva.getLibro(); // Nombre
            fila[3] = reserva.getFechaReserva(); // Celular
         

            modelo.addRow(fila);
        }
    }
    private LinkedList<Reserva> obtenerListaReservasDesdeArchivo(String nombreArchivo) {
        LinkedList<Reserva> listaReservas = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("PrID: ")) {
                    int prId = Integer.parseInt(extraerValor(linea, "PrID: "));
                    int ci = Integer.parseInt(extraerValor(reader.readLine(), "CI: "));
                    String codigoLibro = extraerValor(reader.readLine(), "Codigo Libro: ");
                    String fechaReserva = extraerValor(reader.readLine(), "Fecha reserva: ");

                    // Crear un objeto Reserva y agregarlo a la lista
                    Usuario usuario = buscarUsuarioPorCI(ci);
                    Libro libro = buscarLibroPorISBN(codigoLibro);
                    if (usuario != null && libro != null) {
                        Reserva reserva = new Reserva(prId, usuario, libro, fechaReserva);
                        listaReservas.add(reserva);
                    }

                    reader.readLine(); // Leer la línea de separación
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaReservas;
    }
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
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JFMReservas frame = new JFMReservas();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}