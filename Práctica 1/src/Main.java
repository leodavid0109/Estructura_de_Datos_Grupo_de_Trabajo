
import java.util.Scanner;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Registro registro = new Registro(10);
		Scanner scanner = new Scanner(System.in);

		// Menú de opciones
		int opcion;
		do {
			System.out.println("==== Menú de Registro de Usuarios ====");
			System.out.println("1. Agregar un nuevo usuario");
			System.out.println("2. Consultar usuario por ID");
			System.out.println("3. Eliminar un usuario");
			System.out.println("4. Mostrar lista de usuarios");
			System.out.println("5. Buscar usuario por posición");
			System.out.println("6. Guardar en archivo");
			System.out.println("7. Cargar desde archivo");
			System.out.println("0. Salir");
			System.out.print("Ingrese la opción deseada: ");
			opcion = scanner.nextInt();

			switch (opcion) {
			case 1:
				agregarNuevoUsuario(registro, scanner);
				ordenarRegistroPorId(registro);
				break;
			case 2:
				System.out.print("Ingrese el ID del usuario a consultar: ");
				long idConsulta = scanner.nextLong();
				Usuario usuarioConsulta = registro.buscarUsuario(idConsulta);
				if (usuarioConsulta != null) {
					System.out.println("Usuario encontrado: " + usuarioConsulta.toString());
				} else {
					System.out.println("Usuario no encontrado.");
				}
				break;
			case 3:
				System.out.print("Ingrese el ID del usuario que desea eliminar: ");
				int IDUsuarioEliminar = scanner.nextInt();
				registro.eliminar(IDUsuarioEliminar);
				break;
			case 4:
				mostrarListaUsuarios(registro);
				break;
			case 5:
				System.out.print("Ingrese la posición (índice) del usuario que desea buscar: ");
				int posicion = scanner.nextInt();
				registro.buscarUsuarioPorPosicion(posicion);
				break;
			case 6:
				registro.toFile("Registro de Usuarios.txt");
				break;
			case 7:
				registro.importFile("Registro de Usuarios.txt");
				break;
			case 0:
				System.out.println("Saliendo del programa.");
				break;
			default:
				System.out.println("Opción inválida.");
				break;
			}

		} while (opcion != 0);

		scanner.close();
	}

	// Métodos

	private static boolean registroEstaLleno(Registro registro) {
		// Verificar si el registro está lleno
		return registro.getNoRegistro() >= registro.getRegistro().length;
	}

	private static void mostrarListaUsuarios(Registro registro) {
		System.out.println("==== Lista de Usuarios ====");
		if (registro.getNoRegistro() == 0) {
			System.out.println("No hay usuarios");
			return;
		}
		Main.impresiónUsuarios(registro.getRegistro());
	}

	private static void ordenarRegistroPorId(Registro registro) {
		mergeSort(registro.getRegistro(), 0, registro.getNoRegistro() - 1);
	}

	private static void mergeSort(Usuario arr[], int left, int right) {
		if (left < right) {
			int mid = left + (right - left) / 2;
			mergeSort(arr, left, mid);
			mergeSort(arr, mid + 1, right);
			merge(arr, left, mid, right);
		}
	}

	private static void merge(Usuario arr[], int left, int mid, int right) {
		int n1 = mid - left + 1;
		int n2 = right - mid;

		Usuario L[] = new Usuario[n1];
		Usuario R[] = new Usuario[n2];

		for (int i = 0; i < n1; i++)
			L[i] = arr[left + i];
		for (int j = 0; j < n2; j++)
			R[j] = arr[mid + 1 + j];

		int i = 0, j = 0;
		int k = left;
		while (i < n1 && j < n2) {
			if (L[i].getId() <= R[j].getId()) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}

	private static void agregarNuevoUsuario(Registro registro, Scanner scanner) {
		if (registroEstaLleno(registro)) {
			System.out.println("El registro está lleno, no se puede agregar más usuarios.");
			return;
		}

		System.out.println("==== Agregar un nuevo usuario ====");
		System.out.println("¿Desea crear un usuario con datos random? (S/N)");
		char respuesta = scanner.next().charAt(0);
		if (respuesta == 'S' || respuesta == 's') {
			System.out.print("Ingrese la cantidad de usuarios random que desea crear: ");
			int cantidadUsuarios = scanner.nextInt();
			if (cantidadUsuarios <= 0) {
				System.out.println("Cantidad inválida.");
				return;
			}
			if (registro.getNoRegistro() + cantidadUsuarios > registro.getRegistro().length) {
				System.out.println("La cantidad de usuarios excede la capacidad máxima del registro.");
				return;
			}
			for (int i = 0; i < cantidadUsuarios; i++) {
				agregarUsuarioRandom(registro);
			}
		} else {
			System.out.println("==== Agregar un nuevo usuario ====");
			System.out.print("Ingrese el ID del usuario: ");
			long nuevoId = scanner.nextLong();
			scanner.nextLine(); // Consumir el salto de línea pendiente
			if(registro.buscarUsuario(nuevoId)!=null){
				System.out.println("Ya existe un usuario con id: "+nuevoId);
				return;
			}

			System.out.print("Ingrese el nombre del usuario: ");
			String nuevoNombre = scanner.nextLine();

			System.out.println("Ingrese la fecha de nacimiento (dd mm aa): ");
			int nuevoDia = scanner.nextInt();
			int nuevoMes = scanner.nextInt();
			int nuevoAnio = scanner.nextInt();
			Fecha nuevaFecha = new Fecha(nuevoDia, nuevoMes, nuevoAnio);

			scanner.nextLine(); // Consumir el salto de línea pendiente
			System.out.print("Ingrese la ciudad de nacimiento: ");
			String nuevaCiudadNac = scanner.nextLine();

			System.out.print("Ingrese la dirección (calle, noCalle, nomenclatura, barrio, ciudad): ");
			String[] direccion = scanner.nextLine().split(", ");
			String nuevaCalle = direccion[0];
			int nuevoNoCalle = Integer.parseInt(direccion[1]);
			String nuevaNomenclatura = direccion[2];
			String nuevoBarrio = direccion[3];
			String nuevaCiudad = direccion[4];
			Direccion nuevaDireccion = new Direccion(nuevaCalle, nuevoNoCalle, nuevaNomenclatura, nuevoBarrio,
					nuevaCiudad);

			System.out.print("Ingrese el número de teléfono: ");
			long nuevoTel = scanner.nextLong();

			scanner.nextLine(); // Consumir el salto de línea pendiente
			System.out.print("Ingrese el correo electrónico: ");
			String nuevoEmail = scanner.nextLine();

			Usuario nuevoUsuario = new Usuario(nuevoId, nuevoNombre, nuevaFecha, nuevaCiudadNac, nuevaDireccion,
					nuevoTel, nuevoEmail);
			boolean agregado = registro.agregar(nuevoUsuario);
			if (agregado) {
				System.out.println("Usuario agregado exitosamente.");
			} else {
				System.out.println("No se pudo agregar el usuario.");
			}
		}
	}

	private static void agregarUsuarioRandom(Registro registro) {
		// Generar datos aleatorios
		Random random = new Random();
		long nuevoId = random.nextInt(1000) + 1; // ID entre 1 y 1000
		while(registro.buscarUsuario(nuevoId)!=null){
			nuevoId=nuevoId+1;
		}
		String nuevoNombre = "UsuarioRandom" + nuevoId;
		Fecha nuevaFecha = new Fecha(random.nextInt(28) + 1, random.nextInt(12) + 1, random.nextInt(21) + 2000);
		String nuevaCiudadNac = "CiudadRandom" + (random.nextInt(10) + 1); // Ciudad entre 1 y 10
		Direccion nuevaDireccion = new Direccion("CalleRandom", random.nextInt(500) + 1, "NomenclaturaRandom",
				"BarrioRandom", "CiudadRandom");
		long nuevoTel = 3000000000L + random.nextInt(1000000000); // Teléfono aleatorio
		String nuevoEmail = "usuario" + nuevoId + "@random.com";

		Usuario nuevoUsuario = new Usuario(nuevoId, nuevoNombre, nuevaFecha, nuevaCiudadNac, nuevaDireccion, nuevoTel,
				nuevoEmail);
		boolean agregado = registro.agregar(nuevoUsuario);
		if (agregado) {
			System.out.println("Usuario agregado exitosamente.");
		} else {
			System.out.println("No se pudo agregar el usuario.");
		}
	}
	
	private static void impresiónUsuarios(Usuario[] usuarios){
		String[] indices_tabla = {"ID", "NOMBRE", "FECHA NACIMIENTO", "CIUDAD NATAL", "DIRECCIÓN", "TELÉFONO", "EMAIL"};
		
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%4s %8s %20s %20s %20s %80s %15s %27s", 
				"#", indices_tabla[0], indices_tabla[1], indices_tabla[2], indices_tabla[3], indices_tabla[4], indices_tabla[5], indices_tabla[6]);
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		int i = 0;
		for (Usuario usuario : usuarios) {
			if(usuario == null) {
				break;
			}
			System.out.printf("%4d %8d %20s %20s %20s %80s %15d %27s", 
					i, usuario.getId(), usuario.getNombre(), usuario.getFecha_nac(), usuario.getCiudad_nac(), usuario.getDir(),
					usuario.getTel(), usuario.getEmail());
			System.out.println();
			i++;
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		return;
	}
}
