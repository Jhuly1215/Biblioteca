package ventanas;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class JFMLibrosRegistrar extends JFrame {
    private JTextField textAutor;
    private JTextField textISBN;
    private JTextField textTitulo;
    JComboBox cboxGenero;
    private JTextField textAnioPublicacion;
    private JTextField textCantidad;
    JLabel lblFoto;
    public JFMLibrosRegistrar() {
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
        textTitulo.setBackground(SystemColor.controlHighlight);
        textTitulo.setColumns(10);
        textTitulo.setBounds(430, 70, 270, 30);
        panel_1.add(textTitulo);

        JLabel lblautor = new JLabel("Autor");
        lblautor.setFont(font);
        lblautor.setBounds(343, 128, 46, 14);
        panel_1.add(lblautor);

        textAutor = new JTextField();
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
        panel_1.add(cboxGenero);

        cboxGenero.addItem("Biografía");
        cboxGenero.addItem("Novela");
        cboxGenero.addItem("Soneto");
        cboxGenero.addItem("Carta");
        cboxGenero.addItem("Ensayo");
        cboxGenero.addItem("Artículo científico");


        JLabel lblISBN = new JLabel("ISBN");
        lblISBN.setBounds(343, 178, 46, 14);
        lblISBN.setFont(font);
        panel_1.add(lblISBN);

        textISBN = new JTextField();
        textISBN.setBackground(SystemColor.controlHighlight);
        textISBN.setBounds(430, 170, 270, 30);
        textISBN.setColumns(10);
        panel_1.add(textISBN);

        JLabel lblAnioPublicacion = new JLabel("Año");
        lblAnioPublicacion.setBounds(343, 282, 67, 14);
        lblAnioPublicacion.setFont(font);
        panel_1.add(lblAnioPublicacion);

        textAnioPublicacion = new JTextField();
        textAnioPublicacion.setColumns(10);
        textAnioPublicacion.setBackground(SystemColor.controlHighlight);
        textAnioPublicacion.setBounds(430, 276, 134, 30);
        panel_1.add(textAnioPublicacion);

        JLabel lblCantidad = new JLabel("Cantidad");
        lblCantidad.setFont(new Font("Rockwell", Font.PLAIN, 18));
        lblCantidad.setBounds(343, 332, 77, 14);
        panel_1.add(lblCantidad);

        textCantidad = new JTextField();
        textCantidad.setColumns(10);
        textCantidad.setBackground(SystemColor.controlHighlight);
        textCantidad.setBounds(430, 331, 134, 30);
        panel_1.add(textCantidad);

        lblFoto = new JLabel("FOTO");
        lblFoto.setBounds(41, 80, 250, 250);
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
        panel_1.add(btnAgregarFoto);

        //

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(271, 483, 186, 55);
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de registrar este libro?", "Confirmación de registro",
                        JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    guardarDatosEnArchivo();
                    JOptionPane.showMessageDialog(null, "Libro registrado.");

                }
            }
        });
        panel_1.add(btnRegistrar);

        JLabel lblRegistrar = new JLabel("Registrar Libro");
        lblRegistrar.setBounds(100, 22, 147, 14);
        lblRegistrar.setFont(new Font("Tahoma", Font.BOLD, 16));
        panel_1.add(lblRegistrar);

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

    // Método para limpiar los campos después de guardar
    private void limpiarCampos() {
        textTitulo.setText("");
        textAutor.setText("");
        cboxGenero.setSelectedIndex(0);
        textISBN.setText("");
        textAnioPublicacion.setText("");
        textCantidad.setText("");
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JFMLibrosRegistrar frame = new JFMLibrosRegistrar();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}