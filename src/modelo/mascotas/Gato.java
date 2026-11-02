package modelo.mascotas;

import modelo.personas.Dueno;

/**
 * Gato que se atiende en la clínica. Hereda los datos de Mascota
 * y agrega si es doméstico.
 */
public class Gato extends Mascota {
    private boolean esDomestico;

    /**
     * Crea un gato con sus datos comunes y propios.
     *
     * @param nombre nombre del gato
     * @param edad edad, mayor que 0
     * @param peso peso, mayor que 0
     * @param dueno dueño del gato, no puede ser nulo
     * @param esDomestico true si el gato es doméstico
     * @throws IllegalArgumentException si algún dato no cumple su regla
     */
    public Gato(String nombre, double edad, double peso, Dueno dueno, boolean esDomestico) {
        super(nombre, edad, peso, dueno);
        this.esDomestico = esDomestico;
    }

    /**
     * Indica si el gato es doméstico.
     *
     * @return true si es doméstico
     */
    public boolean esDomestico() {
        return esDomestico;
    }

    /**
     * Cambia si el gato es doméstico.
     *
     * @param esDomestico true si el gato es doméstico
     */
    public void setEsDomestico(boolean esDomestico) {
        this.esDomestico = esDomestico;
    }

    @Override
    public String hacerSonido() {
        return "Miau Miau";
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Doméstico: " + esDomestico;
    }
}