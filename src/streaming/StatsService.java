package streaming;

public class StatsService {

    public void instanceMostrarEstadisticas(Transmision transmision) {
        System.out.println("\n=== ESTADÍSTICAS DE LA TRANSMISIÓN ===");
        System.out.println("Título: " + transmision.getTitulo());
        System.out.println("Profesor: " + transmision.getProfesor().getNombre());
        System.out.println("Total de asistentes: " + transmision.getAsistentes().size());
        System.out.println("Total de mensajes: " + transmision.getMensajes().size());

        int estudiantes = 0;
        for (Usuario u : transmision.getAsistentes()) {
            if ("estudiante".equals(u.getRol())) {
                estudiantes++;
            }
        }
        System.out.println("Estudiantes conectados: " + estudiantes);

        System.out.println("\n=== MENSAJES ===");
        for (Mensaje m : transmision.getMensajes()) {
            System.out.println(m.getUsuario().getNombre() + ": " + m.getTexto());
        }
    }

    public void instanceMostrarAsistentes(Transmision transmision) {
        System.out.println("\n=== ASISTENTES ===");
        for (Usuario u : transmision.getAsistentes()) {
            System.out.println("- " + u.getNombre() + " (" + u.getRol() + ")");
        }
    }

    // Static wrappers to preserve original API (Main uses static calls).
    public static void mostrarEstadisticas(Transmision transmision) {
        new StatsService().instanceMostrarEstadisticas(transmision);
    }

    public static void mostrarAsistentes(Transmision transmision) {
        new StatsService().instanceMostrarAsistentes(transmision);
    }
}
