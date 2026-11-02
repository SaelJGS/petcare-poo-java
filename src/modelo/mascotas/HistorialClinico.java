package modelo.mascotas;

import java.util.ArrayList;
import java.util.List;

import modelo.servicios.Facturable;

/**
 * Registro de los servicios que ha recibido una mascota.
 * Lo crea la propia mascota y desaparece con ella.
 */
public class HistorialClinico {
    private List<Facturable> registros;

    // Acceso de paquete: solo Mascota, del mismo paquete, puede crear su historial
    // (composición); fuera de modelo.mascotas nadie puede hacer new HistorialClinico().
    HistorialClinico() {
        this.registros = new ArrayList<>();
    }

    /**
     * Agrega un servicio al historial.
     *
     * @param registro servicio realizado, no puede ser nulo
     * @throws IllegalArgumentException si el registro es nulo
     */
    public void agregarRegistro(Facturable registro) {
        if (registro == null) {
            throw new IllegalArgumentException("El registro no puede ser nulo.");
        }
        registros.add(registro);
    }

    /**
     * Devuelve una copia de los registros, para que nadie modifique el historial desde fuera.
     *
     * @return lista de servicios registrados
     */
    public List<Facturable> listarRegistros() {
        return new ArrayList<>(registros);
    }
}