package modelo.personas;

/**
 * Veterinario de la clínica. Hereda los datos de contacto de Persona
 * y agrega su cédula profesional y su especialidad.
 */
public class Veterinario extends Persona {
    private String cedulaProfesional;
    private String especialidad;

    /**
     * Crea un veterinario con sus datos de contacto y profesionales.
     *
     * @param nombre nombre del veterinario
     * @param apellido apellido del veterinario
     * @param telefono teléfono de 10 dígitos
     * @param correo correo electrónico válido
     * @param cedulaProfesional cédula de 7 u 8 dígitos
     * @param especialidad especialidad, no vacía
     * @throws IllegalArgumentException si algún dato no cumple su regla
     */
    public Veterinario(String nombre, String apellido, String telefono, String correo,
                       String cedulaProfesional, String especialidad) {
        super(nombre, apellido, telefono, correo);
        setCedulaProfesional(cedulaProfesional);
        setEspecialidad(especialidad);
    }

    /**
     * Devuelve la cédula profesional.
     *
     * @return la cédula de 7 u 8 dígitos
     */
    public String getCedulaProfesional() {
        return cedulaProfesional;
    }

    /**
     * Cambia la cédula profesional.
     *
     * @param cedulaProfesional nueva cédula, solo dígitos, 7 u 8
     * @throws IllegalArgumentException si la cédula es nula o no tiene 7 u 8 dígitos
     */
    public void setCedulaProfesional(String cedulaProfesional) {
        if (cedulaProfesional == null || !cedulaProfesional.matches("\\d{7,8}")) {
            throw new IllegalArgumentException("La cédula profesional debe tener 7 u 8 dígitos.");
        }
        this.cedulaProfesional = cedulaProfesional;
    }

    /**
     * Devuelve la especialidad del veterinario.
     *
     * @return la especialidad
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Cambia la especialidad del veterinario.
     *
     * @param especialidad nueva especialidad, no puede ser nula ni estar vacía
     * @throws IllegalArgumentException si la especialidad es nula o está vacía
     */
    public void setEspecialidad(String especialidad) {
        if (especialidad == null || especialidad.isBlank()) {
            throw new IllegalArgumentException("La especialidad es obligatoria.");
        }
        this.especialidad = especialidad.trim();
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Cédula: " + cedulaProfesional
                + " | Especialidad: " + especialidad;
    }
}