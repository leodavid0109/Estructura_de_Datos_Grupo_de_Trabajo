import Estructuras.DoubleList;
import Estructuras.DoubleNode;

import java.io.*;
import java.util.*;

public class Registro {
    private DoubleList registro;


    public Registro() {
        this.registro = new DoubleList();
    }

    public boolean agregar(Empleado usuario) {
        DoubleNode nodo = this.first();
        Empleado u = null;
        if (nodo!=null){u= (Empleado) nodo.getData();}else{u=null;}
//        Recorremos nodo por nodo y luego asignamos a u el objeto almacenado en ese nodo
        while (nodo!=null) {
            if (u.getCedula() == usuario.getCedula()) {
                System.out.println("Ya existe un usuario con el mismo número de identificación.");
                return false;
            }
            nodo = nodo.getNext();
            if (nodo!=null){u= (Empleado) nodo.getData();}else{u=null;}
        }
        this.addFirst(usuario);
        return true;
    }

    public void eliminar(long cedula) {
        DoubleNode nodo = buscarNodoCedula(cedula);
        if(nodo!=null){
            this.registro.remove(nodo);
            System.out.println("Se elimino el usuario con docuemnto"+ cedula + "correctamente");
        }else{
            System.out.println("No se encuentra un usuario con este número de documento");
        }
    }

    public DoubleNode buscarNodoCedula(long cedula){
        DoubleNode nodo = this.registro.first();
        Empleado usuario = (Empleado) nodo.getData();
        while (nodo!=null){
            if (usuario.getCedula() == cedula) {
                return nodo;
            }else{
                nodo.getNext();
                usuario = (Empleado) nodo.getData();
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
            DoubleNode nodo = this.registro.first();
            Empleado usuario = null;
            if (nodo!=null){usuario= (Empleado) nodo.getData();}
            int i =1;
            while (usuario !=null) {
                writer.write(String.format("%-4d %-20s %-8d %-20s %-20s %-15d %-27s %-80s\n", i, usuario.getNombre(), usuario.getCedula(), usuario.getFecha_nac(), usuario.getCiudad_nac(),
                    usuario.getTel(), usuario.getEmail(), usuario.getDir()));
                nodo = nodo.getNext();
                if (nodo!=null){usuario= (Empleado) nodo.getData();}else{usuario=null;}
                i++;
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
            writer.println("------------------------------------------------");
            writer.write(String.format("%-4s %-8s %-20s %-20s\n", "#", encabezados[0], encabezados[1], encabezados[2]));
            writer.print("\n");
            writer.println("------------------------------------------------");
            DoubleNode nodo = this.registro.first();
            Empleado usuario = null;
            if (nodo!=null){usuario= (Empleado) nodo.getData();}else{usuario=null;}
            int i =1;
            while (usuario !=null) {
                writer.write(String.format("%-4d %-8d %-20s %-20s\n", i, usuario.getCedula(), usuario.getContrasena(), usuario.getPuesto()));
                nodo = nodo.getNext();
                if (nodo!=null){usuario= (Empleado) nodo.getData();}else{usuario = null;}
                i++;
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

                DoubleNode nodo = this.registro.first();
                Empleado usuario = (Empleado) nodo.getData();
                while (usuario !=null) {
                    if(id == usuario.getCedula()){
                        usuario.setContrasena(contrasena);
                        switch (categoria){
                            case "Empleado":
                                usuario.setPuesto(Categoria.Empleado);
                            case "Administrador":
                                usuario.setPuesto(Categoria.Administrador);
                        }
                    }
                    nodo = nodo.getNext();
                    usuario = (Empleado) nodo.getData();
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

                DoubleNode nodo = this.registro.first();
                Empleado usuario = null;
                if (nodo!=null){usuario= (Empleado) nodo.getData();}
                while (usuario !=null) {
                    if(id == usuario.getCedula()){
                        usuario.setContrasena(contrasena);
                        switch (categoria){
                            case "empleado":
                                usuario.setPuesto(Categoria.Empleado);
                            case "administrador":
                                usuario.setPuesto(Categoria.Administrador);
                        }
                    }
                    nodo = nodo.getNext();
                    if (nodo!=null){usuario= (Empleado) nodo.getData();}else{usuario=null;}
                }
            }
            System.out.println("Datos importados desde el archivo: " + fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        }
    }

    public void addFirst(Empleado e){this.registro.addFirst(e);}
    public void addLast(Empleado e){this.registro.addLast(e);}
    public void removeFirst(){ this.registro.removeFirst();}

    public void removeLast(){ this.registro.removeLast();}

    public void remove(DoubleNode n){ this.registro.remove(n);}

    public void addAfter(DoubleNode n,Empleado e){ this.registro.addAfter(n,e);}



    public Boolean isEmpty(){ return this.registro.isEmpty();}

    public DoubleNode first(){ return this.registro.first();}

    public DoubleNode last(){
        return this.registro.last();
    }


    public int size(){ return this.registro.size();}

}