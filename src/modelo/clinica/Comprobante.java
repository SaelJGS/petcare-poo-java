package modelo.clinica;

/**
 * Comprobante que se genera al terminar una cita atendida.
 * Guarda el folio, la cita y el total cobrado.
 */
public class Comprobante {
    // static: hay un solo contador para toda la clase; si fuera de instancia,
    // todos los comprobantes tendrían el folio 1.
    private static int contadorComprobantes = 0;

    private int folio;
    private Cita cita;
    private double total;

    /**
     * Crea el comprobante de una cita atendida y calcula su total una sola vez.
     *
     * @param cita cita atendida de la que sale el comprobante
     * @throws IllegalArgumentException si la cita es nula o no está atendida
     */
    public Comprobante(Cita cita) {
        if (cita == null || cita.getEstado() != EstadoCita.ATENDIDA) {
            throw new IllegalArgumentException("El comprobante requiere una cita atendida.");
        }
        contadorComprobantes++;
        this.folio = contadorComprobantes;
        this.cita = cita;
        this.total = cita.getServicio().calcularCosto();
    }

    /**
     * Devuelve el folio del comprobante.
     *
     * @return el folio, que empieza en 1
     */
    public int getFolio() {
        return folio;
    }

    /**
     * Devuelve la cita de la que sale el comprobante.
     *
     * @return la cita atendida
     */
    public Cita getCita() {
        return cita;
    }

    /**
     * Devuelve el total cobrado.
     *
     * @return el total, nunca negativo
     */
    public double getTotal() {
        return total;
    }

    /**
     * Devuelve cuántos comprobantes se han generado en total.
     *
     * @return la cantidad de comprobantes generados
     */
    public static int getTotalGenerados() {
        return contadorComprobantes;
    }

    @Override
    public String toString() {
        return "Comprobante #" + folio
                + " | Mascota: " + cita.getMascota().getNombre()
                + " | Veterinario: " + cita.getVeterinario().getNombre()
                + " | Total: " + total;
    }
}