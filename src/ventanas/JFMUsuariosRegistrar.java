package ventanas;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class JFMUsuariosRegistrar extends JFrame {
	private JTextField txCI;
	private JTextField txNombre;
	private JTextField txCelular;
	JButton btnRegistrar;

    public JFMUsuariosRegistrar() {
    	getContentPane().setBackground(new Color(62, 95, 138));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 700);
        getContentPane().setLayout(null);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(200, 100, 567, 396);
        panel_1.setLayout(null);
        getContentPane().add(panel_1);
        
        Font f= new Font("Rockwell", Font.PLAIN, 18);
        JLabel lblCI = new JLabel("Carnet");
        lblCI.setFont(f);
        lblCI.setBounds(57, 108, 77, 14);
        panel_1.add(lblCI);
        
        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(57, 168, 113, 14);
        lblNombre.setFont(f);
        panel_1.add(lblNombre);
        
        JLabel lblCelular = new JLabel("Celular");
        lblCelular.setBounds(57, 224, 113, 14);
        lblCelular.setFont(f);
        panel_1.add(lblCelular);
        
        txCI = new JTextField();
        txCI.setColumns(10);
        txCI.setBounds(200, 100, 300, 30);
        panel_1.add(txCI);
        
        txNombre = new JTextField();
        txNombre.setColumns(10);
        txNombre.setBounds(200, 160, 300, 30);
        panel_1.add(txNombre);
        
        txCelular = new JTextField();
        txCelular.setColumns(10);
        txCelular.setBounds(200, 216, 300, 30);
        panel_1.add(txCelular);
        
        txCI.addKeyListener(new EnterKeyListener());
        txNombre.addKeyListener(new EnterKeyListener());
        txCelular.addKeyListener(new EnterKeyListener());
        
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de registrar este usuario?", "Confirmación de registro",
                        JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                	guardarDatosEnArchivo();
                    JOptionPane.showMessageDialog(null, "Usuario registrado.");
                    
                }
        	}
        });
        JButton btnUsuarios = new JButton("Usuarios");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                abrirVentanaRegistrar();
            }
		});
        
        btnRegistrar.setBounds(182, 303, 230, 60);
        panel_1.add(btnRegistrar);
        
        JLabel lblRegistrarUsuario = new JLabel("Registrar Usuario");
        lblRegistrarUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        lblRegistrarUsuario.setBounds(55, 22, 147, 14);
        panel_1.add(lblRegistrarUsuario);
        
        JButton btnAtras = new JButton("Atras");
        btnAtras.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JFMUsuarios frameUsuarios = new JFMUsuarios();
                frameUsuarios.setVisible(true);
                dispose();
        	}
        });
        btnAtras.setBounds(860, 610, 89, 23);
        getContentPane().add(btnAtras);
    }
    private void guardarDatosEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Usuarios.txt", true))) {
            String nombre = txNombre.getText();
            int ci = Integer.parseInt(txCI.getText());
            int celular = Integer.parseInt	(txCelular.getText());

            writer.write("CI: " + ci + "\n");
            writer.write("Nombre: " + nombre + "\n");
            writer.write("Celular: " + celular + "\n");
            writer.write("------------------------------\n");


            limpiarCampos();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para limpiar los campos después de guardar
    private void limpiarCampos() {
        txNombre.setText("");
        txCI.setText("");
        txCelular.setText("");
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JFMUsuariosRegistrar frame = new JFMUsuariosRegistrar();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    private void abrirVentanaRegistrar() {
        JFMUsuariosRegistrar frameUsuariosRegistrar = new JFMUsuariosRegistrar();
        frameUsuariosRegistrar.setVisible(true);
    }
    private class EnterKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                btnRegistrar.doClick();
            }
        }
    }
}
