package modelo.mascotas;

import modelo.personas.Dueno;

/**
 * Base común de las mascotas de la clínica: perros, gatos y aves exóticas.
 * Guarda y valida los datos compartidos y crea el historial clínico de cada mascota.
 */
public abstract class Mascota {
    protected String nombre;
    protected double edad;
    protected double peso;
    protected Dueno dueno;
    // private: el historial es parte de la mascota (composición); nadie más lo reemplaza.
    private HistorialClinico historial;

    // protected: solo Perro, Gato y AveExotica lo llaman con super(...).
    protected Mascota(String nombre, double edad, double peso, Dueno dueno) {
        setNombre(nombre);
        setEdad(edad);
        setPeso(peso);
        if (dueno == null) {
            throw new IllegalArgumentException("El dueño es obligatorio.");
        }
        this.dueno = dueno;
        this.historial = new HistorialClinico();
    }

    /**
     * Devuelve el nombre de la mascota.
     *
     * @return el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve la edad de la mascota.
     *
     * @return la edad, mayor que 0
     */
    public double getEdad() {
        return edad;
    }

    /**
     * Devuelve el peso de la mascota.
     *
     * @return el peso, mayor que 0
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Devuelve el dueño de la mascota.
     *
     * @return el dueño
     */
    public Dueno getDueno() {
        return dueno;
    }

    /**
     * Devuelve el historial clínico, que la mascota crea en su constructor.
     *
     * @return el historial clínico
     */
    public HistorialClinico getHistorial() {
        return historial;
    }

    /**
     * Cambia el nombre de la mascota.
     *
     * @param nombre nuevo nombre, no puede ser nulo ni estar vacío
     * @throws IllegalArgumentException si el nombre es nulo o está vacío
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        this.nombre = nombre.trim();
    }

    /**
     * Cambia la edad de la mascota.
     *
     * @param edad nueva edad, mayor que 0
     * @throws IllegalArgumentException si la edad es menor o igual que 0
     */
    public void setEdad(double edad) {
        if (edad <= 0) {
            throw new IllegalArgumentException("La edad debe ser mayor que 0.");
        }
        this.edad = edad;
    }

    /**
     * Cambia el peso de la mascota.
     *
     * @param peso nuevo peso, mayor que 0
     * @throws IllegalArgumentException si el peso es menor o igual que 0
     */
    public void setPeso(double peso) {
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor que 0.");
        }
        this.peso = peso;
    }

    /**
     * Devuelve el sonido que emite la mascota; cada tipo de mascota lo define a su manera.
     *
     * @return el sonido de la mascota
     */
    public abstract String hacerSonido();

    @Override
    public String toString() {
        return nombre + " | Edad: " + edad + " | Peso: " + peso + " kg"
                + " | Dueño: " + dueno.getNombre();
    }
}