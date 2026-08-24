package num2;

import java.util.List;

/**
 * Punto de entrada del programa. Ejercita el flujo completo del Sistema
 * de Gestión de Cursos: registro de personas y cursos, asignación de
 * profesores, inscripción de estudiantes (incluyendo un caso de cupo
 * lleno) y visualización de resultados mediante polimorfismo.
 */
public class Main {
 public static void main(String[] args) {

     System.out.println("======= SISTEMA DE GESTIÓN DE CURSOS UNIVERSITARIOS =======\n");

     // 1. Crear el sistema orquestador
     SistemaGestion sistema = new SistemaGestion();

     // 2. Crear profesores
     Profesor prof1 = new Profesor("10000001", "Dr. Carlos Ramírez",
             "carlos.ramirez@uni.edu", "987654321", "Programación");
     Profesor prof2 = new Profesor("10000002", "Dra. Ana Torres",
             "ana.torres@uni.edu", "987654322", "Matemáticas");

     // 3. Crear estudiantes
     Estudiante est1 = new Estudiante("70000001", "Luis Fernández",
             "luis.f@uni.edu", "999111222", "Ingeniería de Sistemas", 3, 15.5);
     Estudiante est2 = new Estudiante("70000002", "María Gómez",
             "maria.g@uni.edu", "999111333", "Ingeniería Civil", 5, 17.2);
     Estudiante est3 = new Estudiante("70000003", "Pedro Salazar",
             "pedro.s@uni.edu", "999111444", "Ingeniería de Sistemas", 2, 14.0);
     Estudiante est4 = new Estudiante("70000004", "Lucía Díaz",
             "lucia.d@uni.edu", "999111555", "Ingeniería Industrial", 7, 18.1);

     // 4. Crear cursos
     Curso curso1 = new Curso("Programación Orientada a Objetos", "POO101",
             "Programación", 3); // cupo pequeño para pruebas
     Curso curso2 = new Curso("Álgebra Lineal", "MAT201",
             "Matemáticas"); // usa CUPO_POR_DEFECTO = 30
     Curso curso3 = new Curso("Estructuras de Datos", "ED102",
             "Programación", 2);

     // 5. Registrar en el sistema
     System.out.println("--- Registro de profesores ---");
     sistema.registrarProfesor(prof1);
     sistema.registrarProfesor(prof2);

     System.out.println("\n--- Registro de estudiantes ---");
     sistema.registrarEstudiante(est1);
     sistema.registrarEstudiante(est2);
     sistema.registrarEstudiante(est3);
     sistema.registrarEstudiante(est4);

     System.out.println("\n--- Registro de cursos ---");
     sistema.registrarCurso(curso1);
     sistema.registrarCurso(curso2);
     sistema.registrarCurso(curso3);

     // 6. Asignar profesores a cursos
     System.out.println("\n--- Asignación de profesores ---");
     sistema.asignarProfesor("10000001", "POO101");
     sistema.asignarProfesor("10000002", "MAT201");
     sistema.asignarProfesor("10000001", "ED102");

     // 7. Inscribir estudiantes (polimorfismo con enviarNotificacion)
     System.out.println("\n--- Inscripción de estudiantes ---");
     sistema.inscribirEstudiante("70000001", "POO101");
     sistema.inscribirEstudiante("70000002", "POO101");
     sistema.inscribirEstudiante("70000003", "POO101");
     sistema.inscribirEstudiante("70000004", "POO101"); // No debe pasar (cupo=3)

     System.out.println();
     sistema.inscribirEstudiante("70000001", "MAT201");
     sistema.inscribirEstudiante("70000002", "MAT201");
     sistema.inscribirEstudiante("70000003", "ED102");
     sistema.inscribirEstudiante("70000004", "ED102");

     // 8. Mostrar información usando polimorfismo (mostrarInformacion + getRol)
     System.out.println("\n--- Información de personas (polimorfismo) ---");
     est1.mostrarInformacion();
     System.out.println();
     prof1.mostrarInformacion();

     // 9. Mostrar resumen de cursos y matriculados
     sistema.mostrarResumenCursos();

     // 10. Listar cursos disponibles (con cupo)
     System.out.println("--- Cursos disponibles (con cupo) ---");
     List<Curso> disponibles = sistema.listarCursosDisponibles();
     for (Curso c : disponibles) {
         System.out.println(c);
     }

     // 11. Mostrar cursos que dicta un profesor
     System.out.println("\n--- Cursos del Profesor " + prof1.getNombre() + " ---");
     for (Curso c : prof1.getCursosDictados()) {
         System.out.println("  - " + c.getNombre() + " (" + c.getCodigo() + ")");
     }

     System.out.println("\n======= FIN DEL SISTEMA =======");
 }
}