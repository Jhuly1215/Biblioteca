package ventanas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Prestamos{
	private LinkedList<Prestamos> listaPrestamos;
	private int carnetUsuario;
    private String codigoLibro;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private String estado;
    public Prestamos(int carnetUsuario, String codigoLibro, Date fechaPrestamo, Date fechaDevolucion, String estado) {
        this.carnetUsuario = carnetUsuario;
        this.codigoLibro = codigoLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
    }
	private HashMap<Integer, Usuario> bibliotecaUsuariosCompartida;
    private HashMap<String, Libro> librosPorCodigoCompartido;
    public Prestamos(HashMap<Integer, Usuario> bibliotecaUsuarios, HashMap<String, Libro> librosPorCodigo) {
    	listaPrestamos = new LinkedList<>();
        this.bibliotecaUsuariosCompartida = bibliotecaUsuarios;
        this.librosPorCodigoCompartido = librosPorCodigo;
    }
    public void realizarPrestamo(Scanner scanner) {
        System.out.print("Ingrese el carnet del usuario: ");
        int carnetUsuario = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner

        System.out.print("Ingrese el c�digo del libro: ");
        String codigoLibro = scanner.nextLine();

        if (bibliotecaUsuariosCompartida.containsKey(carnetUsuario) && librosPorCodigoCompartido.containsKey(codigoLibro)) {
            System.out.print("Ingrese la fecha de pr�stamo (dd/MM/yyyy): ");
            String fechaPrestamoStr = scanner.nextLine();
            Date fechaPrestamo = parseFecha(fechaPrestamoStr);

            System.out.print("Ingrese la fecha de devoluci�n (dd/MM/yyyy): ");
            String fechaDevolucionStr = scanner.nextLine();
            Date fechaDevolucion = parseFecha(fechaDevolucionStr);

            System.out.print("Ingrese el estado (Prestado/Devuelto): ");
            String estado = scanner.nextLine();

            Prestamos nuevoPrestamo = new Prestamos(carnetUsuario, codigoLibro, fechaPrestamo, fechaDevolucion, estado);
            listaPrestamos.add(nuevoPrestamo);
            System.out.println("Pr�stamo registrado correctamente.");
        } else {
            System.out.println("El carnet de usuario o el código del libro no existen en la biblioteca.");
        }
    }
    private Date parseFecha(String fechaStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dateFormat.parse(fechaStr);
        } catch (ParseException e) {
            System.out.println("Formato de fecha incorrecto. Use el formato dd/MM/yyyy.");
            return null;
        }
    }
    public static void main(String[] args) {
        // Aqu� puedes crear instancias de Usuario, Libro y HashMaps
        HashMap<Integer, Usuario> usuarios = new HashMap<>();
        HashMap<String, Libro> libros = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        Prestamos prestamos = new Prestamos(usuarios, libros);
        prestamos.realizarPrestamo(scanner);
        scanner.close();
    }
}
