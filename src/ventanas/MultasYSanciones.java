package ventanas;

import java.util.HashMap;
import java.util.Map;

public class MultasYSanciones {
    private Map<String, PenalizacionUsuario> penalizacionesPorUsuario;

    public MultasYSanciones() {
        penalizacionesPorUsuario = new HashMap<>();
    }

    public void agregarMultaPorRetraso(String usuario, int diasRetraso) {
        PenalizacionUsuario penalizacionUsuario = penalizacionesPorUsuario.getOrDefault(usuario, new PenalizacionUsuario());
        penalizacionUsuario.agregarMultaPorRetraso(diasRetraso);
        penalizacionesPorUsuario.put(usuario, penalizacionUsuario);
        System.out.println("Multa por retraso agregada con éxito.");
    }

    public void agregarSancionPorEstadoLibro(String usuario, int costoSancion) {
        PenalizacionUsuario penalizacionUsuario = penalizacionesPorUsuario.getOrDefault(usuario, new PenalizacionUsuario());
        penalizacionUsuario.agregarSancionPorEstadoLibro(costoSancion);
        penalizacionesPorUsuario.put(usuario, penalizacionUsuario);
        System.out.println("Sanción por estado del libro agregada con éxito.");
    }

    public int obtenerPenalizacionTotal(String usuario) {
        PenalizacionUsuario penalizacionUsuario = penalizacionesPorUsuario.getOrDefault(usuario, new PenalizacionUsuario());
        return penalizacionUsuario.getPenalizacionTotal();
    }

    public void mostrarPenalizaciones() {
        System.out.println("Penalizaciones por usuario:");
        for (Map.Entry<String, PenalizacionUsuario> entry : penalizacionesPorUsuario.entrySet()) {
            System.out.println("Usuario: " + entry.getKey() + ", Penalización Total: " + entry.getValue().getPenalizacionTotal());
        }
    }

    private static class PenalizacionUsuario {
        private int multaPorRetraso;
        private int sancionPorEstadoLibro;

        public PenalizacionUsuario() {
            this.multaPorRetraso = 0;
            this.sancionPorEstadoLibro = 0;
        }

        public void agregarMultaPorRetraso(int diasRetraso) {
            // Lógica para calcular la multa por retraso
            this.multaPorRetraso += diasRetraso * 5; // Supongamos $5 por día de retraso
        }

        public void agregarSancionPorEstadoLibro(int costoSancion) {
            // Lógica para calcular la sanción por estado del libro
            this.sancionPorEstadoLibro += costoSancion;
        }

        public int getPenalizacionTotal() {
            return multaPorRetraso + sancionPorEstadoLibro;
        }
    }

    public static void main(String[] args) {
        MultasYSanciones multasYSanciones = new MultasYSanciones();

        // Ejemplo de cómo agregar multa por retraso
        multasYSanciones.agregarMultaPorRetraso("usuario1", 3);

        // Ejemplo de cómo agregar sanción por estado del libro
        multasYSanciones.agregarSancionPorEstadoLibro("usuario2", 20);

        // Mostrar penalizaciones
        multasYSanciones.mostrarPenalizaciones();

        // Obtener penalización total para un usuario
        String usuarioConsulta = "usuario1";
        int penalizacionTotal = multasYSanciones.obtenerPenalizacionTotal(usuarioConsulta);
        System.out.println("Penalización total para " + usuarioConsulta + ": $" + penalizacionTotal);
    }
}