package ventanas;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Prestamos {
    private LinkedList<Prestamos> listaPrestamos;
    private int PrID;
    private HashMap<Integer, Usuario> usuariosPorCI;
    private HashMap<String, Libro> librosPorISBN;
    private String fechaPrestamo;
    private String fechaDevolucion;
    private String estado;

    public Prestamos(int PrID,HashMap<Integer, Usuario> usuarios, HashMap<String, Libro> libros, String fechaPrestamo, String fechaDevolucion, String estado) {
        this.PrID = PrID;
        this.usuariosPorCI = usuarios;
        this.librosPorISBN = libros;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
    }
    public int getPrID(){ return PrID;}
    public Usuario getci() {
        // Lógica para obtener el Usuario (por ejemplo, el primer elemento del HashMap)
        if (!usuariosPorCI.isEmpty()) {
            return usuariosPorCI.values().iterator().next();
        } else {
            return null; // O manejar el caso en el que no haya ningún Usuario en el HashMap
        }
    }

    public Libro getcodigoLibro() {
        // Lógica para obtener el Libro (por ejemplo, el primer elemento del HashMap)
        if (!librosPorISBN.isEmpty()) {
            return librosPorISBN.values().iterator().next();
        } else {
            return null; // O manejar el caso en el que no haya ningún Libro en el HashMap
        }
    }

    public String getfechaPrestamo() {
        return fechaPrestamo;
    }

    public String getfechaDevolucion() {
        return fechaDevolucion;
    }

    public String getestado() {
        return estado;
    }

    public Prestamos(int prId, int ci, int codigoLibro, String fechaprestamo, String fechadevolucion, String estado2) {
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

