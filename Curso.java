package num2;

import java.util.ArrayList;

/**
 * Representa un curso universitario, con un profesor a cargo y una lista de
 * estudiantes inscritos.
 * <p>
 * Sigue el principio <i>Tell, Don't Ask</i>: el propio {@code Curso} valida
 * su cupo y controla su lista interna de inscritos (ver
 * {@link #inscribirEstudiante(Estudiante)}), en lugar de exponer esa lista
 * para que otra clase la manipule directamente. {@link SistemaGestion} solo
 * delega la operación, no decide por el curso.
 * <p>
 * Mantiene dos contadores estáticos compartidos por todas las instancias:
 * {@link #totalCursosCreados} y {@link #totalInscripciones}.
 */
public class Curso {

 /** Cupo máximo asignado por defecto cuando no se especifica uno explícito. */
 public static final int CUPO_POR_DEFECTO = 30;

 private static int totalCursosCreados = 0;
 private static int totalInscripciones = 0;

 private String nombre;
 private String codigo;
 private String categoria;
 private int cupoMaximo;
 private Profesor profesorACargo; // Agregación: el profesor existe independientemente del curso
 private ArrayList<Estudiante> estudiantesInscritos; // Agregación: los estudiantes existen independientemente del curso

 /**
  * Crea un nuevo curso con un cupo máximo específico.
  *
  * @param nombre     nombre del curso
  * @param codigo     código único que identifica al curso
  * @param categoria  categoría o área a la que pertenece (por ejemplo, "Programación")
  * @param cupoMaximo número máximo de estudiantes que pueden inscribirse
  */
 public Curso(String nombre, String codigo, String categoria, int cupoMaximo) {
     this.nombre = nombre;
     this.codigo = codigo;
     this.categoria = categoria;
     this.cupoMaximo = cupoMaximo;
     this.estudiantesInscritos = new ArrayList<>();
     totalCursosCreados++;
 }

 /**
  * Crea un nuevo curso usando el cupo máximo por defecto
  * ({@link #CUPO_POR_DEFECTO}).
  *
  * @param nombre    nombre del curso
  * @param codigo    código único que identifica al curso
  * @param categoria categoría o área a la que pertenece
  */
 public Curso(String nombre, String codigo, String categoria) {
     this(nombre, codigo, categoria, CUPO_POR_DEFECTO);
 }

 // Getters y setters
 public String getNombre() {
     return nombre;
 }

 public void setNombre(String nombre) {
     this.nombre = nombre;
 }

 public String getCodigo() {
     return codigo;
 }

 public void setCodigo(String codigo) {
     this.codigo = codigo;
 }

 public String getCategoria() {
     return categoria;
 }

 public void setCategoria(String categoria) {
     this.categoria = categoria;
 }

 public int getCupoMaximo() {
     return cupoMaximo;
 }

 public void setCupoMaximo(int cupoMaximo) {
     this.cupoMaximo = cupoMaximo;
 }

 public Profesor getProfesorACargo() {
     return profesorACargo;
 }

 public ArrayList<Estudiante> getEstudiantesInscritos() {
     return estudiantesInscritos;
 }

 /**
  * @return la cantidad total de cursos creados en toda la ejecución del programa
  */
 public static int getTotalCursosCreados() {
     return totalCursosCreados;
 }

 /**
  * @return la cantidad total de inscripciones exitosas realizadas en
  *         todos los cursos del sistema
  */
 public static int getTotalInscripciones() {
     return totalInscripciones;
 }

 /**
  * @return la cantidad de estudiantes actualmente inscritos en este curso
  */
 public int getCantidadEstudiantes() {
     return estudiantesInscritos.size();
 }

 /**
  * Indica si el curso todavía tiene espacio para más estudiantes.
  *
  * @return {@code true} si la cantidad de inscritos es menor al cupo máximo
  */
 public boolean tieneCupoDisponible() {
     return getCantidadEstudiantes() < cupoMaximo;
 }

 /**
  * Intenta inscribir a un estudiante en este curso.
  * <p>
  * Aplica <i>Tell, Don't Ask</i>: es el propio curso quien valida que el
  * estudiante no sea {@code null}, que no esté ya inscrito y que haya
  * cupo disponible, en lugar de exponer su lista interna para que otra
  * clase decida por él. Solo incrementa {@link #totalInscripciones}
  * cuando la inscripción se realiza con éxito.
  *
  * @param e estudiante a inscribir
  * @return {@code true} si la inscripción fue exitosa; {@code false} si
  *         el estudiante es {@code null}, ya estaba inscrito o no hay cupo
  */
 public boolean inscribirEstudiante(Estudiante e) {
     if (e == null) {
         System.out.println("Error: estudiante inválido.");
         return false;
     }
     if (estudiantesInscritos.contains(e)) {
         System.out.println("El estudiante " + e.getNombre() + " ya está inscrito en " + nombre);
         return false;
     }
     if (!tieneCupoDisponible()) {
         System.out.println("No hay cupo disponible en el curso " + nombre);
         return false;
     }
     estudiantesInscritos.add(e);
     totalInscripciones++;
     System.out.println("Estudiante " + e.getNombre() + " inscrito correctamente en " + nombre);
     return true;
 }

 /**
  * Asigna un profesor a cargo de este curso y sincroniza la relación en
  * ambos sentidos, agregando este curso a la lista de cursos dictados
  * del profesor.
  *
  * @param p profesor a asignar
  */
 public void asignarProfesor(Profesor p) {
     this.profesorACargo = p;
     if (p != null) {
         p.asignarCurso(this); // bidireccional
     }
 }

 /**
  * @return una representación legible del curso, incluyendo su ocupación
  *         actual (inscritos/cupo) y el profesor a cargo
  */
 @Override
 public String toString() {
     String prof = (profesorACargo != null) ? profesorACargo.getNombre() : "Sin asignar";
     return String.format(
         "Curso{código=%s, nombre=%s, categoría=%s, cupo=%d/%d, profesor=%s}",
         codigo, nombre, categoria, getCantidadEstudiantes(), cupoMaximo, prof
     );
 }
}