package ventanas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
public class JFMMultasYSanciones extends JFrame {
    private JTable tableMultasYSanciones;

    public JFMMultasYSanciones() {
        getContentPane().setBackground(new Color(62, 95, 138));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 700);
        getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(48, 122, 800, 400);
        getContentPane().add(scrollPane);

        tableMultasYSanciones = new JTable();
        tableMultasYSanciones.setEnabled(false);
        tableMultasYSanciones.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "CI usuario", "ISBN Libro", "Fecha devolucion", "Sancion", "Multa"
                }
        ));
        scrollPane.setViewportView(tableMultasYSanciones);

        JLabel lblPrestamo = new JLabel("Multas y sanciones");
        lblPrestamo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblPrestamo.setBounds(51, 64, 298, 14);
        getContentPane().add(lblPrestamo);

        JButton btnAgregar = new JButton("Agregar multa");
        btnAgregar.setBounds(60, 581, 150, 23);
        getContentPane().add(btnAgregar);
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarFila();
            }
        });

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean editable = !tableMultasYSanciones.isEditing();
                tableMultasYSanciones.setEnabled(editable);
            }
        });
        btnEditar.setBounds(247, 581, 150, 23);
        getContentPane().add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tableMultasYSanciones.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    DefaultTableModel model = (DefaultTableModel) tableMultasYSanciones.getModel();
                    model.removeRow(filaSeleccionada);
                } else {
                    JOptionPane.showInternalConfirmDialog(null, "Selecciona una fila para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnEliminar.setBounds(637, 581, 150, 23);
        getContentPane().add(btnEliminar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tableMultasYSanciones.setEnabled(false);
            }
        });
        btnActualizar.setBounds(452, 581, 150, 23);
        getContentPane().add(btnActualizar);

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
    }

    private void agregarFila() {
        DefaultTableModel model = (DefaultTableModel) tableMultasYSanciones.getModel();
        model.addRow(new Object[]{"Nuevo CI", "Nuevo ISBM", "Nuevo Estado", "Nueva Fecha prestada", "Nueva Fecha devolucion", "Nueva Multa"});
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JFMMultasYSanciones frame = new JFMMultasYSanciones();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}