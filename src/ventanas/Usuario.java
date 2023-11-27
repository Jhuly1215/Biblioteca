package ventanas;

import java.util.HashMap;

public class Usuario {
	private int ci;
    private String nombreCompleto;
    private int celular;
    private boolean libroPrestado;
    private boolean deudaLibro;
    private boolean multaPendiente;

    public Usuario(int numeroCarnet, String nombreCompleto, int celular) {
        this.ci = numeroCarnet;
        this.nombreCompleto = nombreCompleto;
        this.celular = celular;
        this.libroPrestado = false;
        this.deudaLibro = false;
        this.multaPendiente = false;
    }
    public int getCI() {
    	return ci;
    }
    public void setCI(int n) {
    	this.ci=n;
    }
    public String getNombre() {
        return nombreCompleto;

    }

    public void setNombre(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public boolean isLibroPrestado() {
        return libroPrestado;
    }

    public void setLibroPrestado(boolean libroPrestado) {
        this.libroPrestado = libroPrestado;
    }

    public boolean isDeudaLibro() {
        return deudaLibro;
    }

    public void setDeudaLibro(boolean deudaLibro) {
        this.deudaLibro = deudaLibro;
    }

    public boolean isMultaPendiente() {
        return multaPendiente;
    }

    public void setMultaPendiente(boolean multaPendiente) {
        this.multaPendiente = multaPendiente;
    }

    public String toString() {
        return "Numero de carnet: " + ci + "\nNombre: " + nombreCompleto +
                "\nCelular: " + celular + "\nLibro prestado: " + libroPrestado + "\nDeuda de libro: " +
                deudaLibro + "\nMulta pendiente: " + multaPendiente;
    }
}
