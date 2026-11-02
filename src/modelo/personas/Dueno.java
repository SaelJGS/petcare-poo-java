package modelo.personas;

import java.util.ArrayList;
import java.util.List;

import modelo.mascotas.Mascota;

/**
 * Dueño de una o más mascotas de la clínica. Hereda los datos de contacto de Persona,
 * agrega su dirección y lleva el registro de sus mascotas.
 */
public class Dueno extends Persona {
    private String direccion;
    private List<Mascota> mascotas;

    /**
     * Crea un dueño sin mascotas.
     *
     * @param nombre nombre del dueño
     * @param apellido apellido del dueño
     * @param telefono teléfono de 10 dígitos
     * @param correo correo electrónico válido
     * @param direccion dirección, no vacía
     * @throws IllegalArgumentException si algún dato no cumple su regla
     */
    public Dueno(String nombre, String apellido, String telefono, String correo,
                 String direccion) {
        super(nombre, apellido, telefono, correo);
        setDireccion(direccion);
        this.mascotas = new ArrayList<>();
    }

    /**
     * Devuelve la dirección del dueño.
     *
     * @return la dirección
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Cambia la dirección del dueño.
     *
     * @param direccion nueva dirección, no puede ser nula ni estar vacía
     * @throws IllegalArgumentException si la dirección es nula o está vacía
     */
    public void setDireccion(String direccion) {
        if (direccion == null || direccion.isBlank()) {
            throw new IllegalArgumentException("La dirección es obligatoria.");
        }
        this.direccion = direccion.trim();
    }

    /**
     * Devuelve una copia de la lista de mascotas, para que nadie la modifique desde fuera.
     *
     * @return las mascotas del dueño
     */
    public List<Mascota> getMascotas() {
        return new ArrayList<>(mascotas);
    }

    /**
     * Registra una mascota del dueño. Si ya estaba registrada, no la duplica.
     *
     * @param mascota mascota a registrar, no puede ser nula
     * @throws IllegalArgumentException si la mascota es nula
     */
    public void agregarMascota(Mascota mascota) {
        if (mascota == null) {
            throw new IllegalArgumentException("La mascota no puede ser nula.");
        }
        if (!mascotas.contains(mascota)) {
            mascotas.add(mascota);
        }
    }

    /**
     * Quita una mascota del registro del dueño, por ejemplo al darla de baja.
     *
     * @param mascota mascota a quitar
     */
    public void quitarMascota(Mascota mascota) {
        mascotas.remove(mascota);
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Dirección: " + direccion
                + " | Mascotas: " + mascotas.size();
    }
}