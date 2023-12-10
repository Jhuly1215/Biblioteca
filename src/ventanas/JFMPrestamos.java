package ventanas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedWriter;

import java.io.FileWriter;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class JFMPrestamos extends JFrame {
    private JTable tablePrestamo;

    public JFMPrestamos() {
        getContentPane().setBackground(new Color(62, 95, 138));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 700);
        getContentPane().setLayout(null);



        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(48, 122, 800, 400);
        getContentPane().add(scrollPane);

        tablePrestamo = new JTable();
        tablePrestamo.setEnabled(false);
        tablePrestamo.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "PrID", "CI", "ISBM", "Estado", "Fecha prestada", "Fecha devolucion"
                }
        ));
        scrollPane.setViewportView(tablePrestamo);

        JLabel lblPrestamo = new JLabel("Prestamo");
        lblPrestamo.setBounds(51, 64, 101, 14);
        lblPrestamo.setFont(new Font("Tahoma", Font.BOLD, 20));
        getContentPane().add(lblPrestamo);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(247, 581, 150, 23);
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean editable = !tablePrestamo.isEditing();
                tablePrestamo.setEnabled(editable);
            }
        });
        getContentPane().add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(637, 581, 150, 23);
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablePrestamo.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    DefaultTableModel model = (DefaultTableModel) tablePrestamo.getModel();
                    model.removeRow(filaSeleccionada);
                } else {
                    JOptionPane.showInternalConfirmDialog(null, "Selecciona una fila para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        getContentPane().add(btnEliminar);

        JButton btnAtras = new JButton("Atras");
        btnAtras.setBounds(847, 610, 89, 23);
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFMPantallaInicio pantallaInicio = new JFMPantallaInicio();
                pantallaInicio.setVisible(true);
                dispose();
            }
        });
        getContentPane().add(btnAtras);

        JButton btnNewButton = new JButton("Agregar Prestamo");
        btnNewButton.setBounds(48, 581, 154, 23);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFMAgregarPrestamo frame = new JFMAgregarPrestamo();
                frame.setVisible(true);
                dispose();
            }
        });
        getContentPane().add(btnNewButton);

        cargarDatosDesdeArchivo("Prestamos.txt");

    }
    private void cargarDatosDesdeArchivo(String nombreArchivo) {
        DefaultTableModel modelo = (DefaultTableModel) tablePrestamo.getModel();
        modelo.setRowCount(0); // Limpiar la tabla antes de cargar nuevos datos

        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Procesar cada línea del archivo para obtener los datos
                String idStr = extraerValor(linea, "PrID: ");
                String ciStr = extraerValor(reader.readLine(), "CI: ");
                String isbnStr = extraerValor(reader.readLine(), "Codigo Libro: ");
                String fechaPrestamo = extraerValor(reader.readLine(), "Fecha prestamo: ");
                String fechaDevolucion = extraerValor(reader.readLine(), "Fecha devolucion: ");
                String estado = extraerValor(reader.readLine(), "Estado: ");
                String fechaDevuelto = extraerValor(reader.readLine(), "Fecha devuelto: ");

                modelo.addRow(new Object[]{idStr, ciStr, isbnStr, estado, fechaPrestamo, fechaDevolucion, ""});

                reader.readLine(); // Leer la línea de separación
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String extraerValor(String linea, String etiqueta) {
        if (linea.startsWith(etiqueta)) {
            return linea.substring(etiqueta.length()).trim();
        } else {
            return "";
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JFMPrestamos frame = new JFMPrestamos();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}