import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Message {
    private Empleado de;
    private Empleado para;
    private LocalDateTime fecha;
    private String titulo;
    private String contenido;

    public Message(Empleado de, Empleado para,String titulo, String contenido) {
        this.de = de;
        this.para = para;
        this.fecha = LocalDateTime.now();
        this.titulo = titulo;
        this.contenido = contenido;
    }
    public Message(Empleado de, Empleado para,LocalDateTime fecha,String titulo, String contenido) {
        this.de = de;
        this.para = para;
        this.fecha = fecha;
        this.titulo = titulo;
        this.contenido = contenido;
    }

    public Empleado getDe() {
        return de;
    }

    public Empleado getPara() {
        return para;
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
        return de.getCedula() + "," + para.getCedula() + "," + fecha.format(formatter) + "," + titulo + "," + contenido;
    }
}