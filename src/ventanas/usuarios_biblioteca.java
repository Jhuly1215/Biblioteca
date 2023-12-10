package ventanas;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Collections;
public class usuarios_biblioteca {
	private static HashMap<Integer, Usuario> usuariosPorCI;
	private static HashMap<String, Usuario> usuariosPorNombre;
	List <Usuario> listaUsuarios;

	public usuarios_biblioteca(List<Usuario> usuarios ,List<Libro> libros) {
       
		this.usuariosPorCI = new HashMap<>();
	    this.usuariosPorNombre = new HashMap<>();
	    this.listaUsuarios = new LinkedList<>();
	    
        for (Usuario usuario : usuarios) {
            agregarUsuario(usuario);
        }
    }
	public void agregarUsuario(Usuario usuario) {
        usuariosPorCI.put(usuario.getCI(), usuario);
        usuariosPorNombre.put(usuario.getNombre(), usuario);
    
    }
	public List<Usuario> buscarPorCI(int ci) {
        List<Usuario> resultados = new ArrayList<>();
        usuariosPorCI.forEach((clave, usuario) -> {
            if (clave == ci) {
                resultados.add(usuario);
            }
        });

        return resultados;
    }

	public List<Usuario> buscarPorNombre(String nombre) {
        List<Usuario> resultados = new ArrayList<>();
        usuariosPorNombre.forEach((clave, usuario) -> {
            if (clave.equalsIgnoreCase(nombre)) {
                resultados.add(usuario);
            }
        });
        return resultados;
    }
    public static void main() {

        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, Usuario> bibliotecaUsuarios = new HashMap<>();
        //usuarios_biblioteca usuario_libro_pres = new usuarios_biblioteca(librosPorCodigoCompartidousuario);
        //Prestamos prestamos = new Prestamos(bibliotecaUsuarios,usuario_libro_pres.librosPorCodigoCompartidousuario);
        int opcion;
        do {
            System.out.println("\nMen� de opciones:");
            System.out.println("1. Ingresar nuevo usuario");
            System.out.println("2. Buscar y editar informaci�n de usuario");
            System.out.println("3. Borrar informaci�n de usuario");
            System.out.println("4. Salir");
            System.out.print("Ingrese el n�mero de opci�n: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    ingresarNuevoUsuario(scanner, bibliotecaUsuarios);
                    break;
                case 2:
                    buscarYEditarUsuario(scanner, bibliotecaUsuarios);
                    break;
                case 3:
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opci�n no v�lida. Int�ntelo de nuevo.");
                    break;
            }
        } while (opcion != 4);

        scanner.close();
    }

    private static void ingresarNuevoUsuario(Scanner scanner, HashMap<Integer, Usuario> bibliotecaUsuarios) {
        System.out.println("\nIngresar nuevo usuario:");
        System.out.print("Ingrese el n�mero de carnet: ");
        int numeroCarnet = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner
        System.out.print("Ingrese el nombre completo: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el n�mero de celular: ");
        int celular = scanner.nextInt();

        // Crear un nuevo usuario
        Usuario nuevoUsuario = new Usuario(numeroCarnet, nombre, celular);
        bibliotecaUsuarios.put(numeroCarnet, nuevoUsuario);

        System.out.println("Usuario agregado exitosamente.");
    }

    private static void buscarYEditarUsuario(Scanner scanner, HashMap<Integer, Usuario> bibliotecaUsuarios) {
        System.out.println("\nBuscar y editar informaci�n de usuario:");
        System.out.println("\n");
        System.out.print("Ingrese el carnet del usuario: ");
        int numeroCarnet = scanner.nextInt();
        scanner.nextLine();
        
        Usuario usuario = bibliotecaUsuarios.get(numeroCarnet);
        if (usuario != null) {
            System.out.println("Informaci�n del usuario:");
            System.out.println(usuario);
            System.out.println("\n");
            System.out.println("�Qu� informaci�n desea editar?");
            System.out.println("1. Libro prestado");
            System.out.println("2. Deuda de libro");
            System.out.println("3. Multa pendiente");
            System.out.println("4. Regresar al men� principal");
            System.out.print("Ingrese el n�mero de opci�n: ");
            int opcionEditar = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            switch (opcionEditar) {
                case 1:
                    System.out.print("�Se prest� alg�n libro? (S�/No): ");
                    boolean libroPrestado = scanner.nextLine().equalsIgnoreCase("S�");
                    usuario.setLibroPrestado(libroPrestado);
                    break;
                case 2:
                    System.out.print("�Debe alg�n libro? (S�/No): ");
                    boolean deudaLibro = scanner.nextLine().equalsIgnoreCase("S�");
                    usuario.setDeudaLibro(deudaLibro);
                    break;
                case 3:
                    System.out.print("�Debe pagar alguna multa? (S�/No): ");
                    boolean multaPendiente = scanner.nextLine().equalsIgnoreCase("S�");
                    usuario.setMultaPendiente(multaPendiente);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opci�n no v�lida. Volviendo al men� principal.");
                    break;
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }
    
}
