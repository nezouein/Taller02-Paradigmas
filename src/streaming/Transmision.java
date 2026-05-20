package streaming;

import java.util.ArrayList;
import java.util.List;

public class Transmision {

    private String titulo;
    private Usuario profesor;
    private boolean activa;
    private List<Usuario> asistentes;
    private List<Mensaje> mensajes;
    private List<TransmisionListener> listeners;

    public Transmision(String titulo, Usuario profesor) {
        this.titulo = titulo;
        this.profesor = profesor;
        this.activa = false;
        this.asistentes = new ArrayList<>();
        this.mensajes = new ArrayList<>();
        this.listeners = new ArrayList<>();
    }

    public void agregarListener(TransmisionListener listener) {
        listeners.add(listener);
    }

    public void iniciar() {
        activa = true;

        for (TransmisionListener listener : listeners) {
            listener.onTransmisionIniciada(this);
        }
    }

    public void unirUsuario(Usuario usuario) {
        asistentes.add(usuario);

        for (TransmisionListener listener : listeners) {
            listener.onUsuarioUnido(this, usuario);
        }
    }

    public void enviarMensaje(Usuario usuario, String texto) {
        Mensaje mensaje = new Mensaje(usuario, texto);
        mensajes.add(mensaje);

        for (TransmisionListener listener : listeners) {
            listener.onMensajeEnviado(this, mensaje);
        }
    }

    public List<Usuario> obtenerEstudiantes() {
        List<Usuario> estudiantes = new ArrayList<>();
        for (Usuario usuario : asistentes) {
            if (usuario.esEstudiante()) {
                estudiantes.add(usuario);
            }
        }
        return estudiantes;
    }

    public List<String> obtenerNombresAsistentes() {
        List<String> nombres = new ArrayList<>();
        for (Usuario usuario : asistentes) {
            nombres.add(usuario.getNombre());
        }
        return nombres;
    }

    public void mostrarEstadisticas() {
        System.out.println("\n=== ESTADÍSTICAS DE LA TRANSMISIÓN ===");
        System.out.println("Título: " + titulo);
        System.out.println("Profesor: " + profesor.getNombre());
        System.out.println("Total de asistentes: " + asistentes.size());
        System.out.println("Total de mensajes: " + mensajes.size());

        int estudiantesConectados = 0;
        for (Usuario usuario : asistentes) {
            if (usuario.esEstudiante()) {
                estudiantesConectados++;
            }
        }
        System.out.println("Estudiantes conectados: " + estudiantesConectados);

        System.out.println("\n=== MENSAJES ===");
        for (Mensaje mensaje : mensajes) {
            System.out.println(mensaje.getUsuario().getNombre() + ": " + mensaje.getTexto());
        }
    }

    public void mostrarAsistentes() {
        System.out.println("\n=== ASISTENTES ===");
        for (Usuario usuario : asistentes) {
            System.out.println("- " + usuario.getNombre() + " (" + usuario.getRol() + ")");
        }
    }

    public String getTitulo() {
        return titulo;
    }
    
    public Usuario getProfesor() {
        return profesor;
    }
    
    public boolean isActiva() {
        return activa;
    }

    public List<Usuario> getAsistentes() {
        return asistentes;
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }
    
    public void finalizarTransmision() {
        activa = false;
        System.out.println("[EVENTO] Transmisión finalizada");
    }
}