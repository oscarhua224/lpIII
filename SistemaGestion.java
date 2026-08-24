package num2;

import java.util.ArrayList;
import java.util.List;

/**
 * Coordina el conjunto de cursos, estudiantes y profesores del sistema.
 * <p>
 * No representa una entidad del dominio (no es un estudiante, curso o
 * profesor); actúa como orquestador o fachada: busca los objetos
 * involucrados y delega en ellos la responsabilidad de validar y
 * modificar su propio estado (por ejemplo, {@link Curso#inscribirEstudiante}
 * decide si hay cupo, no {@code SistemaGestion}).
 */
public class SistemaGestion {

 private ArrayList<Curso> cursos;
 private ArrayList<Estudiante> estudiantes;
 private ArrayList<Profesor> profesores;

 /** Crea un sistema de gestión con las tres listas internas vacías. */
 public SistemaGestion() {
     this.cursos = new ArrayList<>();
     this.estudiantes = new ArrayList<>();
     this.profesores = new ArrayList<>();
 }

 /**
  * Registra un estudiante en el sistema si no es {@code null} y no está
  * ya registrado.
  *
  * @param e estudiante a registrar
  */
 public void registrarEstudiante(Estudiante e) {
     if (e != null && !estudiantes.contains(e)) {
         estudiantes.add(e);
         System.out.println("Estudiante registrado: " + e.getNombre());
     }
 }

 /**
  * Registra un profesor en el sistema si no es {@code null} y no está
  * ya registrado.
  *
  * @param p profesor a registrar
  */
 public void registrarProfesor(Profesor p) {
     if (p != null && !profesores.contains(p)) {
         profesores.add(p);
         System.out.println("Profesor registrado: " + p.getNombre());
     }
 }

 /**
  * Registra un curso en el sistema si no es {@code null} y no está ya
  * registrado.
  *
  * @param c curso a registrar
  */
 public void registrarCurso(Curso c) {
     if (c != null && !cursos.contains(c)) {
         cursos.add(c);
         System.out.println("Curso registrado: " + c.getNombre());
     }
 }

 /**
  * Busca al estudiante y al curso indicados por sus identificadores y
  * delega en {@link Curso#inscribirEstudiante} la validación de cupo y
  * duplicados. Si la inscripción tiene éxito, notifica al estudiante.
  *
  * @param dniEstudiante DNI del estudiante a inscribir
  * @param codigoCurso   código del curso destino
  * @return {@code true} si la inscripción fue exitosa; {@code false} si
  *         no se encontró alguno de los dos o si el curso la rechazó
  */
 public boolean inscribirEstudiante(String dniEstudiante, String codigoCurso) {
     Estudiante e = buscarEstudiantePorDni(dniEstudiante);
     Curso c = buscarCursoPorCodigo(codigoCurso);
     if (e == null) {
         System.out.println("No se encontró estudiante con DNI: " + dniEstudiante);
         return false;
     }
     if (c == null) {
         System.out.println("No se encontró curso con código: " + codigoCurso);
         return false;
     }
     // Tell, Don't Ask: el curso valida su propio cupo
     boolean exito = c.inscribirEstudiante(e);
     if (exito) {
         e.enviarNotificacion("Te has inscrito correctamente en el curso: " + c.getNombre());
     }
     return exito;
 }

 /**
  * Busca al profesor y al curso indicados por sus identificadores y
  * delega en {@link Curso#asignarProfesor} la asignación. Notifica al
  * profesor si la operación se realiza.
  *
  * @param dniProfesor DNI del profesor a asignar
  * @param codigoCurso código del curso destino
  */
 public void asignarProfesor(String dniProfesor, String codigoCurso) {
     Profesor p = buscarProfesorPorDni(dniProfesor);
     Curso c = buscarCursoPorCodigo(codigoCurso);
     if (p == null) {
         System.out.println("No se encontró profesor con DNI: " + dniProfesor);
         return;
     }
     if (c == null) {
         System.out.println("No se encontró curso con código: " + codigoCurso);
         return;
     }
     c.asignarProfesor(p);
     System.out.println("Profesor " + p.getNombre() + " asignado al curso " + c.getNombre());
     p.enviarNotificacion("Se te ha asignado el curso: " + c.getNombre());
 }

 /**
  * @return la lista de cursos registrados que todavía tienen cupo disponible
  */
 public List<Curso> listarCursosDisponibles() {
     List<Curso> disponibles = new ArrayList<>();
     for (Curso c : cursos) {
         if (c.tieneCupoDisponible()) {
             disponibles.add(c);
         }
     }
     return disponibles;
 }

 /** Imprime por consola un resumen de todos los cursos y los totales del sistema. */
 public void mostrarResumenCursos() {
     System.out.println("\n===== RESUMEN DE CURSOS =====");
     for (Curso c : cursos) {
         System.out.println(c.toString());
     }
     System.out.println("Total cursos creados   : " + Curso.getTotalCursosCreados());
     System.out.println("Total inscripciones    : " + Curso.getTotalInscripciones());
     System.out.println("=============================\n");
 }

 /**
  * Busca un curso registrado por su código (sin distinguir mayúsculas/minúsculas).
  *
  * @param codigo código del curso buscado
  * @return el curso encontrado, o {@code null} si no existe
  */
 public Curso buscarCursoPorCodigo(String codigo) {
     for (Curso c : cursos) {
         if (c.getCodigo().equalsIgnoreCase(codigo)) {
             return c;
         }
     }
     return null;
 }

 /**
  * Busca un estudiante registrado por su DNI (sin distinguir mayúsculas/minúsculas).
  *
  * @param dni DNI del estudiante buscado
  * @return el estudiante encontrado, o {@code null} si no existe
  */
 public Estudiante buscarEstudiantePorDni(String dni) {
     for (Estudiante e : estudiantes) {
         if (e.getDni().equalsIgnoreCase(dni)) {
             return e;
         }
     }
     return null;
 }

 /**
  * Busca un profesor registrado por su DNI (sin distinguir mayúsculas/minúsculas).
  *
  * @param dni DNI del profesor buscado
  * @return el profesor encontrado, o {@code null} si no existe
  */
 public Profesor buscarProfesorPorDni(String dni) {
     for (Profesor p : profesores) {
         if (p.getDni().equalsIgnoreCase(dni)) {
             return p;
         }
     }
     return null;
 }

 // Getters de las listas
 public ArrayList<Curso> getCursos() {
     return cursos;
 }

 public ArrayList<Estudiante> getEstudiantes() {
     return estudiantes;
 }

 public ArrayList<Profesor> getProfesores() {
     return profesores;
 }
}