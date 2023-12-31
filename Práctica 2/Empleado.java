import Estructuras.DoubleList;
import Estructuras.DoubleNode;
import Estructuras.Queue;
import Estructuras.Stack;

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
    private Queue correosLeidos;
    private Stack borradores;

    public Empleado(long cedula, String nombre, Fecha fecha_nac, String ciudad_nac, long tel, String email, Direccion dir) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.fecha_nac = fecha_nac;
        this.ciudad_nac = ciudad_nac;
        this.tel = tel;
        this.email = email;
        this.dir = dir;
        this.contrasena = null;
        this.puesto = null;
        this.bandejaEntrada = new DoubleList();
        this.correosLeidos = new Queue();
        this.borradores = new Stack();
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
        this.correosLeidos = new Queue();
        this.borradores = new Stack();
    }

    public void agregarMensajeBandejaEntrada(Message mensaje){
        bandejaEntrada.addFirst(mensaje);
    }

    public void agregarMensajeBorradores(Message mensaje){
        borradores.push(mensaje);
    }

    public void mostrarBandejaEntrada() {
        DoubleNode nodo = bandejaEntrada.first();
        if (nodo == null){
            System.out.println("Su bandeja de entrada esta vacía");
            return;
        }

        String[] indices_tabla = {"FECHA", "TITULO", "REMITENTE"};
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%4s %35s %40s %20s",
                "#", indices_tabla[0], indices_tabla[1], indices_tabla[2]);
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        int i = 1;
        while (nodo != null){
            Message correo = (Message) nodo.getData();
            System.out.printf("%4d %35s %40s %20s",
                    i, correo.getFecha(), correo.getTitulo(), correo.getRemitente().getNombre());
            System.out.println();
            nodo = nodo.getNext();
            i++;
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public DoubleNode buscarNodoBandejaEntrada(int posicion){
        DoubleNode nodo = bandejaEntrada.first();
        int i = 1;
        while (i < posicion){
            nodo = nodo.getNext();
            i++;
        }
        return nodo;
    }

    // ToString
    @Override
    public String toString() {
        return "Nombre: " + nombre + "\nCC: " + cedula +  "\nFecha de nacimiento: " + fecha_nac + "\nCiudad de nacimiento: "
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

    public Queue getCorreosLeidos() {
        return correosLeidos;
    }

    public Stack getBorradores() {
        return borradores;
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

    public void setBandejaEntrada(DoubleList bandejaEntrada) {
        this.bandejaEntrada = bandejaEntrada;
    }

    public void setCorreosLeidos(Queue correosLeidos) {
        this.correosLeidos = correosLeidos;
    }

    public void setBorradores(Stack borradores) {
        this.borradores = borradores;
    }
}
