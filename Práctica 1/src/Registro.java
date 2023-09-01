
import java.io.*;
import java.util.*;

public class Registro {
	private Usuario registro[];
	private int noRegistro = 0;

	public Registro(int capacidad) {
		this.registro = new Usuario[capacidad];
	}

	public boolean agregar(Usuario usuario) {
		if (noRegistro < registro.length) {
			for (Usuario u : registro) {
				if (u != null && u.getId() == usuario.getId()) {
					System.out.println("Ya existe un usuario con el mismo número de identificación.");
					return false;
				}
			}
			registro[noRegistro++] = usuario;
			return true;
		} else {
			System.out.println("El registro está lleno, no se puede agregar más usuarios.");
			return false;
		}
	}

	public void eliminar(int posicion) {
		if (posicion >= 0 && posicion < noRegistro) {
			registro[posicion] = null;
			System.out.println("Usuario en la posición " + posicion + " eliminado.");
		} else {
			System.out.println("Posición inválida.");
		}
	}

	public void buscarUsuarioPorPosicion(int posicion) {
		if (posicion >= 0 && posicion < noRegistro) {
			Usuario usuario = registro[posicion];
			if (usuario != null) {
				System.out.println("Usuario en la posición " + posicion + ":");
				System.out.println(usuario.toString());
			} else {
				System.out.println("No hay usuario en la posición " + posicion);
			}
		} else {
			System.out.println("Posición inválida.");
		}
	}

	public Usuario buscarUsuario(long id) {
		for (int i = 0; i < noRegistro; i++) {
			if (registro[i] != null && registro[i].getId() == id) {
				return registro[i];
			}
		}
		return null;
	}

	public void toFile(String fileName) {
		String[] encabezados = {"ID", "NOMBRE", "FECHA NACIMIENTO", "CIUDAD NATAL", "DIRECCIÓN", "TELÉFONO", "EMAIL"};
		try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
			writer.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			writer.write(String.format("%-4s %-8s %-20s %-20s %-20s %-80s %-15s %-27s\n", "#", encabezados[0], encabezados[1], encabezados[2], encabezados[3], encabezados[4], encabezados[5], encabezados[6]));
			writer.print("\n");
			writer.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			for (int i = 0; i < noRegistro; i++) {
				Usuario usuario = registro[i];
				if (usuario != null) {
					writer.write(String.format("%-4d %-8d %-20s %-20s %-20s %-80s %-15d %-27s\n", i, usuario.getId(), usuario.getNombre(), usuario.getFecha_nac(), usuario.getCiudad_nac(), usuario.getDir(),
							usuario.getTel(), usuario.getEmail()));
				}
			}
			System.out.println("Datos guardados en el archivo: " + fileName);
		} catch (IOException e) {
			System.out.println("Error al escribir en el archivo: " + e.getMessage());
		}
	}

	public void importFile(String fileName) {
		try (Scanner scanner = new Scanner(new File(fileName))) {
			while (scanner.hasNextLine()) {
				String[] data = scanner.nextLine().split(",");
				long id = Long.parseLong(data[0]);
				String nombre = data[1];
				String[] fechaData = data[2].split("/");
				int dd = Integer.parseInt(fechaData[0]);
				int mm = Integer.parseInt(fechaData[1]);
				int aa = Integer.parseInt(fechaData[2]);
				Fecha fecha = new Fecha(dd, mm, aa);
				String ciudadNac = data[3];
				String calle = data[4];
				int noCalle = Integer.parseInt(data[5]);
				String nomenclatura = data[6];
				String barrio = data[7];
				String ciudad = data[8];
				Direccion direccion = new Direccion(calle, noCalle, nomenclatura, barrio, ciudad);
				long tel = Long.parseLong(data[9]);
				String email = data[10];
				Usuario usuario = new Usuario(id, nombre, fecha, ciudadNac, direccion, tel, email);
				agregar(usuario);
			}
			System.out.println("Datos importados desde el archivo: " + fileName);
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado: " + e.getMessage());
		}
	}

	public int getNoRegistro() {
		return noRegistro;
	}

	public Usuario[] getRegistro() {
		return registro;
	}
}
