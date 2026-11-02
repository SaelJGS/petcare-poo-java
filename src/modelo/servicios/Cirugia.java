package modelo.servicios;

/**
 * Cirugía practicada a una mascota. Su costo depende de las horas de quirófano
 * y de la complejidad de la operación.
 */
public class Cirugia implements Facturable {
    // static final: el costo por hora es el mismo para todas las cirugías,
    // así que no hace falta una copia por objeto.
    private static final double COSTO_HORA_QUIROFANO = 800.0;

    private String complejidad;
    private double horasQuirofano;

    /**
     * Crea una cirugía con su complejidad y sus horas de quirófano.
     *
     * @param complejidad baja, media o alta, sin distinguir mayúsculas
     * @param horasQuirofano horas de quirófano, mayores que 0
     * @throws IllegalArgumentException si algún dato no cumple su regla
     */
    public Cirugia(String complejidad, double horasQuirofano) {
        setComplejidad(complejidad);
        setHorasQuirofano(horasQuirofano);
    }

    /**
     * Devuelve la complejidad de la cirugía.
     *
     * @return la complejidad en minúsculas
     */
    public String getComplejidad() {
        return complejidad;
    }

    /**
     * Cambia la complejidad de la cirugía. Se guarda siempre en minúsculas.
     *
     * @param complejidad baja, media o alta, sin distinguir mayúsculas
     * @throws IllegalArgumentException si la complejidad es nula o no es una de las tres
     */
    public void setComplejidad(String complejidad) {
        if (complejidad == null
                || !(complejidad.equalsIgnoreCase("baja")
                || complejidad.equalsIgnoreCase("media")
                || complejidad.equalsIgnoreCase("alta"))) {
            throw new IllegalArgumentException("La complejidad debe ser baja, media o alta.");
        }
        this.complejidad = complejidad.toLowerCase();
    }

    /**
     * Devuelve las horas de quirófano.
     *
     * @return las horas de quirófano
     */
    public double getHorasQuirofano() {
        return horasQuirofano;
    }

    /**
     * Cambia las horas de quirófano.
     *
     * @param horasQuirofano nuevas horas, mayores que 0
     * @throws IllegalArgumentException si las horas son menores o iguales que 0
     */
    public void setHorasQuirofano(double horasQuirofano) {
        if (horasQuirofano <= 0) {
            throw new IllegalArgumentException("Las horas de quirófano deben ser mayores que 0.");
        }
        this.horasQuirofano = horasQuirofano;
    }

    @Override
    public double calcularCosto() {
        return horasQuirofano * COSTO_HORA_QUIROFANO * obtenerFactor();
    }

    private double obtenerFactor() {
        switch (complejidad) {
            case "baja":
                return 1.0;
            case "media":
                return 1.5;
            default:
                return 2.0;
        }
    }

    @Override
    public String toString() {
        return "Cirugía | Complejidad: " + complejidad
                + " | Horas: " + horasQuirofano
                + " | Total: " + calcularCosto();
    }
}