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
    
    public void mostrarEstadisticas() {
        System.out.println("\n=== ESTADÍSTICAS DE LA TRANSMISIÓN ===");
        System.out.println("Título: " + this.titulo);
        System.out.println("Profesor: " + this.profesor.getNombre());
        System.out.println("Total de asistentes: " + this.asistentes.size());
        System.out.println("Total de mensajes: " + this.mensajes.size());

        int estudiantes = 0;
        for (Usuario u : this.asistentes) {
            if ("estudiante".equals(u.getRol())) {
                estudiantes++;
            }
        }
        System.out.println("Estudiantes conectados: " + estudiantes);

        System.out.println("\n=== MENSAJES ===");
        for (Mensaje m : this.mensajes) {
            System.out.println(m.getUsuario().getNombre() + ": " + m.getTexto());
        }
    }

    public void mostrarAsistentes() {
        System.out.println("\n=== ASISTENTES ===");
        for (Usuario u : this.asistentes) {
            System.out.println("- " + u.getNombre() + " (" + u.getRol() + ")");
        }
    }
    
    public void finalizarTransmision() {
        activa = false;
        for (TransmisionListener listener : listeners) {
            listener.onTransmisionFinalizada(this);
        }
    }
}