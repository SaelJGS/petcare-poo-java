package modelo.clinica;

/**
 * Consultorio numerado de la clínica, donde ocurren las citas.
 * La clínica lo crea y desaparece con ella.
 */
public class Consultorio {
    private int numero;

    // Acceso de paquete: solo Clinica, del mismo paquete, crea sus consultorios
    // (composición); fuera de modelo.clinica nadie puede hacer new Consultorio(...).
    Consultorio(int numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException("El número de consultorio debe ser mayor que 0.");
        }
        this.numero = numero;
    }

    /**
     * Devuelve el número del consultorio.
     *
     * @return el número, mayor que 0
     */
    public int getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return "Consultorio " + numero;
    }
}