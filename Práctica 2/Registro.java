import Estructuras.DoubleList;
import Estructuras.DoubleNode;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class Registro {
    private DoubleList registro;

    public Registro() {
        this.registro = new DoubleList();
    }

    public boolean agregar(Empleado empleado) {
        DoubleNode nodo = registro.first();
        while (nodo != null){
            Empleado empleado_aux = (Empleado) nodo.getData();
            if (empleado_aux.getCedula() == empleado.getCedula()){
                System.out.println("Ya existe un usuario con el mismo número de identificación.");
                return false;
            }
            nodo = nodo.getNext();
        }
        registro.addLast(empleado);
        return true;
    }

    public void eliminar(long cedula) {
        DoubleNode nodo = buscarNodoCedula(cedula);
        if(nodo != null){
            registro.remove(nodo);
            System.out.println("Empleado con CC " + cedula + " eliminado.");
        }
        else{
            System.out.println("No se encuentra un usuario con este número de documento");
        }
    }

    public DoubleNode buscarNodoCedula(long cedula){
        DoubleNode nodo = registro.first();
        while (nodo != null){
            Empleado empleado = (Empleado) nodo.getData();
            if (empleado.getCedula() == cedula) {
                return nodo;
            }
            nodo = nodo.getNext();
        }
        return null;
    }

    public void toFileEmpleados(String fileName) {
        String[] encabezados = {"NOMBRE", "CC", "FECHA NACIMIENTO", "CIUDAD NATAL", "TELÉFONO", "EMAIL", "DIRECCIÓN"};
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            writer.write(String.format("%-4s %-20s %-10s %-20s %-20s %-15s %-27s %-80s\n", "#", encabezados[0], encabezados[1], encabezados[2], encabezados[3], encabezados[4], encabezados[5], encabezados[6]));
            writer.print("\n");
            writer.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            DoubleNode nodo = registro.first();
            int i =1;
            while (nodo != null) {
                Empleado empleado = (Empleado) nodo.getData();
                writer.write(String.format("%-4d %-20s %-10d %-20s %-20s %-15d %-27s %-80s\n", i, empleado.getNombre(), empleado.getCedula(), empleado.getFecha_nac(), empleado.getCiudad_nac(),
                    empleado.getTel(), empleado.getEmail(), empleado.getDir()));
                nodo = nodo.getNext();
                i++;
            }
            System.out.println("Datos guardados en el archivo: " + fileName);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    // Método de lectura de archivo para importación de Empleados
    public void importFileEmpleados(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            // Descartamos las tres primeras líneas del archivo
            // Estas líneas corresponden a los encabezados, y a las dos líneas de separación para generación de formato como tabla.
            scanner.nextLine();
            scanner.nextLine();
            scanner.nextLine();
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String registro = scanner.nextLine();
                String nombre = registro.substring(5, 25).trim();
                long id = Long.parseLong(registro.substring(25, 35).trim());
                String[] fechaData = registro.substring(35, 55).trim().split("/");
                int dd = Integer.parseInt(fechaData[0]);
                int mm = Integer.parseInt(fechaData[1]);
                int aa = Integer.parseInt(fechaData[2]);
                Fecha fecha = new Fecha(dd, mm, aa);
                String ciudadNac = registro.substring(55, 75).trim();
                long tel = Long.parseLong(registro.substring(75, 91).trim());
                String email = registro.substring(91, 118).trim();
                String[] direccion = registro.substring(118).trim().split(", ");
                String calle = direccion[0].substring(6).trim();
                int noCalle = Integer.parseInt(direccion[1].substring(4).trim());
                String nomenclatura = direccion[2];
                String barrio = direccion[3];
                String ciudad = direccion[4];
                Direccion direccion_final;
                if (direccion[5].equals("null")){
                    direccion_final = new Direccion(calle, noCalle, nomenclatura, barrio, ciudad);
                }
                else {
                    String urbanizacion = direccion[5];
                    String apartamento = direccion[6].split(": ")[1];
                    direccion_final = new Direccion(calle, noCalle, nomenclatura, barrio, ciudad, urbanizacion, apartamento);
                }

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
                Direccion direccion_final;
                if (registro[12] == "null"){
                    direccion_final = new Direccion(calle, noCalle, nomenclatura, barrio, ciudad);
                }
                else{
                    String urbanizacion = registro[12];
                    String apartamento = registro[13];
                    direccion_final = new Direccion(calle, noCalle, nomenclatura, barrio, ciudad, urbanizacion, apartamento);
                }

                Empleado empleado = new Empleado(id, nombre, fecha, ciudadNac, tel, email, direccion_final);
                this.agregar(empleado);
            }
            System.out.println("Datos importados desde el archivo: " + fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        }
    }

    public void toFilePassword(String fileName) {
        String[] encabezados = {"CC", "CONTRASEÑA", "PUESTO"};
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("----------------------------------------------------");
            writer.write(String.format("%-4s %-10s %-20s %-20s\n", "#", encabezados[0], encabezados[1], encabezados[2]));
            writer.print("\n");
            writer.println("----------------------------------------------------");
            DoubleNode nodo = registro.first();
            int i =1;
            while (nodo !=null) {
                Empleado empleado = (Empleado) nodo.getData();
                writer.write(String.format("%-4d %-10d %-20s %-20s\n", i, empleado.getCedula(), empleado.getContrasena(), empleado.getPuesto()));
                nodo = nodo.getNext();
                i++;
            }
            System.out.println("Datos guardados en el archivo: " + fileName);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    // Método de lectura de archivo para importación de método de ingreso de Empleados (contraseñas)
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

                DoubleNode nodo = registro.first();
                while (nodo !=null) {
                    Empleado empleado = (Empleado) nodo.getData();
                    if(id == empleado.getCedula()){
                        empleado.setContrasena(contrasena);
                        switch (categoria) {
                            case "Empleado" -> empleado.setPuesto(Categoria.Empleado);
                            case "Administrador" -> empleado.setPuesto(Categoria.Administrador);
                        }
                    }
                    nodo = nodo.getNext();
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

                DoubleNode nodo = registro.first();
                while (nodo !=null) {
                    Empleado empleado = (Empleado) nodo.getData();
                    if(id == empleado.getCedula()){
                        empleado.setContrasena(contrasena);
                        switch (categoria) {
                            case "empleado" -> empleado.setPuesto(Categoria.Empleado);
                            case "administrador" -> empleado.setPuesto(Categoria.Administrador);
                        }
                    }
                    nodo = nodo.getNext();
                }
            }
            System.out.println("Datos importados desde el archivo: " + fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        }
    }

    // Métodos para la bandeja de entrada
    public void importBandejaEntrada(){
        DoubleNode nodo = registro.first();
        while (nodo!=null){
            Empleado usuario = (Empleado) nodo.getData();
            try {
                Scanner scanner = new Scanner(new File("inbox/"+usuario.getCedula()+ "BA.txt"));
                scanner.nextLine();
                scanner.nextLine();
                scanner.nextLine();
                scanner.nextLine();
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Empleado de =  (Empleado) this.buscarNodoCedula(Long.parseLong(line.substring(0,9).trim())).getData();
                    Empleado para =  (Empleado) this.buscarNodoCedula(Long.parseLong(line.substring(9,17).trim())).getData();
                    Message mensaje = new Message(de, para, LocalDateTime.parse(line.substring(17,47).trim()),line.substring(47,87).trim(),line.substring(87).trim());
                    usuario.agregarMensajeBandejaEntrada(mensaje);
                }
                scanner.close();
            } catch (IOException e) {
            }
            nodo = nodo.getNext();
        }
    }

//    Cargar bandeja de entrada
    public void toFileInbox() {
        String[] encabezados = {"De", "Para", "Fecha", "titulo", "Contenido", };
        //          Pasamos usuario por usuario para cargar su bandeja de entrada
        DoubleNode nodo = this.registro.first();
        Empleado usuario;
        while (nodo!=null) {
            usuario = (Empleado) nodo.getData();
            try (PrintWriter writer = new PrintWriter(new FileWriter("inbox/" + usuario.getCedula() + "BA.txt"))) {
                writer.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                writer.write(String.format("%-8s %-8s %-30s %-40s %-100s\n", encabezados[0], encabezados[1], encabezados[2], encabezados[3], encabezados[4]));
                writer.print("\n");
                writer.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//              Pasamos mensaje por mensaje y lo guardamos en el archivo
                DoubleNode mensajeNodo = usuario.getBandejaEntrada().first();
                while (mensajeNodo != null) {
                    Message mensaje = (Message) mensajeNodo.getData();
                    writer.write(String.format("%-8s %-8s %-30s %-40s %-100s\n", mensaje.getRemitente().getCedula(), mensaje.getDestinatario().getCedula(), mensaje.getFecha(), mensaje.getTitulo(), mensaje.getContenido() ));
                    mensajeNodo = mensajeNodo.getNext();
                }
                System.out.println("Bandeja de entrada: " + "inbox/" + usuario.getCedula() + "BA.txt");
            } catch (IOException e) {
                System.out.println("Error al exportar bandeja de entrada: " + e.getMessage());
            }
            nodo =nodo.getNext();
        }
    }

    public DoubleList getRegistro() {
        return registro;
    }
}