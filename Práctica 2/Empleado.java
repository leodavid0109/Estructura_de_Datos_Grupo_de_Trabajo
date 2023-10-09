import Estructuras.DoubleList;
import Estructuras.DoubleNode;

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

    public Empleado(long cedula, String nombre, Fecha fecha_nac, String ciudad_nac, long tel, String email, Direccion dir) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.fecha_nac = fecha_nac;
        this.ciudad_nac = ciudad_nac;
        this.tel = tel;
        this.email = email;
        this.dir = dir;
        this.bandejaEntrada =  new DoubleList();
        this.contrasena = null;
        this.puesto = null;
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
        this.bandejaEntrada =  new DoubleList();

    }



    public void mostrarBandejaEntrada() {
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
    public DoubleList getBandejaEntrada(){return bandejaEntrada;}

    public void agregarBandejaEntrada(Message mensaje){
        this.bandejaEntrada.addFirst(mensaje);
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
