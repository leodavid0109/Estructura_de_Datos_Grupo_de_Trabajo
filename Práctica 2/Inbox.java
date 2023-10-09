//import Estructuras.DoubleList;
//import Estructuras.DoubleNode;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Scanner;
//
//public class Inbox {
//    private DoubleList messages;
//    private String username;
//
//    public Inbox(String id) {
//        messages = new DoubleList();
//        username = id;
//        loadMessages();
//    }
//
//    public void addMessage(Message message) {
//        String filename = message.getRecipient() + "BA.txt";
//        try {
//            FileWriter fileWriter = new FileWriter(filename, true);
//            PrintWriter printWriter = new PrintWriter(fileWriter);
//            printWriter.println(message.toString());
//            printWriter.close();
//            System.out.println("Message added to inbox.");
//        } catch (IOException e) {
//            System.out.println("Error adding message to inbox.");
//        }
//    }
//
//    public void printMessages() {
//        System.out.println("Messages:");
//        DoubleNode current = messages.first();
//        int i = 1;
//        while (current != null) {
//            Message message = (Message) current.getData();
//            if (message.getRecipient().equals(username)) {
//                System.out.println(i + ". " + message.getTitle());
//            }
//            current = current.getNext();
//            i++;
//        }
//    }
//
//    public void readMessage() {
//        System.out.println("Messages:");
//        DoubleNode current = messages.first();
//        int i = 1;
//        while (current != null) {
//            Message message = (Message) current.getData();
//            if (message.getRecipient().equals(username)) {
//                System.out.println(i + ". " + message.getTitle());
//            }
//            current = current.getNext();
//            i++;
//        }
//        System.out.print("Enter message index to read: ");
//        Scanner scanner = new Scanner(System.in);
//        int index = scanner.nextInt();
//        scanner.nextLine(); // consume newline character
//        if (index < 1 || index > messages.size()) {
//            System.out.println("Index out of range.");
//            return;
//        }
//        current = messages.first();
//        i = 1;
//        while (current != null && i < index) {
//            current = current.getNext();
//            i++;
//        }
//        if (current != null) {
//            Message message = (Message) current.getData();
//            if (message.getRecipient().equals(username)) {
//                System.out.println("From: " + message.getSender());
//                System.out.println("Date: " + message.getDate());
//                System.out.println("Title: " + message.getTitle());
//                System.out.println("Content: " + message.getContent());
//                current.remove();
//                saveMessages();
//                saveReadMessages(message);
//            } else {
//                System.out.println("Invalid message index.");
//            }
//        }
//    }
//
//    private void loadMessages() {
//        try {
//            File file = new File(username + "BA.txt");
//            Scanner scanner = new Scanner(file);
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                String[] parts = line.split(",");
//                if (parts.length == 5 && parts[1].equals(username)) {
//                	Message message = new Message(parts[0], parts[1], parts[3], parts[4]);
//                    messages.addLast(message);
//                }
//            }
//            scanner.close();
//        } catch (IOException e) {
//            System.out.println("Error loading messages.");
//        }
//    }
//
//    private void saveMessages() {
//        try {
//            FileWriter writer = new FileWriter(username + "BA.txt");
//            DoubleNode current = messages.first();
//            while (current != null) {
//                Message message = (Message) current.getData();
//                writer.write(message.getSender() + "," + message.getRecipient() + "," + message.getDate() + "," + message.getTitle() + "," + message.getContent() + "\n");
//                current = current.getNext();
//            }
//            writer.close();
//        } catch (IOException e) {
//            System.out.println("Error saving messages.");
//        }
//    }
//
//    private void saveReadMessages(Message message) {
//        try {
//            FileWriter writer = new FileWriter(username + "ML.txt", true);
//            writer.write(message.getSender() + "," + message.getRecipient() + "," + message.getDate() + "," + message.getTitle() + "," + message.getContent() + "\n");
//            writer.close();
//        } catch (IOException e) {
//            System.out.println("Error saving read messages.");
//        }
//    }
//}