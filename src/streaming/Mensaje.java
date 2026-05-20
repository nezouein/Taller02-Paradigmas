package streaming;

public class Mensaje {
    private final Usuario usuario;
    private final String texto;

    public Mensaje(Usuario usuario, String texto) {
        this.usuario = usuario;
        this.texto = texto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getTexto() {
       return texto;
    }

}
