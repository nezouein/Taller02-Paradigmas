# Taller02-Paradigmas

## 0. Integrantes
- Nejeh Zouein
- Jose Navarrete

## 1. Tabla de identificación de paradigmas
| Archivo Java               | Tipo de paradigma   |
|----------------------------|---------------------|
| Usuario.java               | Orientada a Objetos |
| Mensaje.java               | Orientado a Objetos |
| Transmision.java           | Orientado a Objetos |
| TransmisionListener.java   | Orientado a Objetos |
| ChatComponent.java         | Orientado a Eventos |
| AttendanceComponent.java   | Orientado a Eventos |
| NotificationComponent.java | Orientado a Eventos |
| StatsService.java          | Funcional           |
| Main.java                  | Orientado a Objetos + Funcional |

## 2. Justificación breve
- **Usuario.java:** Ya que es una clase con atributos privados, constructores y getters.
- **Mensaje.java:** Ya que es un objeto de datos con encapsulamientos.
- **Transmision.java:** Mantienen su estado interno y se definen métodos que actúan sobre ellos. 
- **TransmisionListener.java:** Ya que es abstraccion para las clases y no una función aislada.
- **ChatComponent.java:** Reacciona ante eventos de transmision mediante respuestas a acciones. 
- **AttendanceComponent.java:** Este reacciona al momento de ingreso de un usuario y de inicio de transmisión.
- **NotificationComponent.java:** Implementa las notificaciones basadas en eventos del sistema.
- **StatsService.java:** Usa streams, lambdas y métodos estáticos procesado las colecciones del sistema.
- **Main.java:** Aquí se crea objetos y obtiene la ejecución de la aplicación.


## 3. Modificaciones realizadas
- **TransmitionListener:** Se convirtió a `public abstract` para eliminar las dependencias en as interfaces.
- **ChatComponent, AttendanceComponent y NotificationComponent:** Ahora extiende de `TransmitionListener` para adaptarlo como clase y mantener el polimorfismo.
- **Transmision:** Ahora llama a un listener para que la llamada sea directamente al mismi para evitar lógica procedural.
- **Usuario y Mensaje:** Convertidos en public y final para que estas sean inmutables.
- **StatsServices:** Convertido en clase de instancia para reemplazar el uso de streams.

## 4. Diagrama de clases
*(Se tuvo que trabajar en UML para que se pueda representar gráficamente bien)*
```java
@startuml
class Transmision {
  - titulo: String
  - profesor: Usuario
  - asistentes: List<Usuario>
  - mensajes: List<Mensaje>
  + iniciar()
  + unirUsuario(u)
  + enviarMensaje(u,t)
  + finalizar()
}

class Usuario {
  - nombre: String
  - rol: String
}

class Mensaje {
  - usuario: Usuario
  - texto: String
}

abstract class TransmisionListener {
  + onTransmisionIniciada(t)
  + onUsuarioUnido(t,u)
  + onMensajeEnviado(t,m)
}

class ChatComponent
class AttendanceComponent
class NotificationComponent

Transmision "1" --> "1" Usuario : profesor
Transmision "1" o-- "0..*" Usuario : asistentes
Transmision "1" o-- "0..*" Mensaje : mensajes
Transmision "1" o-- "0..*" TransmisionListener : listeners

ChatComponent --|> TransmisionListener
AttendanceComponent --|> TransmisionListener
NotificationComponent --|> TransmisionListener
@enduml
```

## 5. Refactorización de una parte del codigo
Se cambió:
```java
public class StatsService {
    public static void mostrarEstadisticas(Transmision transmision) {
        long estudiantes = transmision.getAsistentes().stream()
            .filter(u -> "estudiante".equals(u.getRol()))
            .count();
        transmision.getMensajes().forEach(m ->
            System.out.println(m.getUsuario().getNombre() + ": " + m.getTexto())
        );
    }
}
```
para que sea movible a la clase Transmisión, oara que el comportamiento permanezca al mismo objeto y sea testeable.


## 6. Preguntas
1. ¿Qué se gana cuando obligamos un sistema multiparadigma a convertirse en OOP puro?

Obtenemos una limpia en la estructura del código, para que este sea comprensible y se queden bien definidos, adicionalmente de fortalecer el encapsulamiento, haciendo que el código sea mas testeable y mantenible.

2. ¿Qué se pierde?

Se pierde operaciones con funciones que se escriben en una línea que requieren de bucles. Asi mismo, se pierde flexibilidad y rendimiento del mismo, ralentizando un poco los cambios rápidos a largo plazo.