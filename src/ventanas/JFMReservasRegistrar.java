package ventanas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class JFMReservasRegistrar extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txCI;
    private JTextField txCodigoLibro;
    private JTextField txFechareserva;
    private List<Usuario> usuariosRegistrados;
    private List<Libro> librosRegistrados;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    JFMReservasRegistrar frame = new JFMReservasRegistrar();
                    frame.setVisible(true);
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
    public JFMReservasRegistrar() {

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

        JLabel lblNewLabel = new JLabel("AÑADIR RESERVA");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(221, 45, 201, 31);
        panel_1.add(lblNewLabel);

        txCI = new JTextField();
        txCI.setBounds(327, 126, 178, 20);
        panel_1.add(txCI);
        txCI.setColumns(10);

        txCodigoLibro = new JTextField();
        txCodigoLibro.setBounds(327, 171, 178, 20);
        panel_1.add(txCodigoLibro);
        txCodigoLibro.setColumns(10);

        txFechareserva = new JTextField();
        txFechareserva.setBounds(327, 217, 178, 20);
        panel_1.add(txFechareserva);
        txFechareserva.setColumns(10);

        JLabel lblCI = new JLabel("Carnet de Identidad:");
        lblCI.setFont(new Font("Rockwell", Font.PLAIN, 18));
        lblCI.setBounds(125, 127, 178, 14);
        panel_1.add(lblCI);

        JLabel lblCodigolibro = new JLabel("Codigo Libo:");
        lblCodigolibro.setFont(new Font("Rockwell", Font.PLAIN, 18));
        lblCodigolibro.setBounds(189, 172, 114, 14);
        panel_1.add(lblCodigolibro);

        JLabel lblFechareserva = new JLabel("Fecha reserva(dd/mm/yyyy):");
        lblFechareserva.setFont(new Font("Rockwell", Font.BOLD, 18));
        lblFechareserva.setBounds(36, 218, 281, 14);
        panel_1.add(lblFechareserva);

        JButton btnReservar = new JButton("Agregar reserva");
        btnReservar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de registrar esta reserva?", "Confirmación de registro",
                        JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    guardarDatosEnArchivo();


                }
            }
        });
        btnReservar.setFont(new Font("Rockwell", Font.PLAIN, 18));
        btnReservar.setBounds(141, 309, 293, 31);
        panel_1.add(btnReservar);

        JButton btnNewButton = new JButton("Atrás");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFMReservas frame = new JFMReservas();
                frame.setVisible(true);
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
    private boolean validarFechas(String fechareserva) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        try {
            Date fechaReservaDate = dateFormat.parse(fechareserva);

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto. Utilice el formato dd/MM/yyyy.");
            return false;
        }

        return true;
    }
    private int obtenerId(String nombreArchivo){
        int id = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("PrID: ")) {
                    id++;
                    reader.readLine(); // Leer la línea de separación
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }

    private void guardarDatosEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Reservas.txt", true))) {
            int prId = obtenerId("Reservas.txt");
            int ci = Integer.parseInt(txCI.getText());
            String codigoLibro = txCodigoLibro.getText();
            String fechareserva = txFechareserva.getText();

            Usuario usuario = buscarUsuarioPorCI(Integer.parseInt(txCI.getText()));
            Libro libro = buscarLibroPorISBN(txCodigoLibro.getText());

            if (!validarFechas(fechareserva)) {
                return; // Si las fechas no son válidas, salir sin guardar
            }
            if (usuario != null && libro != null) {
                Reserva reserva = new Reserva(prId, usuario, libro, fechareserva);

                writer.write("PrID: " + prId + "\n");
                writer.write("CI: " + ci + "\n");
                writer.write("Codigo Libro: " + codigoLibro + "\n");
                writer.write("Fecha reserva: " + fechareserva + "\n");
                writer.write("------------------------------\n");

                JOptionPane.showMessageDialog(null, "Reserva registrada.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o libro no registrado. No se puede realizar la reserva.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void limpiarCampos() {
        txCI.setText("");
        txCodigoLibro.setText("");
        txFechareserva.setText("");
    }

}