import java.io.*;
import java.util.*;

public class Registro {
    private Empleado registro[];
    private int noRegistro = 0;

    public Registro(int capacidad) {
        this.registro = new Empleado[capacidad];
    }

    public boolean agregar(Empleado usuario) {
        if (noRegistro < registro.length) {
            for (Empleado u : registro) {
                if (u != null && u.getCedula() == usuario.getCedula()) {
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

    public void eliminar(long id) {
        int posicion = retornarPosicion(id);
        if (posicion != -1){
            registro[posicion] = null;
            ajusteRegistro(posicion);
            System.out.println("Usuario con ID " + id + " eliminado.");
        } else {
            System.out.println("No se encuentra un usuario con este ID.");
        }
    }

    public void ajusteRegistro(int posicion){
        for (int i = posicion; i < noRegistro-1; i++){
            registro[i] = registro[i+1];
        }
        registro[noRegistro-1] = null;
        noRegistro--;
    }

    public int retornarPosicion(long cedula){
        for (int i = 0; i < noRegistro; i++) {
            if (registro[i] != null && registro[i].getCedula() == cedula) {
                return i;
            }
        }
        return -1;
    }

    public void buscarUsuarioPorPosicion(int posicion) {
        if (posicion >= 0 && posicion < noRegistro) {
            Empleado usuario = registro[posicion];
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

    public Empleado buscarUsuario(long id) {
        for (int i = 0; i < noRegistro; i++) {
            if (registro[i] != null && registro[i].getCedula() == id) {
                return registro[i];
            }
        }
        return null;
    }

    public void toFileEmpleados(String fileName) {
        String[] encabezados = {"NOMBRE", "CC", "FECHA NACIMIENTO", "CIUDAD NATAL", "TELÉFONO", "EMAIL", "DIRECCIÓN"};
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            writer.write(String.format("%-4s %-20s %-8s %-20s %-20s %-15s %-27s %-80s\n", "#", encabezados[0], encabezados[1], encabezados[2], encabezados[3], encabezados[4], encabezados[5], encabezados[6]));
            writer.print("\n");
            writer.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (int i = 0; i < noRegistro; i++) {
                Empleado usuario = registro[i];
                if (usuario != null) {
                    writer.write(String.format("%-4d %-20s %-8d %-20s %-20s %-15d %-27s %-80s\n", i, usuario.getNombre(), usuario.getCedula(), usuario.getFecha_nac(), usuario.getCiudad_nac(),
                            usuario.getTel(), usuario.getEmail(), usuario.getDir()));
                }
            }
            System.out.println("Datos guardados en el archivo: " + fileName);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public void importFileEmpleados(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            //Descartamos las tres primeras líneas del archivo
            //Estas líneas corresponden a los encabezados, y a las dos líneas de separación para generación de formato como tabla.
            scanner.nextLine();
            scanner.nextLine();
            scanner.nextLine();
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String registro = scanner.nextLine();
                String nombre = registro.substring(4, 25).trim();
                long id = Long.parseLong(registro.substring(25, 33).trim());
                String[] fechaData = registro.substring(33, 53).trim().split("/");
                int dd = Integer.parseInt(fechaData[0]);
                int mm = Integer.parseInt(fechaData[1]);
                int aa = Integer.parseInt(fechaData[2]);
                Fecha fecha = new Fecha(dd, mm, aa);
                String ciudadNac = registro.substring(53, 73).trim();
                long tel = Long.parseLong(registro.substring(73, 88).trim());
                String email = registro.substring(88, 115).trim();
                String[] direccion = registro.substring(115).trim().split(", ");
                String calle = direccion[0].substring(6).trim();
                int noCalle = Integer.parseInt(direccion[1].substring(4).trim());
                String nomenclatura = direccion[2];
                String barrio = direccion[3];
                String ciudad = direccion[4];
                Direccion direccion_final = new Direccion(calle, noCalle, nomenclatura, barrio, ciudad);
                Empleado empleado = new Empleado(id, nombre, fecha, ciudadNac, tel, email, direccion_final);
                agregar(empleado);
            }
            System.out.println("Datos importados desde el archivo: " + fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        }
    }

    // Método de lectura de archivo con el estilo de impresión enviado en la Práctica
    // Su uso inicial es el de permitir la transición de este estilo de archivo al nuevo donde las tablas son implementadas.
    public void importFileEmpleadosP(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String[] registro = scanner.nextLine().split(" ");
                String nombre = registro[0];
                long id = Long.parseLong(registro[1]);
                int dd = Integer.parseInt(registro[2]);
                int mm = Integer.parseInt(registro[3]);
                int aa = Integer.parseInt(registro[4]);
                Fecha fecha = new Fecha(dd, mm, aa);
                String ciudadNac = registro[5];
                long tel = Long.parseLong(registro[6]);
                String email = registro[7];
                String[] partesCalle = registro[8].split("(?<=\\D)(?=\\d)");
                String calle = partesCalle[0];
                int noCalle = Integer.parseInt(partesCalle[1]);
                String nomenclatura = registro[9];
                String barrio = registro[10];
                String ciudad = registro[11];
                Direccion direccion_final = new Direccion(calle, noCalle, nomenclatura, barrio, ciudad);
                Empleado empleado = new Empleado(id, nombre, fecha, ciudadNac, tel, email, direccion_final);
                agregar(empleado);
            }
            System.out.println("Datos importados desde el archivo: " + fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        }
    }

    public void toFilePassword(String fileName) {
        String[] encabezados = {"CC", "CONTRASEÑA", "PUESTO"};
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("------------------------------------------------");
            writer.write(String.format("%-4s %-8s %-20s %-20s\n", "#", encabezados[0], encabezados[1], encabezados[2]));
            writer.print("\n");
            writer.println("------------------------------------------------");
            for (int i = 0; i < noRegistro; i++) {
                Empleado usuario = registro[i];
                if (usuario != null) {
                    writer.write(String.format("%-4d %-8d %-20s %-20s\n", i, usuario.getCedula(), usuario.getContrasena(), usuario.getPuesto()));
                }
            }
            System.out.println("Datos guardados en el archivo: " + fileName);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public void importFilePassword(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            //Descartamos las tres primeras líneas del archivo
            //Estas líneas corresponden a los encabezados, y a las dos líneas de separación para generación de formato como tabla.
            scanner.nextLine();
            scanner.nextLine();
            scanner.nextLine();
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                long id = Long.parseLong(linea.substring(4, 13).trim());
                String contrasena = linea.substring(13, 33).trim();
                String categoria = linea.substring(33, 53).trim();
                for (int i = 0; i < noRegistro; i++){
                    if (id == registro[i].getCedula()){
                        registro[i].setContrasena(contrasena);
                        switch (categoria) {
                            case "Empleado":
                                registro[i].setPuesto(Categoria.Empleado);
                            case "Administrador":
                                registro[i].setPuesto(Categoria.Administrador);
                        }
                    }
                }
            }
            System.out.println("Datos importados desde el archivo: " + fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        }
    }

    // Método de lectura de archivo con el estilo de impresión enviado en la Práctica
    // Su uso inicial es el de permitir la transición de este estilo de archivo al nuevo donde las tablas son implementadas.
    public void importFilePasswordP(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String[] linea = scanner.nextLine().split(" ");
                long id = Long.parseLong(linea[0]);
                String contrasena = linea[1];
                String categoria = linea[2];
                for (int i = 0; i < noRegistro; i++){
                    if (id == registro[i].getCedula()){
                        registro[i].setContrasena(contrasena);
                        switch (categoria) {
                            case "empleado":
                                registro[i].setPuesto(Categoria.Empleado);
                            case "administrador":
                                registro[i].setPuesto(Categoria.Administrador);
                        }
                    }
                }
            }
            System.out.println("Datos importados desde el archivo: " + fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        }
    }

    public int getNoRegistro() {
        return noRegistro;
    }

    public Empleado[] getRegistro() {
        return registro;
    }
}