package ventanas;

import javax.swing.JButton;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class JFMLibros extends JFrame {
    private JTextField txBusqueda;
    private JPanel panelTarjetas;
    private CatalogoLibros catalogo;

    public JFMLibros() {
        //panel de libros

        panelTarjetas = new JPanel(); // Cambié el nombre de la variable
        panelTarjetas.setBounds(28, 156, 915, 400);
        getContentPane().add(panelTarjetas);
        panelTarjetas.setLayout(new GridLayout(0, 1));
        
        JScrollPane scrollPane = new JScrollPane(panelTarjetas);
        scrollPane.setBounds(28, 156, 915, 400);
        getContentPane().add(scrollPane);
        
        JButton btnAgregarLibro = new JButton("Agregar libro");
        btnAgregarLibro.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		abrirVentanaRegistrar();
        		dispose();
        	}
        });
        btnAgregarLibro.setBounds(27, 567, 145, 40);
        getContentPane().add(btnAgregarLibro);
        
        JButton btnBack = new JButton("Atras");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JFMPantallaInicio pantallaInicio = new JFMPantallaInicio();
        		pantallaInicio.setVisible(true);
        		dispose();
        	}
        });
        btnBack.setBounds(860, 610, 89, 23);
        getContentPane().add(btnBack);

        //para sacar los datos del libro
        List<Libro> listaLibros = obtenerListaLibrosDesdeArchivo("LibrosGuardados.txt");
        cargarBotonesLibros(listaLibros);
       
    	//motor de busqueda
    	getContentPane().setBackground(new Color(62, 95, 138));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 700);
        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Libros");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setBounds(28, 40, 101, 14);
        getContentPane().add(lblNewLabel);

        JRadioButton rdbtnAutor = new JRadioButton("Autor");
        rdbtnAutor.setBounds(28, 126, 109, 23);
        getContentPane().add(rdbtnAutor);

        JRadioButton rdbtnTitulo = new JRadioButton("Titulo");
        rdbtnTitulo.setBounds(145, 126, 109, 23);
        getContentPane().add(rdbtnTitulo);
        
        JRadioButton rdbtnIsbn = new JRadioButton("ISBN");
        rdbtnIsbn.setBounds(261, 126, 109, 23);
        getContentPane().add(rdbtnIsbn);
        
        JRadioButton rdbtnGenero = new JRadioButton("Genero");
        rdbtnGenero.setBounds(380, 126, 109, 23);
        getContentPane().add(rdbtnGenero);
        
        ButtonGroup grupoRadioButtons = new ButtonGroup();
        grupoRadioButtons.add(rdbtnAutor);
        grupoRadioButtons.add(rdbtnTitulo);
        grupoRadioButtons.add(rdbtnIsbn);
        grupoRadioButtons.add(rdbtnGenero);
        
        List<Libro> librosIniciales = obtenerListaLibrosDesdeArchivo("LibrosGuardados.txt");
        catalogo = new CatalogoLibros(librosIniciales);
       
        txBusqueda = new JTextField();
        txBusqueda.setBounds(139, 81, 247, 20);
        getContentPane().add(txBusqueda);
        txBusqueda.setColumns(10);
        
        JButton btnBuscar = new JButton("Buscar");
        
        txBusqueda.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    btnBuscar.doClick();
                }
            }
        });
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String valorBusqueda = txBusqueda.getText().trim();
                List<Libro> resultados = new ArrayList<>();
                if (valorBusqueda.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese un término de búsqueda.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
                else if (rdbtnAutor.isSelected() || rdbtnTitulo.isSelected() || rdbtnGenero.isSelected() || rdbtnIsbn.isSelected()) {
                	if (rdbtnAutor.isSelected()) {
                        resultados.addAll(catalogo.buscarPorAutor(valorBusqueda));
                    }
                    if (rdbtnTitulo.isSelected()) {
                        resultados.addAll(catalogo.buscarPorTitulo(valorBusqueda));
                    }
                    if (rdbtnGenero.isSelected()) {
                        resultados.addAll(catalogo.buscarPorGenero(valorBusqueda));
                    }
                    if (rdbtnIsbn.isSelected()) {
                        resultados.addAll(catalogo.buscarPorISBN(valorBusqueda));
                    }
                    panelTarjetas.removeAll();
                    cargarBotonesLibros(resultados);

                    panelTarjetas.revalidate();
                    panelTarjetas.repaint();
                } else {
                   JOptionPane.showMessageDialog(null, "Seleccionar criterio a buscar.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }
        });

        btnBuscar.setBounds(28, 80, 88, 23);
        

        getContentPane().add(btnBuscar);
        
        JButton btnMostrarTodo = new JButton("Mostrar todo");
        btnMostrarTodo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		panelTarjetas.removeAll();
        		cargarBotonesLibros(listaLibros);
        		panelTarjetas.revalidate();
                panelTarjetas.repaint();
        	}
        });
        
        btnMostrarTodo.setBounds(192, 567, 145, 40);
        getContentPane().add(btnMostrarTodo);
    }

    private void cargarBotonesLibros(List<Libro> listaLibros) {
        for (Libro libro : listaLibros) {
            JButton buttonLibro = new JButton();
            buttonLibro.setLayout(new BorderLayout());
            
            panelLibrito panelDetalles= new panelLibrito(libro);

            buttonLibro.add(panelDetalles, BorderLayout.CENTER);
            buttonLibro.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            // Establecer un tamaño preferido para el botón
            buttonLibro.setPreferredSize(new Dimension(590, 300));
            buttonLibro.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	dispose();
                	JFMLibrosDescripcion frameDescripcion = new JFMLibrosDescripcion(libro);
                    frameDescripcion.setVisible(true);
                    dispose();
                }
            });

            panelTarjetas.add(buttonLibro);
        }
    }

  
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
                panelTarjetas.removeAll();
                panelTarjetas.revalidate();
                panelTarjetas.repaint();
                // Leer la línea en blanco
                reader.readLine();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        cargarBotonesLibros(listaLibros);
        
        return listaLibros;
    }
    private String extraerValor(String linea, String etiqueta) {
        return linea.substring(etiqueta.length()).trim();
    }
    private void abrirVentanaRegistrar() {
        JFMLibrosRegistrar frameUsuariosRegistrar = new JFMLibrosRegistrar();
        frameUsuariosRegistrar.setVisible(true);
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
      
                JFMLibros frame = new JFMLibros();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}