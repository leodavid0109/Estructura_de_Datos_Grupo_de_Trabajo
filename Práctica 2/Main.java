import java.util.Random;
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

        // Creación inicial de los archivos para los empleados existentes
//        registro.toFileBandeja();
//        registro.toFileLeidos();
//        registro.toFileBorradores();

        // Importación de todas las bandejas de mensajería
        registro.importBandejaEntrada();
        registro.importLeidos();
        registro.importBorradores();

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
                        registro.toFileEmpleados("Empleados.txt");
                        registro.toFilePassword("Password.txt");
                        registro.toFileBandeja();
                        registro.toFileLeidos();
                        registro.toFileBorradores();
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
                        mostrarListaEmpleados(registro);
                        break;
                    case 6:
                        registrarNuevoEmpleado(registro);
                    case 7:
                        modificarContrasenas(registro);
                    case 8:
                        System.out.println("Ingrese la cédula del empleado que desea eliminar: ");
                        int CCEmpleadoEliminar = scanner.nextInt();
                        if (CCEmpleadoEliminar == usuario.getCedula()){
                            System.out.println("No puede eliminar su propio usuario. Para hacerlo, ingrese desde otro administrador.");
                        }
                        else{
                            //REVISAR COMO SE VA A ELIMINAR EL ARCHIVO DEL EMPLEADO ELIMINADO
                            registro.eliminar(CCEmpleadoEliminar);
                        }
                        break;
                    case 9:
                        running = false;
                        registro.toFileEmpleados("Empleados.txt");
                        registro.toFilePassword("Password.txt");
                        registro.toFileBandeja();
                        registro.toFileLeidos();
                        registro.toFileBorradores();
                        break;
                    default:
                        System.out.println("Entrada no válida. Intente de nuevo");
                        break;
                }
                System.out.println();
            }
        }
        scanner.close();
    }

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
        while (correo < 1 | correo > empleado.getBandejaEntrada().size()){
            System.out.println("Entrada no válida. Intente de nuevo");
            System.out.println("Escoja por favor el correo que desea ver: ");
            correo = scanner.nextInt();
        }
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

    public static void registrarNuevoEmpleado(Registro registro){
        Scanner scanner = new Scanner(System.in);
        System.out.println("==== Agregar un nuevo empleado ====");
        System.out.println("¿Desea crear un empleado con datos random?(Se crearán con puesto empleado) (S/N)");
        char respuesta = scanner.next().charAt(0);
        if (respuesta == 'S' || respuesta == 's') {
            System.out.print("Ingrese la cantidad de empleados random que desea crear: ");
            int cantidadUsuarios = scanner.nextInt();
            if (cantidadUsuarios <= 0) {
                System.out.println("Cantidad inválida.");
                return;
            }
            for (int i = 0; i < cantidadUsuarios; i++) {
                agregarEmpleadosRandom(registro);
            }
        } else {
            System.out.println("==== Agregar un nuevo usuario ====");
            System.out.print("Ingrese el ID del usuario: ");
            long nuevoId = scanner.nextLong();
            scanner.nextLine();
            if(registro.buscarNodoCedula(nuevoId) != null){
                System.out.println("Ya existe un usuario con id: " + nuevoId);
                return;
            }

            System.out.print("Ingrese el nombre del usuario: ");
            String nuevoNombre = scanner.nextLine();

            System.out.println("Ingrese la fecha de nacimiento (dd mm aa): ");
            int nuevoDia = scanner.nextInt();
            int nuevoMes = scanner.nextInt();
            int nuevoAnio = scanner.nextInt();
            Fecha nuevaFecha = new Fecha(nuevoDia, nuevoMes, nuevoAnio);

            scanner.nextLine();
            System.out.print("Ingrese la ciudad de nacimiento: ");
            String nuevaCiudadNac = scanner.nextLine();

            System.out.print("Ingrese la dirección (calle, noCalle, nomenclatura, barrio, ciudad): ");
            String[] direccion = scanner.nextLine().split(", ");
            String nuevaCalle = direccion[0];
            int nuevoNoCalle = Integer.parseInt(direccion[1]);
            String nuevaNomenclatura = direccion[2];
            String nuevoBarrio = direccion[3];
            String nuevaCiudad = direccion[4];

            Direccion nuevaDireccion;

            System.out.println("¿Desea añadir información de apartamento? (S/N)");
            char rta = scanner.next().charAt(0);
            if (rta == 'S' || rta == 's') {
                System.out.print("Ingrese el nombre de la urbanización: ");
                String urbanizacion = scanner.nextLine();
                System.out.println("Ingrese el número de apartamento");
                String apartamento = scanner.nextLine();

                nuevaDireccion = new Direccion(nuevaCalle, nuevoNoCalle, nuevaNomenclatura, nuevoBarrio, nuevaCiudad, urbanizacion, apartamento);
            }
            else {
                nuevaDireccion = new Direccion(nuevaCalle, nuevoNoCalle, nuevaNomenclatura, nuevoBarrio, nuevaCiudad);
            }

            System.out.print("Ingrese el número de teléfono: ");
            long nuevoTel = scanner.nextLong();

            scanner.nextLine();
            System.out.print("Ingrese el correo electrónico: ");
            String nuevoEmail = scanner.nextLine();

            System.out.print("Ingrese la contraseña: ");
            String contrasena = scanner.nextLine();

            System.out.println("Seleccione el puesto del nuevo usuario: " +
                    "\n1. Empleado" +
                    "\n2. Administrador");

            int puesto = scanner.nextInt();
            while (puesto < 1 | puesto > 2){
                System.out.println("Entrada no válida. Intente de nuevo");
                System.out.println("Seleccione el puesto del nuevo usuario: " +
                        "\n1. Empleado" +
                        "\n2. Administrador");
                puesto = scanner.nextInt();
            }

            Empleado nuevoUsuario;
            if (puesto == 1){
                nuevoUsuario = new Empleado(nuevoId, nuevoNombre, nuevaFecha, nuevaCiudadNac,
                        nuevoTel, nuevoEmail, nuevaDireccion, contrasena, Categoria.Empleado);
            }
            else {
                nuevoUsuario = new Empleado(nuevoId, nuevoNombre, nuevaFecha, nuevaCiudadNac,
                        nuevoTel, nuevoEmail, nuevaDireccion, contrasena, Categoria.Administrador);
            }

            boolean agregado = registro.agregar(nuevoUsuario);
            if (agregado) {
                System.out.println("Usuario agregado exitosamente.");
            } else {
                System.out.println("No se pudo agregar el usuario.");
            }
        }
    }

    private static void agregarEmpleadosRandom(Registro registro) {
        // Generar datos aleatorios
        Random random = new Random();
        long nuevoId = random.nextInt(1000) + 1; // ID entre 1 y 1000
        while(registro.buscarNodoCedula(nuevoId) != null){
            nuevoId=nuevoId+1;
        }
        String nuevoNombre = "UsuarioRandom" + nuevoId;
        Fecha nuevaFecha = new Fecha(random.nextInt(28) + 1, random.nextInt(12) + 1, random.nextInt(21) + 2000);
        String nuevaCiudadNac = "CiudadRandom" + (random.nextInt(10) + 1); // Ciudad entre 1 y 10
        Direccion nuevaDireccion = new Direccion("CalleRandom", random.nextInt(500) + 1, "NomenclaturaRandom",
                "BarrioRandom", "CiudadRandom", "UrbanizaciónRandom", (random.nextInt(20) + 1) + "" + (random.nextInt(12) + 1));
        long nuevoTel = 3000000000L + random.nextInt(1000000000); // Teléfono aleatorio
        String nuevoEmail = "usuario" + nuevoId + "@random.com";
        String contrasena = "con" + nuevoId + "ran*";

        Empleado nuevoUsuario = new Empleado(nuevoId, nuevoNombre, nuevaFecha, nuevaCiudadNac, nuevoTel,
                nuevoEmail, nuevaDireccion, contrasena, Categoria.Empleado);
        boolean agregado = registro.agregar(nuevoUsuario);
        if (agregado) {
            System.out.println("Usuario agregado exitosamente.");
        } else {
            System.out.println("No se pudo agregar el usuario.");
        }
    }

    private static void modificarContrasenas(Registro registro){
        System.out.println("==== Lista de Empleados con Contraseñas ====");
        Main.impresionContrasenas(registro.getRegistro());

        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione por favor el empleado al cual le desea cambiar la contraseña: ");
        int eleccionempleado = scanner.nextInt();
        while (eleccionempleado < 1 | eleccionempleado > registro.getRegistro().size()){
            System.out.println("Entrada no válida. Intente de nuevo");
            System.out.println("Seleccione por favor el empleado al cual le desea cambiar la contraseña: ");
            eleccionempleado = scanner.nextInt();
        }

        Empleado empleado = (Empleado) registro.buscarNodoPosicion(eleccionempleado).getData();

        System.out.println("Ingrese la nueva contraseña: ");
        String contrasena = scanner.nextLine();
        System.out.println("Confirme la contraseña: ");
        String confirmacion = scanner.nextLine();
        while (!contrasena.equals(confirmacion) | contrasena.equals(empleado.getContrasena())){
            if (contrasena.equals(empleado.getContrasena())){
                System.out.println("La contraseña ingresada no es válida. Intente de nuevo.");
            }
            else {
                System.out.println("Las contraseñas ingresadas no coinciden. Intente de nuevo.");
            }
            System.out.println("Ingrese la nueva contraseña: ");
            contrasena = scanner.nextLine();
            System.out.println("Confirme la contraseña: ");
            confirmacion = scanner.nextLine();
        }

        empleado.setContrasena(contrasena);
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

    private static void impresionContrasenas(DoubleList registro){
        String[] indices_tabla = {"NOMBRE", "CC", "CONTRASEÑA"};

        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%4s %20s %10s %20s",
                "#", indices_tabla[0], indices_tabla[1], indices_tabla[2]);
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        DoubleNode nodo = registro.first();
        int i = 1;
        while (nodo != null){
            Empleado empleado = (Empleado) nodo.getData();
            System.out.printf("%4d %20s %10d %20s",
                    i, empleado.getNombre(), empleado.getCedula(), empleado.getContrasena());
            System.out.println();
            nodo = nodo.getNext();
            i++;
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
}