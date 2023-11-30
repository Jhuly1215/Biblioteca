package ventanas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        		"CI", "ISBM", "Estado", "Fecha prestada", "Fecha devolucion", "Multa"
        	}
        ));
        scrollPane.setViewportView(tablePrestamo);

        JLabel lblPrestamo = new JLabel("Prestamo");
        lblPrestamo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblPrestamo.setBounds(51, 64, 101, 14);
        getContentPane().add(lblPrestamo);

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
        btnAtras.setBounds(847, 610, 89, 23);
        getContentPane().add(btnAtras);
        
        JButton btnNewButton = new JButton("Agregar Prestamo");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                    JFMAgregarPrestamo frame = new JFMAgregarPrestamo();
                    frame.setVisible(true);
                    dispose();
        	}
        });
        btnNewButton.setBounds(48, 581, 154, 23);
        getContentPane().add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Mostrar Todo");
        btnNewButton_1.setBounds(711, 64, 129, 42);
        getContentPane().add(btnNewButton_1);
        
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//cargarDatosDesdeArchivo("Prestamos.txt");
        	}
        });
        
        btnNewButton_1.setBounds(767, 138, 145, 36);
        getContentPane().add(btnNewButton_1);
        
        //cargarDatosDesdeArchivo("Prestamos.txt");
    }
    private String extraerValor(String linea, String etiqueta) {
        return linea.substring(etiqueta.length()).trim();
    }
    /*
    private void cargarDatosDesdeArchivo(String nombreArchivo) {
        List<Prestamos> listaPrestamos = obtenerListaPrestamosDesdeArchivo(nombreArchivo);

        DefaultTableModel modelo = (DefaultTableModel) tablePrestamo.getModel();
        modelo.setRowCount(0); // Limpiar la tabla antes de cargar nuevos datos

        for (Prestamos prestamo : listaPrestamos) {
            Object[] fila = new Object[modelo.getColumnCount()];
            fila[0] = prestamo.getCI(); // CI
            fila[1] = prestamo.getIBMS(); // IBMS
            fila[2] = prestamo.getFechaprestamo(); // Fechaprestamo
            fila[3] = prestamo.getFechadevolucion(); // Fechadevolucion
            fila[4] = prestamo.getestado; // Multas pendientes (no hay información en el archivo)

            modelo.addRow(fila);
        }
    }*/
    private List<Prestamos> obtenerListaPrestamosDesdeArchivo(String nombreArchivo) {
        List<Prestamos> listaPrestamo = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("CI: ")) {
                    int ci = Integer.parseInt(extraerValor(linea, "CI: "));
                    String fechaprestamo = extraerValor(reader.readLine(), "Fecha Prestamo: ");
                    String fechadevolucion = extraerValor(reader.readLine(), "Fecha Devolucion: ");
                    String estado = extraerValor(reader.readLine(), "Estado: ");

                    Prestamos prestamo = new Prestamos(ci, fechaprestamo, fechadevolucion,estado);
                    listaPrestamo.add(prestamo);

                    reader.readLine(); // Leer la línea de separación
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaPrestamo;
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

