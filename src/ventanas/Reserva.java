package ventanas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reserva {
	private static int codigoReservaActual = 1;
	private int codigoReserva;
    private Usuario usuario;
    private Libro libro;
    private String  fechaReserva;

    public Reserva(int codigoReserva,Usuario usuario, Libro libro, String fechaReserva) {
        this.codigoReserva = codigoReservaActual++;
        this.usuario = usuario;
        this.libro = libro;
        this.fechaReserva = fechaReserva; // Fecha actual
    }

    public int getCodigoReserva() {
        return codigoReserva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }
}