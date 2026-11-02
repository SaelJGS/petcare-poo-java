package app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.clinica.Cita;
import modelo.clinica.Clinica;
import modelo.clinica.Comprobante;
import modelo.mascotas.AveExotica;
import modelo.mascotas.Gato;
import modelo.mascotas.Mascota;
import modelo.mascotas.Perro;
import modelo.personas.Dueno;
import modelo.personas.Veterinario;
import modelo.servicios.Cirugia;
import modelo.servicios.Consulta;
import modelo.servicios.Facturable;
import modelo.servicios.Vacunacion;

/**
 * Punto de entrada del sistema de consola PetCare.
 * Permite administrar dueños, mascotas, veterinarios, citas y cobros.
 */
public class Main {
    private static final int CANTIDAD_CONSULTORIOS = 3;
    private static final Scanner SC = new Scanner(System.in);
    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static final Clinica CLINICA = new Clinica("PetCare", CANTIDAD_CONSULTORIOS);
    private static final List<Dueno> duenos = new ArrayList<>();
    private static final List<Veterinario> veterinarios = new ArrayList<>();
    private static final List<Cita> citas = new ArrayList<>();

    /**
     * Inicia el menú principal de PetCare.
     *
     * @param args argumentos de ejecución
     */
    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Selecciona una opción: ");

            try {
                ejecutarOpcion(opcion);
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }

            if (opcion != 10) {
                System.out.println("\nPresiona ENTER para continuar...");
                SC.nextLine();
            }
        } while (opcion != 10);

        System.out.println("Gracias por utilizar PetCare.");
        SC.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n========== CLÍNICA VETERINARIA PETCARE ==========");
        System.out.println("1. Registrar dueño y mascota");
        System.out.println("2. Registrar veterinario");
        System.out.println("3. Agendar una cita");
        System.out.println("4. Atender o cancelar una cita");
        System.out.println("5. Generar comprobante");
        System.out.println("6. Consultar historial clínico");
        System.out.println("7. Listar mascotas y sus sonidos");
        System.out.println("8. Ver total cobrado por veterinario");
        System.out.println("9. Dar de baja una mascota");
        System.out.println("10. Salir");
        System.out.println("=================================================");
    }

    private static void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                registrarDuenoYMascota();
                break;
            case 2:
                registrarVeterinario();
                break;
            case 3:
                agendarCita();
                break;
            case 4:
                cambiarEstadoCita();
                break;
            case 5:
                generarComprobante();
                break;
            case 6:
                consultarHistorial();
                break;
            case 7:
                listarMascotas();
                break;
            case 8:
                consultarTotalVeterinario();
                break;
            case 9:
                darDeBajaMascota();
                break;
            case 10:
                break;
            default:
                System.out.println("Opción inválida. Elige un número del 1 al 10.");
        }
    }

    private static void registrarDuenoYMascota() {
        System.out.println("\n--- REGISTRAR MASCOTA ---");
        Dueno dueno = obtenerDueno();
        Mascota mascota = crearMascota(leerTipoMascota(), dueno);
        dueno.agregarMascota(mascota);

        // El dueño se registra al final: si la mascota es inválida, no queda un dueño huérfano.
        if (!duenos.contains(dueno)) {
            CLINICA.registrarDueno(dueno);
            duenos.add(dueno);
        }

        System.out.println("Dueño y mascota registrados correctamente.");
        System.out.println(mascota);
    }

    private static Dueno obtenerDueno() {
        if (!duenos.isEmpty()) {
            System.out.println("1. Dueño nuevo");
            System.out.println("2. Dueño existente");
            if (leerEntero("Selecciona: ") == 2) {
                return seleccionarDueno();
            }
        }
        return crearDueno();
    }

    private static Dueno crearDueno() {
        System.out.println("\n--- DATOS DEL DUEÑO ---");
        return new Dueno(
                leerTexto("Nombre: "),
                leerTexto("Apellido: "),
                leerTexto("Teléfono (10 dígitos): "),
                leerTexto("Correo: "),
                leerTexto("Dirección: ")
        );
    }

    private static int leerTipoMascota() {
        System.out.println("\nTipo de mascota:");
        System.out.println("1. Perro");
        System.out.println("2. Gato");
        System.out.println("3. Ave exótica");
        return leerEntero("Selecciona: ");
    }

    private static Mascota crearMascota(int tipo, Dueno dueno) {
        if (tipo < 1 || tipo > 3) {
            throw new IllegalArgumentException("Tipo de mascota inválido.");
        }
        String nombre = leerTexto("Nombre de la mascota: ");
        double edad = leerDouble("Edad: ");
        double peso = leerDouble("Peso en kg: ");

        switch (tipo) {
            case 1:
                return new Perro(nombre, edad, peso, dueno,
                        leerTexto("Raza: "), leerEntero("Nivel de energía (1 a 5): "));
            case 2:
                return new Gato(nombre, edad, peso, dueno,
                        leerBooleano("¿Es doméstico? (s/n): "));
            default:
                return new AveExotica(nombre, edad, peso, dueno,
                        leerDouble("Longitud de alas en cm: "));
        }
    }

    private static void registrarVeterinario() {
        System.out.println("\n--- REGISTRAR VETERINARIO ---");
        Veterinario veterinario = new Veterinario(
                leerTexto("Nombre: "),
                leerTexto("Apellido: "),
                leerTexto("Teléfono (10 dígitos): "),
                leerTexto("Correo: "),
                leerTexto("Cédula profesional (7 u 8 dígitos): "),
                leerTexto("Especialidad: ")
        );

        CLINICA.agregarVeterinario(veterinario);
        veterinarios.add(veterinario);
        System.out.println("Veterinario registrado correctamente.");
    }

    private static void agendarCita() {
        if (CLINICA.listarMascotas().isEmpty() || veterinarios.isEmpty()) {
            System.out.println("Primero registra al menos una mascota y un veterinario.");
            return;
        }

        System.out.println("\n--- AGENDAR CITA ---");
        Mascota mascota = seleccionarMascota();
        Veterinario veterinario = seleccionarVeterinario();
        int consultorio = leerEntero("Consultorio (1 a " + CANTIDAD_CONSULTORIOS + "): ");
        LocalDateTime fecha = leerFechaHora();
        Facturable servicio = crearServicio();

        Cita cita = CLINICA.agendarCita(mascota, veterinario, consultorio, servicio, fecha);
        citas.add(cita);

        System.out.println("Cita agendada correctamente:");
        System.out.println(cita);
    }

    private static Facturable crearServicio() {
        System.out.println("\nTipo de servicio:");
        System.out.println("1. Consulta general");
        System.out.println("2. Vacunación");
        System.out.println("3. Cirugía");

        int tipo = leerEntero("Selecciona: ");
        switch (tipo) {
            case 1:
                return new Consulta(
                        leerTexto("Motivo: "),
                        leerTexto("Diagnóstico: ")
                );
            case 2:
                return new Vacunacion(
                        leerTexto("Nombre de la vacuna: "),
                        leerDouble("Costo de la vacuna: ")
                );
            case 3:
                return new Cirugia(
                        leerTexto("Complejidad (baja, media o alta): "),
                        leerDouble("Horas de quirófano: ")
                );
            default:
                throw new IllegalArgumentException("Tipo de servicio inválido.");
        }
    }

    private static void cambiarEstadoCita() {
        Cita cita = seleccionarCita();
        System.out.println("1. Atender");
        System.out.println("2. Cancelar");
        int opcion = leerEntero("Selecciona: ");

        if (opcion == 1) {
            CLINICA.atenderCita(cita);
            System.out.println("Cita atendida correctamente.");
        } else if (opcion == 2) {
            CLINICA.cancelarCita(cita);
            System.out.println("Cita cancelada correctamente.");
        } else {
            System.out.println("Opción inválida.");
        }
    }

    private static void generarComprobante() {
        Cita cita = seleccionarCita();
        Comprobante comprobante = CLINICA.generarComprobante(cita);
        System.out.println("\n--- COMPROBANTE ---");
        System.out.println(comprobante);
    }

    private static void consultarHistorial() {
        Mascota mascota = seleccionarMascota();
        List<Facturable> registros = mascota.getHistorial().listarRegistros();

        System.out.println("\n--- HISTORIAL DE " + mascota.getNombre() + " ---");
        if (registros.isEmpty()) {
            System.out.println("La mascota todavía no tiene consultas o servicios.");
            return;
        }

        for (int i = 0; i < registros.size(); i++) {
            Facturable registro = registros.get(i);
            System.out.println((i + 1) + ". " + registro
                    + " | Costo: $" + registro.calcularCosto());
        }
    }

    private static void listarMascotas() {
        List<Mascota> mascotas = CLINICA.listarMascotas();

        if (mascotas.isEmpty()) {
            System.out.println("No hay mascotas registradas.");
            return;
        }

        System.out.println("\n--- MASCOTAS REGISTRADAS ---");
        for (Mascota mascota : mascotas) {
            // Polimorfismo: cada subclase responde con su propio sonido, sin instanceof.
            System.out.println(mascota + " | Sonido: " + mascota.hacerSonido());
        }
    }

    private static void consultarTotalVeterinario() {
        Veterinario veterinario = seleccionarVeterinario();
        double total = CLINICA.totalCobradoPorVeterinario(veterinario);

        System.out.println("Total cobrado por " + veterinario.getNombre()
                + " " + veterinario.getApellido() + ": $" + total);
    }

    private static void darDeBajaMascota() {
        Mascota mascota = seleccionarMascota();
        CLINICA.darDeBajaMascota(mascota);
        System.out.println("Mascota dada de baja correctamente.");
    }

    private static Dueno seleccionarDueno() {
        System.out.println("\nDueños:");
        for (int i = 0; i < duenos.size(); i++) {
            System.out.println((i + 1) + ". " + duenos.get(i).getNombre()
                    + " " + duenos.get(i).getApellido());
        }

        int indice = leerEntero("Selecciona un dueño: ") - 1;
        validarIndice(indice, duenos.size());
        return duenos.get(indice);
    }

    private static Mascota seleccionarMascota() {
        List<Mascota> mascotas = CLINICA.listarMascotas();
        if (mascotas.isEmpty()) {
            throw new IllegalStateException("No hay mascotas registradas.");
        }

        System.out.println("\nMascotas:");
        for (int i = 0; i < mascotas.size(); i++) {
            System.out.println((i + 1) + ". " + mascotas.get(i).getNombre()
                    + " (" + mascotas.get(i).getClass().getSimpleName() + ")");
        }

        int indice = leerEntero("Selecciona una mascota: ") - 1;
        validarIndice(indice, mascotas.size());
        return mascotas.get(indice);
    }

    private static Veterinario seleccionarVeterinario() {
        if (veterinarios.isEmpty()) {
            throw new IllegalStateException("No hay veterinarios registrados.");
        }

        System.out.println("\nVeterinarios:");
        for (int i = 0; i < veterinarios.size(); i++) {
            System.out.println((i + 1) + ". " + veterinarios.get(i));
        }

        int indice = leerEntero("Selecciona un veterinario: ") - 1;
        validarIndice(indice, veterinarios.size());
        return veterinarios.get(indice);
    }

    private static Cita seleccionarCita() {
        if (citas.isEmpty()) {
            throw new IllegalStateException("No hay citas registradas.");
        }

        System.out.println("\nCitas:");
        for (int i = 0; i < citas.size(); i++) {
            System.out.println((i + 1) + ". " + citas.get(i));
        }

        int indice = leerEntero("Selecciona una cita: ") - 1;
        validarIndice(indice, citas.size());
        return citas.get(indice);
    }

    private static void validarIndice(int indice, int cantidad) {
        if (indice < 0 || indice >= cantidad) {
            throw new IllegalArgumentException("Selección fuera del rango disponible.");
        }
    }

    private static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String valor = SC.nextLine().trim();
            if (!valor.isEmpty()) {
                return valor;
            }
            System.out.println("El dato no puede estar vacío.");
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(SC.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número entero válido.");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double valor = Double.parseDouble(SC.nextLine().trim());
                if (Double.isFinite(valor)) {
                    return valor;
                }
            } catch (NumberFormatException e) {
                // Se vuelve a solicitar el dato.
            }
            System.out.println("Ingresa un número válido.");
        }
    }

    private static boolean leerBooleano(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String valor = SC.nextLine().trim();
            if (valor.equalsIgnoreCase("s")) {
                return true;
            }
            if (valor.equalsIgnoreCase("n")) {
                return false;
            }
            System.out.println("Responde únicamente s o n.");
        }
    }

    private static LocalDateTime leerFechaHora() {
        while (true) {
            System.out.print("Fecha y hora (yyyy-MM-dd HH:mm): ");
            try {
                return LocalDateTime.parse(SC.nextLine().trim(), FORMATO);
            } catch (DateTimeParseException e) {
                System.out.println("Formato incorrecto. Ejemplo: 2026-11-05 14:30");
            }
        }
    }
}