package modelo.servicios;

/**
 * Vacunación aplicada a una mascota. Se cobra con una tarifa de aplicación
 * más el costo de la vacuna.
 */
public class Vacunacion implements Facturable {
    // static final: la tarifa de aplicación es la misma para todas las vacunaciones,
    // así que no hace falta una copia por objeto.
    private static final double TARIFA_APLICACION = 100.0;

    private String nombreVacuna;
    private double costoVacuna;

    /**
     * Crea una vacunación con el nombre y el costo de la vacuna.
     *
     * @param nombreVacuna nombre de la vacuna, no vacío
     * @param costoVacuna costo de la vacuna, mayor o igual a 0
     * @throws IllegalArgumentException si algún dato no cumple su regla
     */
    public Vacunacion(String nombreVacuna, double costoVacuna) {
        setNombreVacuna(nombreVacuna);
        setCostoVacuna(costoVacuna);
    }

    /**
     * Devuelve el nombre de la vacuna.
     *
     * @return el nombre de la vacuna
     */
    public String getNombreVacuna() {
        return nombreVacuna;
    }

    /**
     * Cambia el nombre de la vacuna.
     *
     * @param nombreVacuna nuevo nombre, no puede ser nulo ni estar vacío
     * @throws IllegalArgumentException si el nombre es nulo o está vacío
     */
    public void setNombreVacuna(String nombreVacuna) {
        if (nombreVacuna == null || nombreVacuna.isBlank()) {
            throw new IllegalArgumentException("El nombre de la vacuna es obligatorio.");
        }
        this.nombreVacuna = nombreVacuna.trim();
    }

    /**
     * Devuelve el costo de la vacuna, sin la tarifa de aplicación.
     *
     * @return el costo de la vacuna
     */
    public double getCostoVacuna() {
        return costoVacuna;
    }

    /**
     * Cambia el costo de la vacuna.
     *
     * @param costoVacuna nuevo costo, mayor o igual a 0
     * @throws IllegalArgumentException si el costo es negativo
     */
    public void setCostoVacuna(double costoVacuna) {
        if (costoVacuna < 0) {
            throw new IllegalArgumentException("El costo de la vacuna no puede ser negativo.");
        }
        this.costoVacuna = costoVacuna;
    }

    @Override
    public double calcularCosto() {
        return TARIFA_APLICACION + costoVacuna;
    }

    @Override
    public String toString() {
        return "Vacunación | Vacuna: " + nombreVacuna
                + " | Costo vacuna: " + costoVacuna
                + " | Total: " + calcularCosto();
    }
}