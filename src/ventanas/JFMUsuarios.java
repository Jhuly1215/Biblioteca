package ventanas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JFMUsuarios extends JFrame {
    private JTable tableUsuario;
    private JTextField txBusqueda;
    private usuarios_biblioteca ubiblioteca;

    public JFMUsuarios() {
        getContentPane().setBackground(new Color(62, 95, 138));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 700);
        getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(51, 185, 861, 364);
        getContentPane().add(scrollPane);
        
        JRadioButton rdbtnCI = new JRadioButton("CI");
        rdbtnCI.setBounds(51, 147, 109, 23);
        getContentPane().add(rdbtnCI);
        
        JRadioButton rdbtnNombre = new JRadioButton("Nombre");
        rdbtnNombre.setBounds(182, 147, 109, 23);
        getContentPane().add(rdbtnNombre);
        
        ButtonGroup grupoRadioButtons = new ButtonGroup();
        grupoRadioButtons.add(rdbtnCI);
        grupoRadioButtons.add(rdbtnNombre);
        JButton btnMostrarTodo = new JButton("Mostrar todo");

        tableUsuario = new JTable();
        tableUsuario.setEnabled(true);
        tableUsuario.setRowSelectionAllowed(true);

        tableUsuario.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] { "CI", "Nombre", "Celular", "Libros Pendientes", "Multas pendientes" }
        ));
        tableUsuario.getColumnModel().getColumn(0).setPreferredWidth(90);
        tableUsuario.getColumnModel().getColumn(1).setPreferredWidth(110);
        tableUsuario.getColumnModel().getColumn(2).setPreferredWidth(50);
        tableUsuario.getColumnModel().getColumn(3).setPreferredWidth(50);
        tableUsuario.getColumnModel().getColumn(4).setPreferredWidth(50);
        scrollPane.setViewportView(tableUsuario);

        JLabel lblUsuarios = new JLabel("Usuarios");
        lblUsuarios.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblUsuarios.setBounds(51, 64, 184, 14);
        getContentPane().add(lblUsuarios);

        JButton btnAgregar = new JButton("Agregar usuarios");
        btnAgregar.setBounds(60, 572, 150, 45);
        getContentPane().add(btnAgregar);
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFMUsuariosRegistrar frame = new JFMUsuariosRegistrar();
                frame.setVisible(true);
                dispose();
            }
        });

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int filaSeleccionada = tableUsuario.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    int confirmacion = JOptionPane.showConfirmDialog(
                            null,
                            "¿Está seguro de eliminar este usuario?",
                            "Confirmación de eliminación",
                            JOptionPane.YES_NO_OPTION);

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        // Obtener el CI del usuario seleccionado
                        int ciUsuario = (int) tableUsuario.getValueAt(filaSeleccionada, 0);
                        
                        // Llamar al método eliminarUsuario
                        eliminarUsuario(ciUsuario);
                        
                        // Actualizar la visualización de la tabla
                        cargarDatosDesdeArchivo("Usuarios.txt");
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
        btnEliminar.setBounds(236, 572, 150, 45);
        getContentPane().add(btnEliminar);
        
        tableUsuario.setDefaultEditor(Object.class, null);

        JButton btnAtras = new JButton("Atras");
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFMPantallaInicio pantallaInicio = new JFMPantallaInicio();
                pantallaInicio.setVisible(true);
                dispose();
            }
        });
        btnAtras.setBounds(860, 597, 89, 36);
        getContentPane().add(btnAtras);
        
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String valorBusqueda = txBusqueda.getText().trim();
                List<Usuario> resultados = new ArrayList<>();
                if (valorBusqueda.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese un término de búsqueda.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
                else if (rdbtnCI.isSelected() || rdbtnNombre.isSelected() ){
                	DefaultTableModel modeloFiltrado=new DefaultTableModel(new String[] {"CI", "Nombre", "Celular", "Libros Pendientes", "Multas pendientes" },0);
                	if (rdbtnCI.isSelected()) {
                		int cii = Integer.parseInt(valorBusqueda);
                        resultados.addAll(ubiblioteca.buscarPorCI(cii));
                        //DefaultTableModel filtrado=new DefaultTableModel(new Object[] {resultados.get(0),resultados.get(1),resultados.get(2),resultados.get(3),resultados.get(4)});
                        
                    }
                    if (rdbtnNombre.isSelected()) {
                        resultados.addAll(ubiblioteca.buscarPorNombre(valorBusqueda));
                    }
                   
                    
                    		
                } else {
                   JOptionPane.showMessageDialog(null, "Seleccionar criterio a buscar.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }
        });
        btnBuscar.setBounds(51, 89, 105, 36);
        getContentPane().add(btnBuscar);
        
        txBusqueda = new JTextField();
        txBusqueda.setBounds(166, 90, 746, 35);
        getContentPane().add(txBusqueda);
        txBusqueda.setColumns(10);
        
        txBusqueda.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    btnBuscar.doClick();
                }
            }
        });
        
        
        
        btnMostrarTodo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cargarDatosDesdeArchivo("Usuarios.txt");
        	}
        });
        
        btnMostrarTodo.setBounds(767, 138, 145, 36);
        getContentPane().add(btnMostrarTodo);
        
        cargarDatosDesdeArchivo("Usuarios.txt");
    }
    

    private void eliminarUsuario(int ci) {
        List<Usuario> listaUsuarios = obtenerListaUsuariosDesdeArchivo("Usuarios.txt");

        // Buscar el usuario con el CI proporcionado
        Optional<Usuario> usuarioAEliminar = listaUsuarios.stream()
                .filter(usuario -> usuario.getCI() == ci)
                .findFirst();

        // Si se encuentra el usuario, eliminarlo de la lista
        usuarioAEliminar.ifPresent(usuario -> listaUsuarios.remove(usuario));

        // Guardar la lista actualizada en el archivo
        guardarListaUsuariosEnArchivo(listaUsuarios);
    }

    private void guardarListaUsuariosEnArchivo(List<Usuario> listaUsuarios) {
        // Guardar la lista de usuarios en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Usuarios.txt"))) {
            for (Usuario usuario : listaUsuarios) {
                writer.write("CI: " + usuario.getCI() + "\n");
                writer.write("Nombre: " + usuario.getNombre() + "\n");
                writer.write("Celular: " + usuario.getCelular() + "\n");
                writer.write("------------------------------\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String extraerValor(String linea, String etiqueta) {
        return linea.substring(etiqueta.length()).trim();
    }

    private void cargarDatosDesdeArchivo(String nombreArchivo) {
        List<Usuario> listaUsuarios = obtenerListaUsuariosDesdeArchivo(nombreArchivo);

        DefaultTableModel modelo = (DefaultTableModel) tableUsuario.getModel();
        modelo.setRowCount(0); // Limpiar la tabla antes de cargar nuevos datos

        for (Usuario usuario : listaUsuarios) {
            Object[] fila = new Object[modelo.getColumnCount()];
            fila[0] = usuario.getCI(); // CI
            fila[1] = usuario.getNombre(); // Nombre
            fila[2] = usuario.getCelular(); // Celular
            fila[3] = ""; // Libros Pendientes (no hay información en el archivo)
            fila[4] = ""; // Multas pendientes (no hay información en el archivo)

            modelo.addRow(fila);
        }
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

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JFMUsuarios frame = new JFMUsuarios();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}


