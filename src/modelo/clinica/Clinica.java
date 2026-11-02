package modelo.clinica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import modelo.mascotas.Mascota;
import modelo.personas.Dueno;
import modelo.personas.Veterinario;
import modelo.servicios.Facturable;

/**
 * Clínica veterinaria PetCare. Coordina a sus partes: crea sus consultorios (composición),
 * recibe veterinarios ya creados (agregación) y gestiona dueños, citas y comprobantes.
 */
public class Clinica {
    private String nombre;
    private List<Consultorio> consultorios;
    private List<Veterinario> veterinarios;
    private List<Dueno> duenos;
    private List<Cita> citas;
    private List<Comprobante> comprobantes;

    /**
     * Crea una clínica con sus consultorios numerados desde 1.
     *
     * @param nombre nombre de la clínica, no vacío
     * @param cantidadConsultorios cantidad de consultorios, mayor que 0
     * @throws IllegalArgumentException si el nombre es nulo o vacío, o la cantidad es 0 o menos
     */
    public Clinica(String nombre, int cantidadConsultorios) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la clínica es obligatorio.");
        }
        if (cantidadConsultorios <= 0) {
            throw new IllegalArgumentException("La clínica necesita al menos un consultorio.");
        }
        this.nombre = nombre.trim();
        this.consultorios = new ArrayList<>();
        this.veterinarios = new ArrayList<>();
        this.duenos = new ArrayList<>();
        this.citas = new ArrayList<>();
        this.comprobantes = new ArrayList<>();
        // Composición: la clínica crea sus consultorios y nadie más puede hacerlo.
        for (int i = 1; i <= cantidadConsultorios; i++) {
            consultorios.add(new Consultorio(i));
        }
    }

    /**
     * Devuelve el nombre de la clínica.
     *
     * @return el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Agrega un veterinario ya creado al equipo. Si ya estaba, no lo duplica.
     *
     * @param veterinario veterinario a agregar, no puede ser nulo
     * @throws IllegalArgumentException si el veterinario es nulo
     */
    public void agregarVeterinario(Veterinario veterinario) {
        if (veterinario == null) {
            throw new IllegalArgumentException("El veterinario no puede ser nulo.");
        }
        if (!veterinarios.contains(veterinario)) {
            veterinarios.add(veterinario);
        }
    }

    /**
     * Registra a un dueño como cliente. Si ya estaba registrado, no lo duplica.
     *
     * @param dueno dueño a registrar, no puede ser nulo
     * @throws IllegalArgumentException si el dueño es nulo
     */
    public void registrarDueno(Dueno dueno) {
        if (dueno == null) {
            throw new IllegalArgumentException("El dueño no puede ser nulo.");
        }
        if (!duenos.contains(dueno)) {
            duenos.add(dueno);
        }
    }

    /**
     * Agenda una cita si el veterinario y el consultorio están libres a esa hora.
     *
     * @param mascota mascota que se atiende
     * @param veterinario veterinario que la atiende
     * @param numeroConsultorio número del consultorio donde ocurre
     * @param servicio servicio que se realiza y se cobra
     * @param fechaHora fecha y hora de la cita
     * @return la cita programada
     * @throws IllegalArgumentException si el consultorio no existe o algún dato es nulo
     * @throws IllegalStateException si el veterinario o el consultorio están ocupados
     */
    public Cita agendarCita(Mascota mascota, Veterinario veterinario, int numeroConsultorio,
                            Facturable servicio, LocalDateTime fechaHora) {
        Consultorio consultorio = buscarConsultorio(numeroConsultorio);
        validarVeterinarioLibre(veterinario, fechaHora);
        validarConsultorioLibre(consultorio, fechaHora);
        Cita cita = new Cita(mascota, veterinario, consultorio, servicio, fechaHora);
        citas.add(cita);
        return cita;
    }

    /**
     * Atiende una cita y guarda su servicio en el historial de la mascota.
     *
     * @param cita cita a atender, no puede ser nula
     * @throws IllegalArgumentException si la cita es nula
     * @throws IllegalStateException si la cita no está programada
     */
    public void atenderCita(Cita cita) {
        validarCitaNoNula(cita);
        cita.atender();
        cita.getMascota().getHistorial().agregarRegistro(cita.getServicio());
    }

    /**
     * Cancela una cita, lo que libera el horario del veterinario y del consultorio.
     *
     * @param cita cita a cancelar, no puede ser nula
     * @throws IllegalArgumentException si la cita es nula
     * @throws IllegalStateException si la cita no está programada
     */
    public void cancelarCita(Cita cita) {
        validarCitaNoNula(cita);
        cita.cancelar();
    }

    /**
     * Genera y guarda el comprobante de una cita atendida.
     *
     * @param cita cita atendida, no puede ser nula
     * @return el comprobante generado
     * @throws IllegalArgumentException si la cita es nula
     * @throws IllegalStateException si la cita no está atendida
     */
    public Comprobante generarComprobante(Cita cita) {
        validarCitaNoNula(cita);
        Comprobante comprobante = cita.generarComprobante();
        comprobantes.add(comprobante);
        return comprobante;
    }

    /**
     * Busca las citas de una mascota por su nombre, sin distinguir mayúsculas.
     *
     * @param nombreMascota nombre de la mascota
     * @return las citas encontradas, vacía si no hay
     */
    public List<Cita> buscarCita(String nombreMascota) {
        List<Cita> resultado = new ArrayList<>();
        for (Cita cita : citas) {
            if (cita.getMascota().getNombre().equalsIgnoreCase(nombreMascota)) {
                resultado.add(cita);
            }
        }
        return resultado;
    }

    /**
     * Busca las citas que ocurren en una fecha.
     *
     * @param fecha fecha a consultar
     * @return las citas encontradas, vacía si no hay
     */
    public List<Cita> buscarCita(LocalDate fecha) {
        List<Cita> resultado = new ArrayList<>();
        for (Cita cita : citas) {
            if (cita.getFechaHora().toLocalDate().equals(fecha)) {
                resultado.add(cita);
            }
        }
        return resultado;
    }

    /**
     * Reúne las mascotas de todos los dueños registrados.
     *
     * @return la lista de mascotas, que puede mezclar perros, gatos y aves
     */
    public List<Mascota> listarMascotas() {
        List<Mascota> mascotas = new ArrayList<>();
        for (Dueno dueno : duenos) {
            mascotas.addAll(dueno.getMascotas());
        }
        return mascotas;
    }

    /**
     * Da de baja una mascota: cancela sus citas programadas y la quita de su dueño.
     *
     * @param mascota mascota a dar de baja, no puede ser nula
     * @throws IllegalArgumentException si la mascota es nula
     */
    public void darDeBajaMascota(Mascota mascota) {
        if (mascota == null) {
            throw new IllegalArgumentException("La mascota no puede ser nula.");
        }
        cancelarCitasProgramadas(mascota);
        mascota.getDueno().quitarMascota(mascota);
        // Recolector de basura: al quitarla de la lista de su dueño, la mascota deja de ser
        // accesible desde el sistema. Cuando no queden referencias (las citas y los comprobantes
        // ya generados se conservan como registro), Java libera la mascota y su historial,
        // porque el historial es parte de ella (composición).
    }

    /**
     * Suma lo cobrado en los comprobantes de las citas de un veterinario.
     *
     * @param veterinario veterinario a consultar
     * @return el total cobrado, 0 si no tiene comprobantes
     */
    public double totalCobradoPorVeterinario(Veterinario veterinario) {
        double total = 0;
        for (Comprobante comprobante : comprobantes) {
            if (comprobante.getCita().getVeterinario().equals(veterinario)) {
                total += comprobante.getTotal();
            }
        }
        return total;
    }

    /**
     * Devuelve los comprobantes de una mascota.
     *
     * @param mascota mascota a consultar
     * @return sus comprobantes, vacía si no tiene
     */
    public List<Comprobante> cobrosPorMascota(Mascota mascota) {
        List<Comprobante> resultado = new ArrayList<>();
        for (Comprobante comprobante : comprobantes) {
            if (comprobante.getCita().getMascota().equals(mascota)) {
                resultado.add(comprobante);
            }
        }
        return resultado;
    }

    private Consultorio buscarConsultorio(int numero) {
        for (Consultorio consultorio : consultorios) {
            if (consultorio.getNumero() == numero) {
                return consultorio;
            }
        }
        throw new IllegalArgumentException("No existe el consultorio " + numero + ".");
    }

    private void validarVeterinarioLibre(Veterinario veterinario, LocalDateTime fechaHora) {
        for (Cita cita : citas) {
            if (cita.getEstado() == EstadoCita.PROGRAMADA
                    && cita.getVeterinario().equals(veterinario)
                    && cita.getFechaHora().equals(fechaHora)) {
                throw new IllegalStateException("El veterinario ya tiene una cita a esa hora.");
            }
        }
    }

    private void validarConsultorioLibre(Consultorio consultorio, LocalDateTime fechaHora) {
        for (Cita cita : citas) {
            if (cita.getEstado() == EstadoCita.PROGRAMADA
                    && cita.getConsultorio().equals(consultorio)
                    && cita.getFechaHora().equals(fechaHora)) {
                throw new IllegalStateException("El consultorio ya está ocupado a esa hora.");
            }
        }
    }

    private void validarCitaNoNula(Cita cita) {
        if (cita == null) {
            throw new IllegalArgumentException("La cita no puede ser nula.");
        }
    }

    private void cancelarCitasProgramadas(Mascota mascota) {
        for (Cita cita : citas) {
            if (cita.getMascota().equals(mascota)
                    && cita.getEstado() == EstadoCita.PROGRAMADA) {
                cita.cancelar();
            }
        }
    }

    @Override
    public String toString() {
        return "Clínica " + nombre
                + " | Consultorios: " + consultorios.size()
                + " | Veterinarios: " + veterinarios.size()
                + " | Dueños: " + duenos.size()
                + " | Citas: " + citas.size()
                + " | Comprobantes: " + comprobantes.size();
    }
}