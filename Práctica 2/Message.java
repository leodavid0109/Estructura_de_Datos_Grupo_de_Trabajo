import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Message {
    private Empleado remitente;
    private Empleado destinatario;
    private LocalDateTime fecha;
    private String titulo;
    private String contenido;

    public Message(Empleado remitente, Empleado destinatario, String titulo, String contenido) {
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.fecha = LocalDateTime.now();
        this.titulo = titulo;
        this.contenido = contenido;
    }

    public Message(Empleado remitente, Empleado destinatario,LocalDateTime fecha,String titulo, String contenido) {
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.fecha = fecha;
        this.titulo = titulo;
        this.contenido = contenido;
    }

    // Getters y Setters
    public Empleado getDestinatario() {
        return destinatario;
    }

    public Empleado getRemitente() {
        return remitente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getContenido() {
        return contenido;
    }


    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return remitente.getCedula() + "," + destinatario.getCedula() + "," + fecha.format(formatter) + "," + titulo + "," + contenido;
    }
}