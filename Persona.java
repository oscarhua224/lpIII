package num2;

/**
 * Clase base abstracta que representa a cualquier persona del sistema
 * (estudiante o profesor).
 * <p>
 * Concentra los atributos e implementaciones comunes a todas las personas
 * (DNI, nombre, correo, teléfono) y define el método abstracto
 * {@link #getRol()}, que cada subclase debe implementar para indicar su rol.
 * <p>
 * {@code mostrarInformacion()} y {@code enviarNotificacion(String)} están
 * implementados aquí una sola vez y reutilizan {@link #getRol()} para
 * personalizar su salida (patrón <i>Template Method</i>): esto evita que
 * {@link Estudiante} y {@link Profesor} dupliquen el mismo formato de texto.
 */
public abstract class Persona implements Notificable {

 private String dni;
 private String nombre;
 private String correo;
 private String telefono;

 /**
  * Crea una nueva persona con los datos comunes a estudiantes y profesores.
  *
  * @param dni      documento de identidad
  * @param nombre   nombre completo
  * @param correo   correo electrónico de contacto
  * @param telefono número de teléfono de contacto
  */
 public Persona(String dni, String nombre, String correo, String telefono) {
     this.dni = dni;
     this.nombre = nombre;
     this.correo = correo;
     this.telefono = telefono;
 }

 // Getters y setters
 public String getDni() {
     return dni;
 }

 public void setDni(String dni) {
     this.dni = dni;
 }

 public String getNombre() {
     return nombre;
 }

 public void setNombre(String nombre) {
     this.nombre = nombre;
 }

 public String getCorreo() {
     return correo;
 }

 public void setCorreo(String correo) {
     this.correo = correo;
 }

 public String getTelefono() {
     return telefono;
 }

 public void setTelefono(String telefono) {
     this.telefono = telefono;
 }

 /**
  * Indica el rol de esta persona dentro del sistema (por ejemplo,
  * {@code "Estudiante"} o {@code "Profesor"}).
  * <p>
  * Cada subclase concreta debe sobrescribir este método; es la pieza
  * variable que permite reutilizar {@code mostrarInformacion()} y
  * {@code enviarNotificacion(String)} sin duplicarlos.
  *
  * @return el nombre del rol, usado para personalizar los mensajes
  */
 public abstract String getRol();

 /**
  * Imprime por consola los datos comunes de la persona, encabezados con
  * su rol específico (obtenido de {@link #getRol()}).
  */
 public void mostrarInformacion() {
     System.out.println("====== Información de " + getRol() + " ======");
     System.out.println("DNI     : " + dni);
     System.out.println("Nombre  : " + nombre);
     System.out.println("Correo  : " + correo);
     System.out.println("Teléfono: " + telefono);
 }

 /**
  * Implementación base de {@link Notificable}, compartida por todas las
  * personas. Usa {@link #getRol()} para etiquetar la notificación sin
  * necesidad de que cada subclase la reimplemente.
  *
  * @param mensaje contenido de la notificación a enviar
  */
 @Override
 public void enviarNotificacion(String mensaje) {
     System.out.println("[Notificación -> " + getRol() + "]");
     System.out.println("Para    : " + nombre + " (" + correo + ")");
     System.out.println("Mensaje : " + mensaje);
     System.out.println("-----------------------------------");
 }
}