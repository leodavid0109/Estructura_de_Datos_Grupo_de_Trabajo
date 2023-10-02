import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the message app. Please log in.");
        System.out.print("Identification number: ");
        String id = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.println();

        boolean loggedIn = false;

        // check login credentials
        if (checkLogin(id, password)) {
            loggedIn = true;
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid login credentials.");
            scanner.close();
            return;
        }

        Inbox inbox = new Inbox(id);

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
                    inbox.readMessage();
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
                    Message message = new Message(id, recipient, title, content);
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
                            inbox.addMessage(message);
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
        scanner.close();
    }

    private static boolean checkLogin(String id, String password) {
        // check login credentials in database or file
        return true;
    }

    private static boolean userExists(String username) {
        // check if user exists in database or file
        return true;
    }
}