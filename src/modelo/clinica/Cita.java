package modelo.clinica;

import java.time.LocalDateTime;

import modelo.mascotas.Mascota;
import modelo.personas.Veterinario;
import modelo.servicios.Facturable;

/**
 * Cita entre una mascota y un veterinario, en un consultorio y a una fecha y hora.
 * Controla su propio estado: programada, atendida o cancelada.
 */
public class Cita {
    private Mascota mascota;
    private Veterinario veterinario;
    private Consultorio consultorio;
    private Facturable servicio;
    private LocalDateTime fechaHora;
    private EstadoCita estado;

    /**
     * Crea una cita programada con objetos que ya existen.
     *
     * @param mascota mascota que se atiende
     * @param veterinario veterinario que la atiende
     * @param consultorio consultorio donde ocurre
     * @param servicio servicio que se realiza y se cobra
     * @param fechaHora fecha y hora de la cita
     * @throws IllegalArgumentException si algún parámetro es nulo
     */
    public Cita(Mascota mascota, Veterinario veterinario, Consultorio consultorio,
                Facturable servicio, LocalDateTime fechaHora) {
        if (mascota == null || veterinario == null || consultorio == null
                || servicio == null || fechaHora == null) {
            throw new IllegalArgumentException("Ningún dato de la cita puede ser nulo.");
        }
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.consultorio = consultorio;
        this.servicio = servicio;
        this.fechaHora = fechaHora;
        this.estado = EstadoCita.PROGRAMADA;
    }

    /**
     * Devuelve la mascota de la cita.
     *
     * @return la mascota
     */
    public Mascota getMascota() {
        return mascota;
    }

    /**
     * Devuelve el veterinario de la cita.
     *
     * @return el veterinario
     */
    public Veterinario getVeterinario() {
        return veterinario;
    }

    /**
     * Devuelve el consultorio de la cita.
     *
     * @return el consultorio
     */
    public Consultorio getConsultorio() {
        return consultorio;
    }

    /**
     * Devuelve el servicio de la cita.
     *
     * @return el servicio que se cobra
     */
    public Facturable getServicio() {
        return servicio;
    }

    /**
     * Devuelve la fecha y hora de la cita.
     *
     * @return la fecha y hora
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Devuelve el estado actual de la cita.
     *
     * @return el estado
     */
    public EstadoCita getEstado() {
        return estado;
    }

    /**
     * Marca la cita como atendida.
     *
     * @throws IllegalStateException si la cita no está programada
     */
    public void atender() {
        if (estado != EstadoCita.PROGRAMADA) {
            throw new IllegalStateException("Solo una cita programada puede atenderse.");
        }
        estado = EstadoCita.ATENDIDA;
    }

    /**
     * Marca la cita como cancelada.
     *
     * @throws IllegalStateException si la cita no está programada
     */
    public void cancelar() {
        if (estado != EstadoCita.PROGRAMADA) {
            throw new IllegalStateException("Solo una cita programada puede cancelarse.");
        }
        estado = EstadoCita.CANCELADA;
    }

    /**
     * Genera el comprobante de una cita ya atendida.
     *
     * @return el comprobante con el total del servicio
     * @throws IllegalStateException si la cita no está atendida
     */
    public Comprobante generarComprobante() {
        if (estado != EstadoCita.ATENDIDA) {
            throw new IllegalStateException("Solo una cita atendida genera comprobante.");
        }
        return new Comprobante(this);
    }

    @Override
    public String toString() {
        return "Cita | Mascota: " + mascota.getNombre()
                + " | Veterinario: " + veterinario.getNombre()
                + " | Consultorio: " + consultorio.getNumero()
                + " | Fecha: " + fechaHora
                + " | Estado: " + estado;
    }
}