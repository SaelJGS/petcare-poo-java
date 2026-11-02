package modelo.personas;

/**
 * Base común de las personas de la clínica: dueños, veterinarios y recepcionistas.
 * Guarda y valida los datos de contacto compartidos.
 */
public abstract class Persona {
    protected String nombre;
    protected String apellido;
    protected String telefono;
    protected String correo;

    // protected: solo las clases hijas (Dueno, Veterinario, Recepcionista)
    // lo llaman con super(...); no es public porque Persona es abstracta
    // y nunca se crea directamente.
    protected Persona(String nombre, String apellido, String telefono, String correo) {
        setNombre(nombre);
        setApellido(apellido);
        setTelefono(telefono);
        setCorreo(correo);
    }

    /**
     * Devuelve el nombre de la persona.
     *
     * @return el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el apellido de la persona.
     *
     * @return el apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Devuelve el teléfono de la persona.
     *
     * @return el teléfono de 10 dígitos
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Devuelve el correo electrónico de la persona.
     *
     * @return el correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Cambia el nombre de la persona.
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
     * Cambia el apellido de la persona.
     *
     * @param apellido nuevo apellido, no puede ser nulo ni estar vacío
     * @throws IllegalArgumentException si el apellido es nulo o está vacío
     */
    public void setApellido(String apellido) {
        if (apellido == null || apellido.isBlank()) {
            throw new IllegalArgumentException("El apellido es obligatorio.");
        }
        this.apellido = apellido.trim();
    }

    /**
     * Cambia el teléfono de la persona.
     *
     * @param telefono nuevo teléfono, exactamente 10 dígitos
     * @throws IllegalArgumentException si el teléfono es nulo o no tiene 10 dígitos
     */
    public void setTelefono(String telefono) {
        if (telefono == null || !telefono.matches("\\d{10}")) {
            throw new IllegalArgumentException("El teléfono debe tener 10 dígitos.");
        }
        this.telefono = telefono;
    }

    /**
     * Cambia el correo electrónico de la persona.
     *
     * @param correo nuevo correo, con una sola @ y un punto después de ella
     * @throws IllegalArgumentException si el correo es nulo o su formato no es válido
     */
    public void setCorreo(String correo) {
        if (correo == null || !esCorreoValido(correo)) {
            throw new IllegalArgumentException("El correo electrónico no es válido.");
        }
        this.correo = correo;
    }

    private boolean esCorreoValido(String correo) {
        int arroba = correo.indexOf('@');
        int punto = correo.lastIndexOf('.');

        return arroba > 0 &&
                punto > arroba + 1 &&
                punto < correo.length() - 1 &&
                correo.indexOf('@', arroba + 1) == -1;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " | Tel: " + telefono + " | Correo: " + correo;
    }
}