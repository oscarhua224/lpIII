package num2;

/**
 * Contrato para cualquier entidad del sistema que pueda recibir notificaciones.
 * <p>
 * Se implementa en {@link Persona}, de modo que tanto {@link Estudiante} como
 * {@link Profesor} heredan un comportamiento base sin duplicar código
 * (ver el método {@code enviarNotificacion} en {@link Persona}).
 */
public interface Notificable {

    /**
     * Envía una notificación con el mensaje indicado al destinatario.
     *
     * @param mensaje contenido de la notificación a enviar
     */
    void enviarNotificacion(String mensaje);
}
