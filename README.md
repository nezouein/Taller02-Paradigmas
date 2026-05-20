# Taller02-Paradigmas
# 1.Identificar los paradigmas utilizados
| Transmision           | OPP/Eventos |
|-----------------------|-------------|
| Usuario               | OPP         |
| Mensaje               | OPP         |
| ChatComponent         | OOP         |
| NotificationComponent | OPP         |
| AttendanceComponent   | OPP         |
| Main                  | Funcional   |
| StasService           | Funcional   |
| TransmisionListener   | Eventos/OPP |
#  2 Justificacion 
# ** Usuario** : Tiene clases con atributos y metodos
# ** Mensaje**: Tiene clases con atributos y metodos
# ** ChatComponent ** : Tiene clases con atributos y metodos
# **NotificationComponent** : Tiene clases con atributos y metodos
# ** AttendanceComponent** : Tiene clases con atributos y metodos
# ** Main** : Utiliza stream y funciones lambda 
# ** StasService : Utiliza funciones lambda 
# Transmision: El patron observer aparece aqui ya que notifca a los listeners cuando inicia, se une un usuari o se envia un mensaje
# Cambios hechos **
# Usuario : Se hizo un cambio en el campo rol de tipo enum cuando antes era String  ademas de agregar metodos como lo son esEstudiante, esProfesor, esAyudante 
# Transmision: Se añadio metodos para encapsular logica tales como : obtenerEstudiantes,obtenerNombresAsistentes,mostrarEstadisticas,mostrarAsistentes , ademas de reemplazar el filtrado funcional por bucles "for" dentro de la clase
# Se reemplazo la creación de usuarios con strings por Usuario.Rol, Se elimino el uso de stream(), filter(), map() y Collectors.Ahora usa:transmision.mostrarEstadisticas()transmision.obtenerEstudiantes()transmision.obtenerNombresAsistentes().
# En StatsService se pasó de utilitarios estáticos con streams a delegación a Transmision.
# 3. Diagrama de clases OOP puro
# Se trabajo con uml para poder representar la imagen
```java 
** @startuml
package streaming {

class Usuario {
    - nombre: String
    - rol: Rol
    + Usuario(nombre: String, rol: Rol)
    + getNombre(): String
    + getRol(): Rol
    + esEstudiante(): boolean
    + esProfesor(): boolean
    + esAyudante(): boolean
}

enum Rol {
    PROFESOR
    AYUDANTE
    ESTUDIANTE
}

class Mensaje {
    - usuario: Usuario
    - texto: String
    + Mensaje(usuario: Usuario, texto: String)
    + getUsuario(): Usuario
    + getTexto(): String
}

interface TransmisionListener {
    + onTransmisionIniciada(transmision: Transmision)
    + onUsuarioUnido(transmision: Transmision, usuario: Usuario)
    + onMensajeEnviado(transmision: Transmision, mensaje: Mensaje)
}

class Transmision {
    - titulo: String
    - profesor: Usuario
    - activa: boolean
    - asistentes: List<Usuario>
    - mensajes: List<Mensaje>
    - listeners: List<TransmisionListener>
    + Transmision(titulo: String, profesor: Usuario)
    + agregarListener(listener: TransmisionListener)
    + iniciar()
    + unirUsuario(usuario: Usuario)
    + enviarMensaje(usuario: Usuario, texto: String)
    + obtenerEstudiantes(): List<Usuario>
    + obtenerNombresAsistentes(): List<String>
    + mostrarEstadisticas()
    + mostrarAsistentes()
    + finalizarTransmision()
    + getTitulo(): String
    + getProfesor(): Usuario
    + isActiva(): boolean
    + getAsistentes(): List<Usuario>
    + getMensajes(): List<Mensaje>
}

class ChatComponent {
    + onTransmisionIniciada(transmision: Transmision)
    + onUsuarioUnido(transmision: Transmision, usuario: Usuario)
    + onMensajeEnviado(transmision: Transmision, mensaje: Mensaje)
}

class NotificationComponent {
    + onTransmisionIniciada(transmision: Transmision)
    + onUsuarioUnido(transmision: Transmision, usuario: Usuario)
    + onMensajeEnviado(transmision: Transmision, mensaje: Mensaje)
}

class AttendanceComponent {
    + onTransmisionIniciada(transmision: Transmision)
    + onUsuarioUnido(transmision: Transmision, usuario: Usuario)
    + onMensajeEnviado(transmision: Transmision, mensaje: Mensaje)
}

class StatsService {
    + mostrarEstadisticas(transmision: Transmision)
    + mostrarAsistentes(transmision: Transmision)
}

Transmision "1" o-- "1" Usuario : profesor
Transmision "1" o-- "*" Usuario : asistentes
Transmision "1" o-- "*" Mensaje : mensajes
Transmision "1" o-- "*" TransmisionListener : listeners

TransmisionListener <|.. ChatComponent
TransmisionListener <|.. NotificationComponent
TransmisionListener <|.. AttendanceComponent

StatsService ..> Transmision
StatsService ..> Usuario
StatsService ..> Mensaje

Usuario "1" *-- "1" Rol

}
@enduml
``` 
# Reflexion 
# Al final, escribir una reflexión grupal sobre: ¿Qué se gana cuando obligamos un sistema multiparadigma a convertirse en OOP puro? 
# Una estructura mas compacta, con mayor cohesión de clases y mejor encapsulamiento
# ¿Qué se pierde?
# Lo simple y poder expresivo de ciertos estilos funcionales y la posibilidad de combinar paradigmas que son mas adecuados para el sistema.
