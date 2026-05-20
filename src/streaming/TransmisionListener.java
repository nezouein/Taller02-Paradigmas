package streaming;

public abstract class TransmisionListener {
    public void onTransmisionIniciada(Transmision transmision) {}
    public void onUsuarioUnido(Transmision transmision, Usuario usuario) {}
    public void onMensajeEnviado(Transmision transmision, Mensaje mensaje) {}
    public void onTransmisionFinalizada(Transmision transmision) {}
}