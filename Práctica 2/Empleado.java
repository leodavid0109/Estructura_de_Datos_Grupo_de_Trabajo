import Estructuras.DoubleList;
import Estructuras.DoubleNode;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Empleado {
    private long cedula;
    private String nombre;
    private Fecha fecha_nac;
    private String ciudad_nac;
    private long tel;
    private String email;
    private Direccion dir;
    private String contrasena;
    private Categoria puesto;
    private DoubleList bandejaEntrada;

    public Empleado(long cedula, String nombre, Fecha fecha_nac, String ciudad_nac, long tel, String email, Direccion dir,DoubleList bandejaEntrada) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.fecha_nac = fecha_nac;
        this.ciudad_nac = ciudad_nac;
        this.tel = tel;
        this.email = email;
        this.dir = dir;
        this.bandejaEntrada = bandejaEntrada;
        this.contrasena = null;
        this.puesto = null;
        this.importBandejaEntrada();
    }

    public Empleado(long cedula, String nombre, Fecha fecha_nac, String ciudad_nac, long tel, String email, Direccion dir, String contrasena, Categoria puesto) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.fecha_nac = fecha_nac;
        this.ciudad_nac = ciudad_nac;
        this.tel = tel;
        this.email = email;
        this.dir = dir;
        this.contrasena = contrasena;
        this.puesto = puesto;
    }

//    Metodos para la bandeja de entrada
    public void importBandejaEntrada(){
        try (Scanner scanner = new Scanner(new File("inbox/"+this.getCedula()+ "BA.txt"))){
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (Long.parseLong(parts[1])== this.getCedula()) {
                    Message message = new Message(parts[0], parts[1], LocalDateTime.parse( parts[2]), parts[3], parts[4]);
                    bandejaEntrada.addFirst(message);
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Bandeja de entrada de: " + this.getCedula() + " no encontrada");
        }
    }

    public void getBandejaEntrada() {
        DoubleNode nodo = this.bandejaEntrada.first();
        if (nodo==null){
            System.out.println("Su bandeja de entrada esta vacia");
        }
        System.out.println("#  De    Para   Titulo         Contenido");
        while (nodo!=null){
            System.out.println("1- "+nodo.getData());
            nodo = nodo.getNext();
        }
    }

    // ToString
    @Override
    public String toString() {
        return "CC: " + cedula + "\nNombre: " + nombre + "\nFecha de nacimiento: " + fecha_nac + "\nCiudad de nacimiento: "
                + ciudad_nac + "\nTeléfono: " + tel + "\nEmail: " + email + "\nDirección: " + dir;
    }

    // Gets y sets
    public long getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public Fecha getFecha_nac() {
        return fecha_nac;
    }

    public String getCiudad_nac() {
        return ciudad_nac;
    }

    public long getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public Direccion getDir() {
        return dir;
    }

    public String getContrasena() {
        return contrasena;
    }

    public Categoria getPuesto() {
        return puesto;
    }




    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha_nac(Fecha fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public void setCiudad_nac(String ciudad_nac) {
        this.ciudad_nac = ciudad_nac;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDir(Direccion dir) {
        this.dir = dir;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setPuesto(Categoria puesto) {
        this.puesto = puesto;
    }
}
