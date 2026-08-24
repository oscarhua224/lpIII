package num2;

import java.util.ArrayList;

/**
 * Representa a un profesor del sistema. Extiende {@link Persona} y agrega
 * su especialidad y la lista de cursos que tiene a cargo.
 * <p>
 * La relación con {@link Curso} es de agregación: un profesor puede existir
 * sin tener cursos asignados, y los cursos siguen existiendo si se elimina
 * al profesor. {@code cursosDictados} se mantiene sincronizado con
 * {@link Curso#asignarProfesor(Profesor)}, que llama internamente a
 * {@link #asignarCurso(Curso)}.
 */
public class Profesor extends Persona {

 private String especialidad;
 private ArrayList<Curso> cursosDictados;

 /**
  * Crea un nuevo profesor. La lista de cursos dictados inicia vacía.
  *
  * @param dni          documento de identidad
  * @param nombre       nombre completo
  * @param correo       correo electrónico de contacto
  * @param telefono     número de teléfono de contacto
  * @param especialidad área de especialización del profesor
  */
 public Profesor(String dni, String nombre, String correo, String telefono, String especialidad) {
     super(dni, nombre, correo, telefono);
     this.especialidad = especialidad;
     this.cursosDictados = new ArrayList<>();
 }

 // Getters y setters
 public String getEspecialidad() {
     return especialidad;
 }

 public void setEspecialidad(String especialidad) {
     this.especialidad = especialidad;
 }

 public ArrayList<Curso> getCursosDictados() {
     return cursosDictados;
 }

 /**
  * {@inheritDoc}
  *
  * @return siempre {@code "Profesor"}
  */
 @Override
 public String getRol() {
     return "Profesor";
 }

 /**
  * Agrega un curso a la lista de cursos que dicta este profesor, si no
  * está ya registrado.
  * <p>
  * Normalmente no se llama directamente: {@link Curso#asignarProfesor}
  * invoca este método para mantener la relación sincronizada en ambos
  * sentidos.
  *
  * @param curso curso a agregar; si es {@code null} no se realiza ninguna acción
  */
 public void asignarCurso(Curso curso) {
     if (curso != null && !cursosDictados.contains(curso)) {
         cursosDictados.add(curso);
     }
 }
}