package ventanas;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;

public class JFMPantallaInicio extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFMPantallaInicio frame = new JFMPantallaInicio();
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
    public JFMPantallaInicio() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 700);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(62, 95, 138));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnUsuarios = new JButton("Usuarios");
        btnUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentanaUsuarios();
                dispose();
            }
        });

        btnUsuarios.setBounds(66, 74, 130, 23);
        contentPane.add(btnUsuarios);


        JButton btnLibros = new JButton("Libros");
        btnLibros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFMLibros frameLibros = new JFMLibros();
                frameLibros.setVisible(true);
                dispose();
            }
        });
        btnLibros.setBounds(66, 123, 130, 23);
        contentPane.add(btnLibros);

        JButton btnPrestamos = new JButton("Prestamos");
        btnPrestamos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFMPrestamos frameAgregarPrestamo = new JFMPrestamos();
                frameAgregarPrestamo.setVisible(true);
                dispose();
            }
        });
        btnPrestamos.setBounds(66, 177, 130, 23);
        contentPane.add(btnPrestamos);

        JButton btnDevolucion = new JButton("Devolucion");
        btnDevolucion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFMDevolucion frameDevolucion = new JFMDevolucion();
                frameDevolucion.setVisible(true);
                dispose();
            }
        });
        btnDevolucion.setBounds(66, 231, 130, 23);
        contentPane.add(btnDevolucion);

        JButton btnReservas = new JButton("Reservas");
        btnReservas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFMReservas frameReservas = new JFMReservas();
                frameReservas.setVisible(true);
                dispose();
            }
        });
        btnReservas.setBounds(66, 279, 130, 23);
        contentPane.add(btnReservas);

        JButton btnNewButton = new JButton("Salir");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnNewButton.setBounds(860, 610, 89, 23);
        contentPane.add(btnNewButton);
    }

    private void abrirVentanaUsuarios() {
        JFMUsuarios frameUsuarios = new JFMUsuarios();
        frameUsuarios.setVisible(true);
    }
}