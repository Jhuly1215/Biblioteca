package ventanas;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Prestamos{
	private LinkedList<Prestamos> listaPrestamos;
	HashMap<Integer, Usuario> usuariosPorCI;
	HashMap<String, Libro> librosPorISBN;
    private String fechaPrestamo;
    private String fechaDevolucion;
    private String estado;
    public Prestamos(List<Usuario> usuario,List<Libro> libros, String fechaPrestamo, String fechaDevolucion, String estado) {
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
    }
   
    public Prestamos(int ci, String fechaprestamo2, String fechadevolucion2, String estado2) {
		// TODO Auto-generated constructor stub
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
    public static void main() {
        // Aqu� puedes crear instancias de Usuario, Libro y HashMaps
        HashMap<Integer, Usuario> usuarios = new HashMap<>();
        HashMap<String, Libro> libros = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        //Prestamos prestamos = new Prestamos(usuarios, libros);
        scanner.close();
    }
}

    