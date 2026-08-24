package num2;

/**
 * Representa a un estudiante del sistema. Extiende {@link Persona} y agrega
 * los datos propios de su condición académica (carrera, semestre y
 * promedio).
 * <p>
 * La relación con los cursos en los que está inscrito no se almacena aquí:
 * es {@link Curso} quien mantiene su propia lista de estudiantes inscritos
 * (agregación), evitando duplicar la misma información en ambos lados.
 */
public class Estudiante extends Persona {

 private String carrera;
 private int semestreActual;
 private double promedioAcumulado;

 /**
  * Crea un nuevo estudiante.
  *
  * @param dni               documento de identidad
  * @param nombre            nombre completo
  * @param correo            correo electrónico de contacto
  * @param telefono          número de teléfono de contacto
  * @param carrera           carrera que cursa
  * @param semestreActual    semestre o año académico actual
  * @param promedioAcumulado promedio ponderado acumulado
  */
 public Estudiante(String dni, String nombre, String correo, String telefono,
                   String carrera, int semestreActual, double promedioAcumulado) {
     super(dni, nombre, correo, telefono);
     this.carrera = carrera;
     this.semestreActual = semestreActual;
     this.promedioAcumulado = promedioAcumulado;
 }

 public String getCarrera() {
     return carrera;
 }

 public void setCarrera(String carrera) {
     this.carrera = carrera;
 }

 public int getSemestreActual() {
     return semestreActual;
 }

 public void setSemestreActual(int semestreActual) {
     this.semestreActual = semestreActual;
 }

 public double getPromedioAcumulado() {
     return promedioAcumulado;
 }

 public void setPromedioAcumulado(double promedioAcumulado) {
     this.promedioAcumulado = promedioAcumulado;
 }

 /**
  * {@inheritDoc}
  *
  * @return siempre {@code "Estudiante"}
  */
 @Override
 public String getRol() {
     return "Estudiante";
 }
}