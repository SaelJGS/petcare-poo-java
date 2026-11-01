# Análisis de clases y objetos: PetCare

## 1. Tabla de clases

### Paquete modelo.personas

| Clase candidata | Tipo | Atributos principales | Responsabilidades (métodos) | Justificación |
| --- | --- | --- | --- | --- |
| Persona | Abstracta | # nombre: String, # apellido: String, # telefono: String, # correo: String | # Persona(String nombre, String apellido, String telefono, String correo)<br>+ getNombre(): String<br>+ getApellido(): String<br>+ getTelefono(): String<br>+ getCorreo(): String<br>+ setNombre(String nombre): void<br>+ setApellido(String apellido): void<br>+ setTelefono(String telefono): void<br>+ setCorreo(String correo): void<br>+ toString(): String | Representa lo que tienen en común las personas de la clínica (dueños, veterinarios y recepcionistas); es abstracta porque solo aporta atributos y comportamiento heredados y nunca se instancia; su única responsabilidad es guardar y validar los datos de contacto comunes. |
| Dueno | Concreta | - direccion: String, - mascotas: List&lt;Mascota&gt; | + Dueno(String nombre, String apellido, String telefono, String correo, String direccion)<br>+ getDireccion(): String<br>+ setDireccion(String direccion): void<br>+ getMascotas(): List&lt;Mascota&gt;<br>+ agregarMascota(Mascota mascota): void<br>+ quitarMascota(Mascota mascota): void<br>+ toString(): String | Representa a la persona dueña de una o más mascotas; es concreta porque se instancia con sus datos propios (dirección) además de los heredados de Persona; su única responsabilidad es guardar sus datos propios y llevar el registro de sus mascotas. |
| Veterinario | Concreta | - cedulaProfesional: String, - especialidad: String | + Veterinario(String nombre, String apellido, String telefono, String correo, String cedulaProfesional, String especialidad)<br>+ getCedulaProfesional(): String<br>+ setCedulaProfesional(String cedulaProfesional): void<br>+ getEspecialidad(): String<br>+ setEspecialidad(String especialidad): void<br>+ toString(): String | Representa a un veterinario que trabaja en la clínica; es concreta porque se instancia con sus datos profesionales (cédula y especialidad) además de los heredados de Persona; su única responsabilidad es guardar y validar sus datos profesionales. |
| Recepcionista | Concreta | - turno: String | + Recepcionista(String nombre, String apellido, String telefono, String correo, String turno)<br>+ getTurno(): String<br>+ setTurno(String turno): void<br>+ toString(): String | Representa a un recepcionista que trabaja en la clínica; es concreta porque se instancia con su turno además de los datos heredados de Persona; su única responsabilidad es guardar y validar su turno. |

### Paquete modelo.mascotas

| Clase candidata | Tipo | Atributos principales | Responsabilidades (métodos) | Justificación |
| --- | --- | --- | --- | --- |
| Mascota | Abstracta | # nombre: String, # edad: double, # peso: double, # dueno: Dueno, - historial: HistorialClinico | # Mascota(String nombre, double edad, double peso, Dueno dueno)<br>+ getNombre(): String<br>+ getEdad(): double<br>+ getPeso(): double<br>+ getDueno(): Dueno<br>+ getHistorial(): HistorialClinico<br>+ setNombre(String nombre): void<br>+ setEdad(double edad): void<br>+ setPeso(double peso): void<br>+ abstract hacerSonido(): String<br>+ toString(): String | Representa lo que tienen en común el perro, el gato y el ave exótica; es abstracta porque no existe una mascota genérica y porque declara hacerSonido() sin cuerpo, que cada hija implementa a su manera; su única responsabilidad es guardar y validar los datos comunes de la mascota y crear su historial clínico. |
| Perro | Concreta | - raza: String, - nivelEnergia: int | + Perro(String nombre, double edad, double peso, Dueno dueno, String raza, int nivelEnergia)<br>+ getRaza(): String<br>+ setRaza(String raza): void<br>+ getNivelEnergia(): int<br>+ setNivelEnergia(int nivelEnergia): void<br>+ hacerSonido(): String<br>+ toString(): String | Representa a un perro que se atiende en la clínica; es concreta porque implementa hacerSonido() y se instancia con atributos propios (raza y nivel de energía), además de los heredados de Mascota; su única responsabilidad es guardar y validar los datos propios del perro y definir su sonido. |
| Gato | Concreta | - esDomestico: boolean | + Gato(String nombre, double edad, double peso, Dueno dueno, boolean esDomestico)<br>+ esDomestico(): boolean<br>+ setEsDomestico(boolean esDomestico): void<br>+ hacerSonido(): String<br>+ toString(): String | Representa a un gato que se atiende en la clínica; es concreta porque implementa hacerSonido() y se instancia con un atributo propio (si es doméstico), además de los heredados de Mascota; su única responsabilidad es guardar si el gato es doméstico y definir su sonido. |
| AveExotica | Concreta | - longitudAlas: double | + AveExotica(String nombre, double edad, double peso, Dueno dueno, double longitudAlas)<br>+ getLongitudAlas(): double<br>+ setLongitudAlas(double longitudAlas): void<br>+ hacerSonido(): String<br>+ toString(): String | Representa a un ave exótica que se atiende en la clínica; es concreta porque implementa hacerSonido() y se instancia con un atributo propio (longitud de alas), además de los heredados de Mascota; su única responsabilidad es guardar y validar la longitud de alas y definir su sonido. |
| HistorialClinico | Concreta | - registros: List&lt;Facturable&gt; | ~ HistorialClinico()<br>+ agregarRegistro(Facturable registro): void<br>+ listarRegistros(): List&lt;Facturable&gt; | Representa el registro de los servicios que ha recibido una mascota; es concreta porque se instancia y no tiene hijas; su única responsabilidad es guardar y entregar esos registros, sin calcular cobros ni agendar citas. Es una composición: la crea la mascota en su constructor y no existe sin ella. |

### Paquete modelo.servicios

| Clase candidata | Tipo | Atributos principales | Responsabilidades (métodos) | Justificación |
| --- | --- | --- | --- | --- |
| Facturable | Interfaz | (ninguno) | + calcularCosto(): double | Representa la capacidad de cobrarse que comparten los servicios de la clínica; es interfaz porque consulta, vacunación y cirugía no comparten atributos, solo la capacidad de calcular su costo; su única responsabilidad es declarar el contrato de cobro sin implementarlo. |
| Consulta | Concreta | - TARIFA_BASE: double (static final) = 300.0, - motivo: String, - diagnostico: String | + Consulta()<br>+ Consulta(String motivo, String diagnostico)<br>+ getMotivo(): String<br>+ setMotivo(String motivo): void<br>+ getDiagnostico(): String<br>+ setDiagnostico(String diagnostico): void<br>+ calcularCosto(): double<br>+ toString(): String | Representa una consulta general que se le hace a una mascota; es concreta porque implementa el contrato de Facturable y se instancia con sus datos propios (motivo y diagnóstico); su única responsabilidad es guardar y validar esos datos y calcular su costo con la tarifa base. |
| Vacunacion | Concreta | - TARIFA_APLICACION: double (static final) = 100.0, - nombreVacuna: String, - costoVacuna: double | + Vacunacion(String nombreVacuna, double costoVacuna)<br>+ getNombreVacuna(): String<br>+ setNombreVacuna(String nombreVacuna): void<br>+ getCostoVacuna(): double<br>+ setCostoVacuna(double costoVacuna): void<br>+ calcularCosto(): double<br>+ toString(): String | Representa una vacunación aplicada a una mascota; es concreta porque implementa el contrato de Facturable y se instancia con sus datos propios (nombre y costo de la vacuna); su única responsabilidad es guardar y validar esos datos y calcular su costo sumando la tarifa de aplicación y el costo de la vacuna. |
| Cirugia | Concreta | - COSTO_HORA_QUIROFANO: double (static final) = 800.0, - complejidad: String, - horasQuirofano: double | + Cirugia(String complejidad, double horasQuirofano)<br>+ getComplejidad(): String<br>+ setComplejidad(String complejidad): void<br>+ getHorasQuirofano(): double<br>+ setHorasQuirofano(double horasQuirofano): void<br>+ calcularCosto(): double<br>+ toString(): String | Representa una cirugía que se le practica a una mascota; es concreta porque implementa el contrato de Facturable y se instancia con sus datos propios (complejidad y horas de quirófano); su única responsabilidad es guardar y validar esos datos y calcular su costo a partir de ambos. |

### Paquete modelo.clinica

| Clase candidata | Tipo | Atributos principales | Responsabilidades (métodos) | Justificación |
| --- | --- | --- | --- | --- |
| Comprobante | Concreta | - contadorComprobantes: int (static) = 0, - folio: int, - cita: Cita, - total: double | + Comprobante(Cita cita)<br>+ getFolio(): int<br>+ getCita(): Cita<br>+ getTotal(): double<br>+ static getTotalGenerados(): int<br>+ toString(): String | Representa el comprobante que se genera al terminar una cita atendida; es concreta porque se instancia y no tiene hijas; su única responsabilidad es guardar el folio, la cita y el total cobrado, y llevar la cuenta de los comprobantes generados. |
| EstadoCita | Enum | PROGRAMADA, ATENDIDA, CANCELADA | (ninguno) | Representa los estados posibles de una cita; es un enum porque el conjunto de valores es fijo y cerrado (programada, atendida o cancelada); su única responsabilidad es restringir el estado de la cita a esos valores, sin lógica propia. |
| Cita | Concreta | - mascota: Mascota, - veterinario: Veterinario, - consultorio: Consultorio, - servicio: Facturable, - fechaHora: LocalDateTime, - estado: EstadoCita | + Cita(Mascota mascota, Veterinario veterinario, Consultorio consultorio, Facturable servicio, LocalDateTime fechaHora)<br>+ getMascota(): Mascota<br>+ getVeterinario(): Veterinario<br>+ getConsultorio(): Consultorio<br>+ getServicio(): Facturable<br>+ getFechaHora(): LocalDateTime<br>+ getEstado(): EstadoCita<br>+ atender(): void<br>+ cancelar(): void<br>+ generarComprobante(): Comprobante<br>+ toString(): String | Representa el encuentro entre una mascota y un veterinario, en un consultorio y en un momento dado; es concreta porque se instancia y no tiene hijas; su única responsabilidad es guardar esa relación y controlar su estado (programada, atendida o cancelada), incluida la generación de su comprobante. |
| Consultorio | Concreta | - numero: int | ~ Consultorio(int numero)<br>+ getNumero(): int<br>+ toString(): String | Representa un consultorio numerado de la clínica, donde ocurren las citas; es concreta porque se instancia y no tiene hijas; su única responsabilidad es guardar y validar su número. Es una composición: la clínica lo crea y desaparece con ella. |
| Clinica | Concreta | - nombre: String, - consultorios: List&lt;Consultorio&gt;, - veterinarios: List&lt;Veterinario&gt;, - duenos: List&lt;Dueno&gt;, - citas: List&lt;Cita&gt;, - comprobantes: List&lt;Comprobante&gt; | + Clinica(String nombre, int cantidadConsultorios)<br>+ getNombre(): String<br>+ agregarVeterinario(Veterinario veterinario): void<br>+ registrarDueno(Dueno dueno): void<br>+ agendarCita(Mascota mascota, Veterinario veterinario, int numeroConsultorio, Facturable servicio, LocalDateTime fechaHora): Cita<br>+ atenderCita(Cita cita): void<br>+ cancelarCita(Cita cita): void<br>+ generarComprobante(Cita cita): Comprobante<br>+ buscarCita(String nombreMascota): List&lt;Cita&gt;<br>+ buscarCita(LocalDate fecha): List&lt;Cita&gt;<br>+ listarMascotas(): List&lt;Mascota&gt;<br>+ darDeBajaMascota(Mascota mascota): void<br>+ totalCobradoPorVeterinario(Veterinario veterinario): double<br>+ cobrosPorMascota(Mascota mascota): List&lt;Comprobante&gt;<br>+ toString(): String | Representa a la clínica veterinaria PetCare; es concreta porque se instancia y no tiene hijas; su única responsabilidad es coordinar a sus partes: crea sus consultorios (composición), recibe veterinarios ya creados (agregación) y gestiona dueños, citas y comprobantes, aplicando las reglas de agenda. Los cálculos y validaciones propios de cada dato los hace la clase correspondiente. |

## 2. Objetos de ejemplo

### Objeto 1: un perro (herencia de Mascota)

```text
miPerro: Perro
- nombre = "Chamoy"
- edad = 4.0
- peso = 18.5
- dueno = "Sael Garduza"
- historial = HistorialClinico (vacío, creado por la mascota)
- raza = "Labrador"
- nivelEnergia = 4
hacerSonido() devuelve "Gua Gua"
```

### Objeto 2: un veterinario (herencia de Persona)

```text
miVeterinario: Veterinario
- nombre = "Luis"
- apellido = "Ramírez"
- telefono = "9211234567"
- correo = "luis.ramirez@petcare.com"
- cedulaProfesional = "12345678"
- especialidad = "Medicina general"
```

### Objeto 3: una vacunación (servicio Facturable)

```text
miVacunacion: Vacunacion
- TARIFA_APLICACION = 100.0 (constante de la clase)
- nombreVacuna = "Antirrábica"
- costoVacuna = 150.0
calcularCosto() devuelve 100.0 + 150.0 = 250.0
```

## 3. Clases descartadas

| Clase candidata | Por qué se descartó |
| --- | --- |
| Sonido | No es una entidad con datos ni comportamiento propio: es el resultado de `hacerSonido()`, así que basta un `String`. |
| Telefono / Correo | Son datos simples de contacto sin comportamiento propio; son atributos de `Persona`, validados en sus setters. |
| Cobro | El caso lo menciona, pero ya lo representa `Comprobante`; crear otra clase duplicaría la misma responsabilidad. |


## 4. Justificación de relaciones

### Composición: Mascota e HistorialClinico

Es composición porque el historial no tiene sentido sin su mascota: la mascota lo crea en su constructor, nadie más lo comparte y, si la mascota se elimina del sistema, su historial desaparece con ella. No es agregación porque en una agregación la parte podría existir por sí sola, y un historial sin mascota no.

### Composición: Clinica y Consultorio

Es composición porque los consultorios pertenecen a la clínica: ella los crea dentro de su constructor, nadie más los crea ni los comparte y, si la clínica desaparece, sus consultorios desaparecen con ella. No es agregación porque un consultorio no puede existir ni usarse fuera de su clínica.

### Agregación: Clinica y Veterinario

Es agregación porque la clínica agrupa veterinarios que existen por sí mismos: se reciben ya creados con agregarVeterinario() y, si la clínica se elimina, siguen existiendo y pueden trabajar en otra. No es composición porque la clínica no los crea ni es su única dueña.

### Asociación: Dueno y Mascota

Es asociación porque dueño y mascota se crean cada uno por su cuenta y ninguno es parte del otro: solo se conocen (el dueño guarda su lista de mascotas y cada mascota guarda a su dueño). Si se da de baja una mascota, el dueño sigue existiendo, y un dueño puede existir con cero mascotas. No es agregación ni composición porque no hay una relación de "todo y parte".

### Asociaciones: Cita con Mascota, Veterinario, Consultorio y Facturable

Son asociaciones porque la cita solo guarda referencias a una mascota, un veterinario, un consultorio y un servicio que ya existen: los recibe en su constructor y no los crea. Si la cita se cancela o desaparece, los cuatro siguen existiendo. No es composición porque ninguno es parte de la cita, ni agregación porque la cita no agrupa a varios: cada referencia es una relación de uso.

### Asociación: Comprobante y Cita

Es asociación porque el comprobante solo guarda una referencia a la cita de la que sale: la cita ya existía antes, el comprobante la recibe en su constructor y no la crea. Si el comprobante se elimina, la cita sigue existiendo con su estado atendida. No es composición porque la cita no es parte del comprobante.

### Asociación: Clinica y Dueno

Es asociación porque el dueño es un cliente que la clínica registra, no parte de su estructura: el dueño existe por su cuenta, lo recibe ya creado en registrarDueno() y podría ser cliente de otra clínica. La clínica puede existir sin dueños y el dueño sigue existiendo si se le quita del registro. No es agregación porque no forma parte del equipo de la clínica, como sí ocurre con los veterinarios.

### Asociaciones: Clinica con Cita y Comprobante

Son asociaciones porque la clínica solo lleva el registro de sus citas y comprobantes: el comprobante lo crea la cita y la clínica solo guarda su referencia, y la cita, aunque la crea la clínica al agendar, también la referencia el comprobante y apunta a objetos que existen por su cuenta. Por eso no son partes exclusivas de la clínica, y no es composición.

### Asociación: HistorialClinico y Facturable

Es asociación porque el historial solo guarda referencias a servicios (consultas, vacunaciones o cirugías) que se crearon por separado al agendar la cita: los recibe en agregarRegistro() y no los crea. Si el historial desaparece, los servicios siguen existiendo porque la cita también los referencia. No es composición porque un servicio no es parte exclusiva del historial.