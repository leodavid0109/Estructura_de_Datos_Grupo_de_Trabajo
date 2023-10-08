import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Message {
    private String sender;
    private String recipient;
    private LocalDateTime date;
    private String title;
    private String content;

    public Message(String sender, String recipient,String title, String content) {
        this.sender = sender;
        this.recipient = recipient;
        this.date = LocalDateTime.now();
        this.title = title;
        this.content = content;
    }
    public Message(String sender, String recipient,LocalDateTime dateTime,String title, String content) {
        this.sender = sender;
        this.recipient = recipient;
        this.date = dateTime;
        this.title = title;
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return sender + "," + recipient + "," + date.format(formatter) + "," + title + "," + content;
    }
}