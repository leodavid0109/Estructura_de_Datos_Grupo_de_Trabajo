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
        System.out.println("Bienvenidos a la aplicación de mesajería. Inicie Sesión.");
        System.out.print("Número de Identificación: ");
        long cedula = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();
        System.out.println();

        boolean loggedIn = false;

        // check login credentials
        Empleado usuario = checkLogin(cedula, contrasena,registro);
        if (usuario != null) {
            loggedIn = true;
            System.out.println("¡Bienvenido! Has ingresado correctamente.");
        } else {
            System.out.println("¡Error! Las credenciales no coinciden, intente nuevamente.");
            scanner.close();
            return;
        }

//        Inbox inbox = new Inbox(id);

        if (usuario.getPuesto() == Categoria.Empleado){
            boolean running = true;
            while (running) {
                System.out.println("Por favor seleccione una opción:");
                System.out.println("1. Bandeja de Entrada");
                System.out.println("2. Mensajes leídos");
                System.out.println("3. Borradores");
                System.out.println("4. Enviar Mensajes");
                System.out.println("5. Salir");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline character
                switch (choice) {
                    case 1:
                        bandejaEntrada(usuario);
                        break;
                    case 2:

                        break;
                    case 3:
//                    inbox.printDrafts();
                        break;
                    case 4:
                        enviarMensaje(usuario,registro);
                        break;
                    case 5:
                        running = false;
                        registro.toFileInbox();
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        break;
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
                System.out.println("4. Enviar Mensajes");
                System.out.println("5. Ver usuarios");
                System.out.println("6. Registrar un nuevo usuario");
                System.out.println("7. Cambiar contraseñas");
                System.out.println("8. Eliminar usuario");
                System.out.println("9. Salir");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline character
                switch (choice) {
                    case 1:
//                        inbox.readMessage();
                        break;
                    case 2:
                        System.out.print("Enter recipient: ");
                        String recipient = scanner.nextLine();
//                        if (!userExists(recipient)) {
//                            System.out.println("Recipient does not exist.");
//                            break;
//                        }
                        System.out.print("Enter title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter message content: ");
                        String content = scanner.nextLine();
//                        Message message = new Message(id, recipient, title, content);
                        System.out.println("1. Save as draft");
                        System.out.println("2. Discard");
                        System.out.println("3. Send");
                        int option = scanner.nextInt();
                        scanner.nextLine(); // consume newline character
                        switch (option) {
                            case 1:
//                            inbox.addDraft(title, content);
                                System.out.println("Message saved as draft.");
                                break;
                            case 2:
                                System.out.println("Message discarded.");
                                break;
                            case 3:
//                                inbox.addMessage(message);
                                System.out.println("Message sent.");
                                break;
                            default:
                                System.out.println("Invalid option.");
                                break;
                        }
                        break;
                    case 3:
//                    inbox.printDrafts();
                        break;
                    case 4:
//                    inbox.discardDraft();
                        System.out.println("Draft discarded.");
                        break;
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

    private static Empleado checkLogin(long cedula, String contrasena,Registro registro) {
        // check login credentials in database or file
        DoubleNode nodo = registro.getRegistro().first();
        Empleado u = null;
        if (nodo!=null){u= (Empleado) nodo.getData();}
//        Recorremos nodo por nodo y luego asignamos a u el objeto almacenado en ese nodo
        while (nodo!=null) {
            if (u.getCedula() == cedula && u.getContrasena().equals(contrasena)) {
                return u;
            }
            nodo = nodo.getNext();
            if (nodo!=null){u= (Empleado) nodo.getData();}else{u=null;}
        }
        return null;
    }

    public static void bandejaEntrada(Empleado usuario){
        System.out.println("-----|Bandeja de Entrada|-----");
        System.out.println("Por favor seleccione el mensaje que desee leer:");
        usuario.mostrarBandejaEntrada();
    }

//    Envio de mensaje
    public static void enviarMensaje(Empleado de,Registro registro){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la cedula de la persona con la que desea contactar: ");
        long cedula = scanner.nextLong();
        DoubleNode destinoNodo = registro.buscarNodoCedula(cedula);
        if (destinoNodo==null) {
            System.out.println("¡Error!No existe un usuario con la cedula: "+cedula);
            return;
        }
        Empleado para = (Empleado) destinoNodo.getData();
        scanner.nextLine();
        System.out.print("Ingrese el Titulo del Mensaje: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese el Mensaje: ");
        String contenido = scanner.nextLine();
//        Creamos el mensaje
        Message mensaje = new Message(de, para, titulo, contenido);
        System.out.println("Seleccione una opción:");
        System.out.println("1. Enviar");
        System.out.println("2. Guardar como Borrador");
        System.out.println("3. Descartar");
        int option = scanner.nextInt();
        scanner.nextLine(); // consume newline character
        switch (option) {
            case 1:
                para.agregarBandejaEntrada(mensaje);
                System.out.println("Mensaje Enviado.");
                break;
            case 2:
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