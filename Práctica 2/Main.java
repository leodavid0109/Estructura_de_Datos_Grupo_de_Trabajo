import java.util.Scanner;
import Estructuras.*;
public class Main {
    public static void main(String[] args) {

        Registro registro = new Registro();
        //Cargar los empleados guardados en el archivo de texto empleados.txt
        registro.importFileEmpleadosP("empleadosOriginal.txt");
        registro.importFilePasswordP("passwordOriginal.txt");

        registro.toFileEmpleados("Empleados.txt");
        registro.toFilePassword("Password.txt");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenidos a la aplicación de mesajeria.Inicie Sesión.");
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
            System.out.println("¡Bienvenido!Has ingresado correctamente.");
        } else {
            System.out.println("¡Error!Las credenciales no coinciden, intente nuevamente.");
            scanner.close();
            return;
        }

//        Inbox inbox = new Inbox(id);

        if (usuario.getPuesto() == Categoria.Empleado){
            boolean running = true;
            while (running) {
                System.out.println("Please select an option:");
                System.out.println("1. Check inbox");
                System.out.println("2. Write message");
                System.out.println("3. View drafts");
                System.out.println("4. Discard draft");
                System.out.println("5. Send draft");
                System.out.println("6. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline character
                switch (choice) {
                    case 1:
//                        inbox.readMessage();
                        break;
                    case 2:
                        System.out.print("Enter recipient: ");
                        String recipient = scanner.nextLine();
                        if (!userExists(recipient)) {
                            System.out.println("Recipient does not exist.");
                            break;
                        }
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
//                    inbox.sendDraft();
                        break;
                    case 6:
                        running = false;
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
                System.out.println("Please select an option:");
                System.out.println("1. Check inbox");
                System.out.println("2. Write message");
                System.out.println("3. View drafts");
                System.out.println("4. Discard draft");
                System.out.println("5. Send draft");
                System.out.println("6. Register new employee");
                System.out.println("7. Change passwords");
                System.out.println("8. Delete user");
                System.out.println("9. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline character
                switch (choice) {
                    case 1:
//                        inbox.readMessage();
                        break;
                    case 2:
                        System.out.print("Enter recipient: ");
                        String recipient = scanner.nextLine();
                        if (!userExists(recipient)) {
                            System.out.println("Recipient does not exist.");
                            break;
                        }
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
//                    inbox.sendDraft();
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
        DoubleNode nodo = registro.first();
        Empleado u = null;
        if (nodo!=null){u= (Empleado) nodo.getData();}
//        Recorremos nodo por nodo y luego asignamos a u el objeto almacenado en ese nodo
        while (nodo!=null) {
            if (u.getCedula() == cedula && u.getContrasena()+"\n"==contrasena) {
                return u;
            }
            System.out.println("Objeto "+u.getContrasena()+" ingresada: "+contrasena);
            nodo = nodo.getNext();
            if (nodo!=null){u= (Empleado) nodo.getData();}else{u=null;}
        }
        return null;
    }

    private static boolean userExists(String username) {
        // check if user exists in database or file
        return true;
    }
}