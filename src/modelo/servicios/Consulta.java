package modelo.servicios;

/**
 * Consulta general que se le hace a una mascota. Se cobra con una tarifa base fija.
 */
public class Consulta implements Facturable {
    // static final: la tarifa es la misma para todas las consultas, no hace falta una copia por objeto.
    private static final double TARIFA_BASE = 300.0;

    private String motivo;
    private String diagnostico;

    /**
     * Crea una consulta con valores iniciales, para completarla después.
     */
    public Consulta() {
        this("Sin especificar", "Pendiente");
    }

    /**
     * Crea una consulta con su motivo y su diagnóstico.
     *
     * @param motivo motivo de la consulta, no vacío
     * @param diagnostico diagnóstico de la consulta, no vacío
     * @throws IllegalArgumentException si algún dato es nulo o está vacío
     */
    public Consulta(String motivo, String diagnostico) {
        setMotivo(motivo);
        setDiagnostico(diagnostico);
    }

    /**
     * Devuelve el motivo de la consulta.
     *
     * @return el motivo
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Cambia el motivo de la consulta.
     *
     * @param motivo nuevo motivo, no puede ser nulo ni estar vacío
     * @throws IllegalArgumentException si el motivo es nulo o está vacío
     */
    public void setMotivo(String motivo) {
        if (motivo == null || motivo.isBlank()) {
            throw new IllegalArgumentException("El motivo es obligatorio.");
        }
        this.motivo = motivo.trim();
    }

    /**
     * Devuelve el diagnóstico de la consulta.
     *
     * @return el diagnóstico
     */
    public String getDiagnostico() {
        return diagnostico;
    }

    /**
     * Cambia el diagnóstico de la consulta.
     *
     * @param diagnostico nuevo diagnóstico, no puede ser nulo ni estar vacío
     * @throws IllegalArgumentException si el diagnóstico es nulo o está vacío
     */
    public void setDiagnostico(String diagnostico) {
        if (diagnostico == null || diagnostico.isBlank()) {
            throw new IllegalArgumentException("El diagnóstico es obligatorio.");
        }
        this.diagnostico = diagnostico.trim();
    }

    @Override
    public double calcularCosto() {
        return TARIFA_BASE;
    }

    @Override
    public String toString() {
        return "Consulta | Motivo: " + motivo
                + " | Diagnóstico: " + diagnostico
                + " | Costo: " + calcularCosto();
    }
}