# Escenario de demostración

Ejecución del escenario obligatorio del examen con el menú de consola de PetCare.

## 1. Menú principal

![Menú principal](capturas/captura1.jpg)

El menú se repite hasta que el usuario elige la opción 10.

## 2. Validación de entradas

![Validación de entradas](capturas/captura2.jpg)

Un texto donde se espera un número y una opción fuera del menú muestran un aviso y el programa continúa.

## 3. Registro de dueños y mascotas

![Registro de dueño y mascota](capturas/captura3.jpg)

Se registran dos dueños y tres mascotas: un perro, un gato y un ave exótica.

## 4. Registro de veterinarios

![Registro de veterinario](capturas/captura4.jpg)

Se registran dos veterinarios y se agregan a la clínica.

## 5. Citas agendadas

![Citas agendadas](capturas/captura5.jpg)

Se agendan tres citas: una consulta, una vacunación y una cirugía.

## 6. Regla de negocio violada

![Regla de negocio](capturas/captura6.jpg)

Un veterinario no puede tener dos citas programadas a la misma hora. El sistema rechaza la cita con un mensaje claro.

## 7. Estado inválido de una cita

![Estado inválido](capturas/captura7.jpg)

Una cita que ya fue atendida no puede cancelarse.

## 8. Comprobante

![Comprobante](capturas/captura8.jpg)

Al atender la cita se genera un comprobante con folio, mascota, veterinario y total.

## 9. Polimorfismo

![Polimorfismo](capturas/captura9.jpg)

Una lista de `Mascota` llama a `hacerSonido()` y cada tipo responde con su propio sonido, sin usar `instanceof`.

## 10. Total cobrado por veterinario

![Total por veterinario](capturas/captura10.jpg)

La clínica suma los comprobantes de las citas de cada veterinario.

## 11. Historial clínico

![Historial clínico](capturas/captura11.jpg)

Cada mascota guarda en su historial los servicios que ha recibido.

## 12. Baja de una mascota

![Baja de mascota](capturas/captura12.jpg)

Al dar de baja una mascota se cancelan sus citas programadas y deja de aparecer en la lista.