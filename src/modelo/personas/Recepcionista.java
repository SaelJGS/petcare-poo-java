package modelo.personas;

/**
 * Recepcionista de la clínica. Hereda los datos de contacto de Persona
 * y agrega su turno de trabajo.
 */
public class Recepcionista extends Persona {
    private String turno;

    /**
     * Crea un recepcionista con sus datos de contacto y su turno.
     *
     * @param nombre nombre del recepcionista
     * @param apellido apellido del recepcionista
     * @param telefono teléfono de 10 dígitos
     * @param correo correo electrónico válido
     * @param turno matutino, vespertino o nocturno
     * @throws IllegalArgumentException si algún dato no cumple su regla
     */
    public Recepcionista(String nombre, String apellido, String telefono, String correo,
                         String turno) {
        super(nombre, apellido, telefono, correo);
        setTurno(turno);
    }

    /**
     * Devuelve el turno del recepcionista.
     *
     * @return el turno en minúsculas
     */
    public String getTurno() {
        return turno;
    }

    /**
     * Cambia el turno del recepcionista. Se guarda siempre en minúsculas.
     *
     * @param turno matutino, vespertino o nocturno, sin distinguir mayúsculas
     * @throws IllegalArgumentException si el turno es nulo o no es uno de los tres válidos
     */
    public void setTurno(String turno) {
        if (turno == null
                || !(turno.equalsIgnoreCase("matutino")
                || turno.equalsIgnoreCase("vespertino")
                || turno.equalsIgnoreCase("nocturno"))) {
            throw new IllegalArgumentException("El turno debe ser matutino, vespertino o nocturno.");
        }
        this.turno = turno.toLowerCase();
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Turno: " + turno;
    }
}