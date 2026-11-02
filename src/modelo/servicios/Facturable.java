package modelo.servicios;

/**
 * Contrato de los servicios que se pueden cobrar en la clínica.
 * Cada servicio calcula su costo a su manera.
 */
public interface Facturable {

    /**
     * Calcula el costo del servicio.
     *
     * @return el costo, nunca negativo
     */
    double calcularCosto();
}