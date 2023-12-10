package ventanas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
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
                        "Prestamo ID", "Usuario CI",  "Fecha devolucion",  "Estado", "Fecha devuelto", "Multa"
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
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablePrestamo.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) tablePrestamo.getModel();
                if (filaSeleccionada >= 0 && Objects.equals((String) model.getValueAt(filaSeleccionada, 4), "Sin devolver")) {

                    LocalDate obtenerFecha = LocalDate.now();
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String fechaActual = obtenerFecha.format(formato);
                    model.setValueAt("Devuelto", filaSeleccionada, 3);
                    model.setValueAt(fechaActual, filaSeleccionada, 4);
                    actualizarPrestamo("Prestamos.txt","Devuelto",filaSeleccionada+1);
                } else {
                    JOptionPane.showInternalConfirmDialog(null, "Selecciona un prestamo para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnActualizar.setBounds(452, 581, 150, 23);
        btnActualizar.setEnabled(false);
        getContentPane().add(btnActualizar);

        JButton btnEditar = new JButton("Seleccionar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!tablePrestamo.isEnabled()){
                    //boolean editable = !tablePrestamo.isEditing();
                    btnActualizar.setEnabled(true);
                    tablePrestamo.setEnabled(true);
                }
                else{
                    //boolean editable = !tablePrestamo.isEditing();
                    btnActualizar.setEnabled(false);
                    tablePrestamo.setEnabled(false);
                }

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

        cargarDatosDesdeArchivo("Prestamos.txt");
    }
    private void actualizarPrestamo(String rutaArchivo,  String nuevoTexto, int numeroDeLinea) {
        File archivo = new File(rutaArchivo);
        StringBuilder contenido = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int contador = 0;

            while ((linea = reader.readLine()) != null) {
                contador++;
                if (contador == (numeroDeLinea*8)-2) {
                    contenido.append("Estado: "+nuevoTexto).append(System.lineSeparator());
                } else {
                    contenido.append(linea).append(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) {
            escritor.write(contenido.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        actualizarFecha(rutaArchivo, numeroDeLinea);
    }
    private void actualizarFecha(String rutaArchivo, int numeroDeLinea){
        File archivo = new File(rutaArchivo);
        StringBuilder contenido = new StringBuilder();
        LocalDate fecha = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaActual = fecha.format(formatter);

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int contador = 0;

            while ((linea = reader.readLine()) != null) {
                contador++;
                if (contador == (numeroDeLinea*8)-1) {
                    contenido.append("Fecha devuelto: "+fechaActual).append(System.lineSeparator());
                } else {
                    contenido.append(linea).append(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) {
            escritor.write(contenido.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

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

                modelo.addRow(new Object[]{idStr, ciStr, fechaDevolucion, estado, fechaDevuelto, obtenerMulta(fechaDevolucion, fechaDevuelto)});

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

    private String obtenerMulta(String fechaDevolucion, String fechaDevuelto){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if(Objects.equals(fechaDevuelto, "Sin devolver")){
            LocalDate fecha = LocalDate.now();
            String fechaForm = fecha.format(formatter);
            LocalDate fechaActual = LocalDate.parse(fechaForm, formatter);
            LocalDate fechaDev = LocalDate.parse(fechaDevolucion, formatter);
            long diasDiferencia = ChronoUnit.DAYS.between(fechaDev, fechaActual);
            if((int)diasDiferencia>0){
                return ((int)diasDiferencia*20)+ " Bs.";
            }
            else return 0 + " Bs.";
        }
        else{
            LocalDate fechaDev1 = LocalDate.parse(fechaDevuelto, formatter);
            LocalDate fechaDev2 = LocalDate.parse(fechaDevolucion, formatter);
            long diasDiferencia = ChronoUnit.DAYS.between(fechaDev2, fechaDev1);
            if((int)diasDiferencia>0){
                return ((int)diasDiferencia*20)+ " Bs.";
            }
            else return 0 + " Bs.";
        }

    }

    private void agregarFila() {
        /*DefaultTableModel model = (DefaultTableModel) tablePrestamo.getModel();
        model.addRow(new Object[]{"Nuevo CI", "Nuevo ISBM", "Nuevo Estado", "Nueva Fecha prestada", "Nueva Fecha devolucion", "Nueva Multa"});*/
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