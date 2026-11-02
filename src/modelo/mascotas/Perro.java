package modelo.mascotas;

import modelo.personas.Dueno;

/**
 * Perro que se atiende en la clínica. Hereda los datos de Mascota
 * y agrega su raza y su nivel de energía.
 */
public class Perro extends Mascota {
    private String raza;
    private int nivelEnergia;

    /**
     * Crea un perro con sus datos comunes y propios.
     *
     * @param nombre nombre del perro
     * @param edad edad, mayor que 0
     * @param peso peso, mayor que 0
     * @param dueno dueño del perro, no puede ser nulo
     * @param raza raza, no vacía
     * @param nivelEnergia nivel de energía, de 1 a 5
     * @throws IllegalArgumentException si algún dato no cumple su regla
     */
    public Perro(String nombre, double edad, double peso, Dueno dueno,
                 String raza, int nivelEnergia) {
        super(nombre, edad, peso, dueno);
        setRaza(raza);
        setNivelEnergia(nivelEnergia);
    }

    /**
     * Devuelve la raza del perro.
     *
     * @return la raza
     */
    public String getRaza() {
        return raza;
    }

    /**
     * Cambia la raza del perro.
     *
     * @param raza nueva raza, no puede ser nula ni estar vacía
     * @throws IllegalArgumentException si la raza es nula o está vacía
     */
    public void setRaza(String raza) {
        if (raza == null || raza.isBlank()) {
            throw new IllegalArgumentException("La raza es obligatoria.");
        }
        this.raza = raza.trim();
    }

    /**
     * Devuelve el nivel de energía del perro.
     *
     * @return el nivel, de 1 a 5
     */
    public int getNivelEnergia() {
        return nivelEnergia;
    }

    /**
     * Cambia el nivel de energía del perro.
     *
     * @param nivelEnergia nuevo nivel, de 1 a 5
     * @throws IllegalArgumentException si el nivel es menor que 1 o mayor que 5
     */
    public void setNivelEnergia(int nivelEnergia) {
        if (nivelEnergia < 1 || nivelEnergia > 5) {
            throw new IllegalArgumentException("El nivel de energía debe estar entre 1 y 5.");
        }
        this.nivelEnergia = nivelEnergia;
    }

    @Override
    public String hacerSonido() {
        return "Gua Gua";
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Raza: " + raza
                + " | Energía: " + nivelEnergia;
    }
}