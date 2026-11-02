package modelo.mascotas;

import modelo.personas.Dueno;

/**
 * Ave exótica que se atiende en la clínica. Hereda los datos de Mascota
 * y agrega la longitud de sus alas.
 */
public class AveExotica extends Mascota {
    private double longitudAlas;

    /**
     * Crea un ave exótica con sus datos comunes y propios.
     *
     * @param nombre nombre del ave
     * @param edad edad, mayor que 0
     * @param peso peso, mayor que 0
     * @param dueno dueño del ave, no puede ser nulo
     * @param longitudAlas longitud de las alas en centímetros, mayor que 0
     * @throws IllegalArgumentException si algún dato no cumple su regla
     */
    public AveExotica(String nombre, double edad, double peso, Dueno dueno,
                      double longitudAlas) {
        super(nombre, edad, peso, dueno);
        setLongitudAlas(longitudAlas);
    }

    /**
     * Devuelve la longitud de las alas.
     *
     * @return la longitud en centímetros
     */
    public double getLongitudAlas() {
        return longitudAlas;
    }

    /**
     * Cambia la longitud de las alas.
     *
     * @param longitudAlas nueva longitud en centímetros, mayor que 0
     * @throws IllegalArgumentException si la longitud es menor o igual que 0
     */
    public void setLongitudAlas(double longitudAlas) {
        if (longitudAlas <= 0) {
            throw new IllegalArgumentException("La longitud de alas debe ser mayor que 0.");
        }
        this.longitudAlas = longitudAlas;
    }

    @Override
    public String hacerSonido() {
        return "Pío Pío Pío";
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Alas: " + longitudAlas + " cm";
    }
}