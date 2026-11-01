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

(pendiente)

### Paquete modelo.clinica

(pendiente)

## 2. Objetos de ejemplo

(pendiente)

## 3. Clases descartadas

(pendiente)