package ds2v.estudiantes;

import ds2v.estudiantes.modelo.Estudiante;
import ds2v.estudiantes.servicio.EstudianteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@SpringBootApplication
public class EstudiantesApplication implements CommandLineRunner {

	@Autowired
	private EstudianteServicio estudianteServicio;

	private static final Logger logger = LoggerFactory.getLogger(EstudiantesApplication.class);

	String nl = System.lineSeparator();

	public static void main(String[] args) {

		logger.info("Iniciando la Aplicacion!!!");

		SpringApplication.run(EstudiantesApplication.class, args);

		logger.info("Finalizando la Aplicacion!!!" + " ");
	}

	@Override
	public void run(String... args) throws Exception {

		logger.info(nl + "Por aqui vamos corriendo la Aplicacion!!!" + nl);

		var salir = false;
		Scanner consola = new Scanner(System.in);

		while (!salir){
			mostrarMenu();
			salir = ejecutarOperaciones(consola);
		}
	}

	// Metodo para mostrar el Menu
	private void mostrarMenu() {
		logger.info(nl);
		logger.info("""
				***** SISTEMA DE ESTUDIANTES *****
				1. Listar Estudiantes
				2. Agregar Estudiante
				3. Buscar Estudiante
				4. Modificar Estudiante
				5. Eliminar Estudiante
				6. Salir
				Ingrese la opcion deseada:
				""");
	}

	// Metodo para ejecutar la opcion seleccionda!
	private boolean ejecutarOperaciones(Scanner consola) {
		var opcion = Integer.parseInt(consola.nextLine());
		var salir = false;
		switch (opcion){
			case 1 -> { // Lista los estudiantes
				logger.info("LISTADO DE LOS ESTUDIANTES" + nl);
				List<Estudiante> estudiantes = estudianteServicio.listarEstudiante();
				estudiantes.forEach((estudiante -> logger.info(estudiante.toString() + nl)));
			}
			case 2 -> { // Agrega un estudiante
				var estudiante = new Estudiante();
				logger.info("Nombre: ");
				var nombre = consola.nextLine();
				estudiante.setNombre(nombre);
				logger.info("Apellido: ");
				var apellido = consola.nextLine();
				estudiante.setApellido(apellido);
				logger.info("Telefono: ");
				var telefono = consola.nextLine();
				estudiante.setTelefono(telefono);
				logger.info("Email: ");
				var email = consola.nextLine();
				estudiante.setEmail(email);
				estudianteServicio.guardarEstudiante(estudiante);
				logger.info("Estudiante guardado Exitosamente!" + nl);
			}
			case 3 -> { // Busca un estudiante por su ID
				logger.info("Ingrese el ID del estudiante: ");
				var codigo = Integer.parseInt(consola.nextLine());
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(codigo);
				if (estudiante != null){
					logger.info("Estudiante encontrado" + nl);
					logger.info(estudiante + nl);
				}else
					logger.info("Estudiante con codigo " + codigo + " no encontrado" + nl);
			}
			case 4 -> { // Modifica un estudiante
				logger.info("Ingrese el ID del estudiante a modificar: ");
				var codModif = Integer.parseInt(consola.nextLine());
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(codModif);
				if (estudiante != null){
					logger.info("Nombre: ");
					var nombre = consola.nextLine();
					estudiante.setNombre(nombre);
					logger.info("Apellido: ");
					var apellido = consola.nextLine();
					estudiante.setApellido(apellido);
					logger.info("Telefono: ");
					var telefono = consola.nextLine();
					estudiante.setTelefono(telefono);
					logger.info("Email: ");
					var email = consola.nextLine();
					estudiante.setEmail(email);
					estudianteServicio.guardarEstudiante(estudiante);
					logger.info("Estudiante modificado Exitosamente!" + nl);
				}else
					logger.info("Estudiante con codigo " + codModif + " no encontrado" + nl);
			}
			case 5 -> { // Elimina un estudiante
				logger.info("Ingrese el ID del estudiante a eliminar: ");
				var codElim = Integer.parseInt(consola.nextLine());
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(codElim);
				if (estudiante != null){
					estudianteServicio.eliminarEstudiante(estudiante);
					logger.info("Estudiante eliminado Exitosamente!" + nl);
				}else
					logger.info("Estudiante con codigo " + codElim + " no encontrado" + nl);
			}
			case 6 -> { // Cierra el programa
				logger.info("Regrese pronto!" + nl);
				salir = true;
			}
			default -> logger.info("Ingrese una opcion valida (1 - 6)" + nl);
		}
		return salir;
	}
}
