package ventanas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
public class JFMDevolucion extends JFrame {
    private JTable tablePrestamo;

    public JFMDevolucion() {
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
        		"Usuario", "Libros Pendientes", "Fecha devolucion", "Multa"
        	}
        ));
        scrollPane.setViewportView(tablePrestamo);

        JLabel lblPrestamo = new JLabel("Devolucion");
        lblPrestamo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblPrestamo.setBounds(51, 64, 184, 14);
        getContentPane().add(lblPrestamo);

        JButton btnAgregar = new JButton("Agregar devolucion");
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
            	boolean editable = !tablePrestamo.isEditing();
                tablePrestamo.setEnabled(editable);
            }
        });
        btnEditar.setBounds(247, 581, 150, 23);
        getContentPane().add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar");
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
        btnEliminar.setBounds(637, 581, 150, 23);
        getContentPane().add(btnEliminar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		tablePrestamo.setEnabled(false);
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
        DefaultTableModel model = (DefaultTableModel) tablePrestamo.getModel();
        model.addRow(new Object[]{"Nuevo CI", "Nuevo ISBM", "Nuevo Estado", "Nueva Fecha prestada", "Nueva Fecha devolucion", "Nueva Multa"});
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JFMDevolucion frame = new JFMDevolucion();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
