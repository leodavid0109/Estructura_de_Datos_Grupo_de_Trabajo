import java.util.Scanner;
import Estructuras.*;
public class Main {
    public static void main(String[] args) {

        Registro registro = new Registro();

        // Transición de archivo enviado para la práctica a tipología de archivos propia
//        registro.importFileEmpleadosP("empleadosOriginal.txt");
//        registro.importFilePasswordP("passwordOriginal.txt");
//
//        registro.toFileEmpleados("Empleados.txt");
//        registro.toFilePassword("Password.txt");

        // Importación de archivos de los empleados y sus respectivos datos de ingreso
        registro.importFileEmpleados("Empleados.txt");
        registro.importFilePassword("Password.txt");

//        mostrarListaEmpleados(registro);

        // Importación de todas las bandejas de entradas
        registro.importBandejaEntrada();

        Scanner scanner = new Scanner(System.in);
        System.out.println("==== Aplicación de Mensajería Interna ====");

        boolean loggedIn = false;
        Empleado usuario = null;

        while (!loggedIn){
            System.out.println("INICIO DE SESIÓN");
            System.out.print("Número de Identificación: ");
            long cedula = scanner.nextLong();
            scanner.nextLine();
            System.out.print("Contraseña: ");
            String contrasena = scanner.nextLine();
            System.out.println();


            // Verificacion de credenciales (checkLogin)
            usuario = checkLogin(cedula, contrasena, registro);
            if (usuario != null) {
                loggedIn = true;
                System.out.println("¡Bienvenido!");
            } else {
                System.out.println("¡Error! Las credenciales no coinciden.");
                System.out.println("¿Desea intentar nuevamente? (Y/N)");
                String validacion = scanner.nextLine();
                if (!validacion.equals("Y") && !validacion.equals("y")){
                    scanner.close();
                    return;
                }
            }
        }

//        Inbox inbox = new Inbox(id);

        if (usuario.getPuesto() == Categoria.Empleado){
            boolean running = true;
            while (running) {
                System.out.println("Por favor seleccione una opción:");
                System.out.println("1. Bandeja de Entrada");
                System.out.println("2. Mensajes leídos");
                System.out.println("3. Borradores");
                System.out.println("4. Redactar nuevo mensaje");
                System.out.println("5. Salir");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        bandejaEntrada(usuario);
                    case 2:
                        mensajesLeidos(usuario);
                    case 3:
                        borradores(usuario);
                    case 4:
                        redactarMensaje(usuario, registro);
                    case 5:
                        running = false;
                        registro.toFileInbox();
                        break;
                    default:
                        System.out.println("Entrada no válida. Intente de nuevo");
                }
                System.out.println();
            }
        }
        else{
            boolean running = true;
            while (running) {
                System.out.println("Por favor seleccione una opción:");
                System.out.println("1. Bandeja de Entrada");
                System.out.println("2. Mensajes leídos");
                System.out.println("3. Borradores");
                System.out.println("4. Redactar nuevo mensaje");
                System.out.println("5. Ver usuarios");
                System.out.println("6. Registrar un nuevo usuario");
                System.out.println("7. Cambiar contraseñas");
                System.out.println("8. Eliminar usuario");
                System.out.println("9. Salir");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline character
                switch (choice) {
                    case 1:
                        bandejaEntrada(usuario);
                    case 2:
                        mensajesLeidos(usuario);
                    case 3:
                        borradores(usuario);
                    case 4:
                        redactarMensaje(usuario, registro);
                    case 5:
                        // Visualización de la lista de empleados por parte del administrador
                        mostrarListaEmpleados(registro);
                        break;
                    case 6:
                        // Registrar nuevo Empleado
                    case 7:
                        // Modificar Contraseñas
                    case 8:
                        // Eliminar Usuarios
                    case 9:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
                System.out.println();
            }
        }
        scanner.close();
    }

    // Verificación de credenciales
    private static Empleado checkLogin(long cedula, String contrasena, Registro registro) {
        DoubleNode nodo = registro.getRegistro().first();
//      Recorrido nodo por nodo buscando credencial correcta
        while (nodo != null) {
            Empleado empleado = (Empleado) nodo.getData();
            if (empleado.getCedula() == cedula && empleado.getContrasena().equals(contrasena)) {
                return empleado;
            }
            nodo = nodo.getNext();
        }
        return null;
    }

    public static void bandejaEntrada(Empleado empleado){
        System.out.println("===== Bandeja de Entrada =====");
        Scanner scanner = new Scanner(System.in);
        empleado.mostrarBandejaEntrada();
        boolean validacionLectura = true;
        while(validacionLectura){
            System.out.println("¿Desea seleccionar un correo a leer? (Y/N)");
            String validacion = scanner.nextLine();
            if (validacion.equals("Y") | validacion.equals("y")){
                validacionLectura = false;
            } else if (validacion.equals("N") | validacion.equals("n")) {
                return;
            }
            else{
                System.out.println("Entrada no válida, intente de nuevo.");
            }
        }
        System.out.println("Escoja por favor el correo que desea ver: ");
        int correo = scanner.nextInt();
        DoubleNode nodo = empleado.buscarNodoBandejaEntrada(correo);
        Message correoLeido = (Message) empleado.getBandejaEntrada().remove(nodo);
        System.out.println(correoLeido);
        empleado.getCorreosLeidos().enqueue(correoLeido);
        scanner.close();
    }

    public static void mensajesLeidos(Empleado empleado){
        System.out.println("===== Mensajes Leídos =====");
        Queue mensajes = empleado.getCorreosLeidos();
        if (mensajes.isEmpty()){
            System.out.println("No tiene más correos leídos por ver.");
            return;
        }
        Message leido = (Message) mensajes.dequeue();
        System.out.println("MENSAJE:");
        System.out.println(leido);
        Scanner scanner = new Scanner(System.in);
        while(!mensajes.isEmpty()){
            boolean validacionLectura = true;
            while(validacionLectura){
                System.out.println("¿Desea ver el siguiente correo leído? (Y/N)");
                String validacion = scanner.nextLine();
                if (validacion.equals("Y") | validacion.equals("y")){
                    validacionLectura = false;
                } else if (validacion.equals("N") | validacion.equals("n")) {
                    scanner.close();
                    return;
                }
                else{
                    System.out.println("Entrada no válida, intente de nuevo.");
                }
            }
            leido = (Message) mensajes.dequeue();
            System.out.println("MENSAJE:");
            System.out.println(leido);
        }
        scanner.close();
    }

    public static void borradores(Empleado empleado){
        System.out.println("===== Borradores =====");
        Stack borradores = empleado.getBorradores();
        if (borradores.isEmpty()){
            System.out.println("No tiene borradores por revisar.");
            return;
        }
        Message borrador = (Message) borradores.top();
        System.out.println("MENSAJE:");
        System.out.println(borrador);
        Scanner scanner = new Scanner(System.in);

        boolean validacionLectura = true;
        while(validacionLectura){
            System.out.println("¿Qué desea hacer con el correo?" +
                    "\n1. Enviar correo." +
                    "\n2. Descartar borrador." +
                    "\n3. Salir.");
            int eleccion = Integer.parseInt(scanner.nextLine());
            if (eleccion > 3 | eleccion < 1){
                System.out.println("Entrada no válida. Intente de nuevo.");
                continue;
            }
            switch (eleccion){
                case 1:
                    borrador.getDestinatario().agregarMensajeBandejaEntrada(borrador);
                    System.out.println("Mensaje Enviado.");
                    validacionLectura = false;
                    break;
                case 2:
                    borradores.pop();
                    System.out.println("Borrador eliminado.");
                    validacionLectura = false;
                    break;
                case 3:
                    validacionLectura = false;
                    break;
            }
        }
        // REVISAR SI QUIERO REVISAR OTRO BORRADOR EN EL MISMO LLAMADO DEL MÉTODO
    }

    public static void redactarMensaje(Empleado remitente, Registro registro){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la cédula del destinatario del correo: ");
        long cedula = scanner.nextLong();
        DoubleNode destinoNodo = registro.buscarNodoCedula(cedula);
        if (destinoNodo == null) {
            System.out.println("No existe un empleado con la cédula: " + cedula);
            return;
        }
        Empleado destinatario = (Empleado) destinoNodo.getData();
        scanner.nextLine();
        System.out.print("Asunto: ");
        String titulo = scanner.nextLine();
        System.out.print("Mensaje: ");
        String contenido = scanner.nextLine();
        // Creamos el mensaje
        Message mensaje = new Message(remitente, destinatario, titulo, contenido);

        System.out.println("¿Qué desea hacer con el mensaje?");
        System.out.println("1. Enviar");
        System.out.println("2. Guardar como Borrador");
        System.out.println("3. Descartar");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                destinatario.agregarMensajeBandejaEntrada(mensaje);
                System.out.println("Mensaje Enviado.");
                break;
            case 2:
                remitente.agregarMensajeBorradores(mensaje);
                System.out.println("Mensaje Guardado como Borrador.");
                break;
            case 3:
                System.out.println("Mensaje Descartado.");
                break;
            default:
                System.out.println("Opción Invalida.");
                break;
        }
    }

    private static void mostrarListaEmpleados(Registro registro) {
        System.out.println("==== Lista de Empleados ====");
        if (registro.getRegistro().isEmpty()) {
            System.out.println("No hay usuarios");
        }
        Main.impresionEmpleados(registro.getRegistro());
    }

    private static void impresionEmpleados(DoubleList registro){
        String[] indices_tabla = {"NOMBRE", "CC", "FECHA NACIMIENTO", "CIUDAD NATAL", "TELÉFONO", "EMAIL", "DIRECCIÓN"};

        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%4s %20s %10s %20s %20s %15s %27s %80s",
                "#", indices_tabla[0], indices_tabla[1], indices_tabla[2], indices_tabla[3], indices_tabla[4], indices_tabla[5], indices_tabla[6]);
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        DoubleNode nodo = registro.first();
        int i = 1;
        while (nodo != null){
            Empleado empleado = (Empleado) nodo.getData();
            System.out.printf("%4d %20s %10d %20s %20s %15d %27s %80s",
                    i, empleado.getNombre(), empleado.getCedula(), empleado.getFecha_nac(), empleado.getCiudad_nac(),
                    empleado.getTel(), empleado.getEmail(), empleado.getDir());
            System.out.println();
            nodo = nodo.getNext();
            i++;
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

}