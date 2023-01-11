import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {

    public static ArrayList<Info> contacts = new ArrayList<>();

    public static List<String> contactList = new ArrayList<>();
    public static final Scanner sc = new Scanner(System.in);
    public static Path path = Paths.get("src", "contacts.txt");


    public static File file = new File("/Users/cui/IdeaProjects/Contacts-Manager/src/contacts.txt");

    public static List<String> list;

    public static void main(String[] args) throws IOException {

        reloadArr();

        run();
    }

    private static void reloadArr() throws IOException {
        try {
            if (Files.size(path) != 0) {
                contactList = Files.readAllLines(path);
                for (int i = 0; i < contactList.size(); i += 2) {
                    contacts.add(new Info(contactList.get(i), contactList.get(i + 1)));
                }
            }
        } catch (Exception e) {
            run();
        }
    }

    //run app
    private static void run() throws IOException {

        //Home page
        while (true) {

            System.out.println("===================Home Page===================");
            System.out.println("1. View contacts.");
            System.out.println("2. Add a new contact.");
            System.out.println("3. Search a contact by name.");
            System.out.println("4. Delete an existing contact.");
            System.out.println("5. Exit.");
            System.out.println("Enter an option (1, 2, 3, 4 or 5):");
            String command = sc.nextLine();

            switch (command) {
                case "1":
                    // show all
                    showAll();
                    break;
                case "2":
                    // add new contact
                    add();
                    break;
                case "3":
                    // search
                    search();
                    break;
                case "4":
                    // delete
                    delet();
                    break;
                case "5":
                    // Exit
                    System.out.println("Bye!!!");
                    System.exit(0);
                default:
                    System.out.println("Command Error");
            }
        }

    }

    //add
    private static void add() throws IOException {

        System.out.println("-------add-------");
        while (true) {
            System.out.println("Enter the name: ");
            String nameInput = sc.nextLine();
//            for (Info contact : contacts) {
//                if (nameInput.equalsIgnoreCase(contact.getName())) {
//                    System.out.println("There's already a contact named Jane Doe. Do you want to overwrite it? (Yes/No)");
//
//                }
//            }
                System.out.println("Enter the Phone number: ");
            String numInput = sc.nextLine();
            if (numInput.matches("^\\d{7,10}$") && (numInput.length() == 7 || numInput.length() == 10)) {
                Files.write(path, List.of(nameInput.toLowerCase()), StandardOpenOption.APPEND);
                Files.write(path, List.of(numInput.toLowerCase()), StandardOpenOption.APPEND);
                System.out.println("Add more?[Y/N]");
                String command = sc.nextLine();
                if (!"y".equalsIgnoreCase(command)) {
                    // add to arraylist
                    contactList = Files.readAllLines(path);
                    //System.out.println(contacts.size());//-----
                    //!!!!!! clear arraylist
                    contacts.clear();
                    for (int i = 0; i < contactList.size(); i += 2) {
                        contacts.add(new Info(contactList.get(i), contactList.get(i + 1)));
                    }
                   // System.out.println(contacts.size());//-----
                    System.out.println("Back to main page.");
                    run();
                }
            } else {
                System.out.println("Invalid phone number. Please do it again.");
                add();
            }
        }
    }

    //show
    private static void showAll() throws IOException {
        if (Files.size(path) == 0) {
            System.out.println("No contacts in the file");
        } else {
            System.out.println("Name       | Phone number |" + "\n---------------------------");
            for (Info contact : contacts) {
                if(contact.getNum().length() == 7){
                    String num = contact.getNum();
                    String strNum = num.substring(0,3) + "-" + num.substring(3);
                System.out.println(contact.getName() + " | " + strNum);
                }else {
                    String num1 = contact.getNum();
                    String strNum1 = num1.substring(0,3) + "-" + num1.substring(3,6)+"-"+num1.substring(6);
                    System.out.println(contact.getName() + " | " + strNum1);
                }
            }
        }
    }

    //search
    private static void search() throws IOException {
        boolean found = false;
        if (Files.size(path) == 0) {
            System.out.println("No contacts in the file");
        } else {
            while (true) {
                System.out.println("Please enter the name for search: ");
                String name = sc.nextLine();
                for (Info contact : contacts) {
                    if (name.equalsIgnoreCase(contact.getName())) {
                        found = true;
                        System.out.println("Name       | Phone number |" + "\n---------------------------");
                        if(contact.getNum().length() == 7){
                            String num = contact.getNum();
                            String strNum = num.substring(0,3) + "-" + num.substring(3);
                            System.out.println(contact.getName() + " | " + strNum);
                        }else{
                            String num1 = contact.getNum();
                            String strNum1 = num1.substring(0,3) + "-" + num1.substring(3,6)+"-"+num1.substring(6);
                            System.out.println(contact.getName() + " | " + strNum1);
                        }
//                        System.out.println(name + " | " + contact.getNum());
                        System.out.println("Continue? [Y/N]");
                        String command = sc.nextLine();
                        if ("y".equalsIgnoreCase(command)) {
                            search();
                        } else {
                            run();
                        }
                    }
                }
                if (!found) {
                    System.out.println(name + " not in the contact list.");
                    System.out.println("Continue? [Y/N]");
                    String command = sc.nextLine();
                    if (!"y".equalsIgnoreCase(command)) {
                        run();
                    }
                }
            }
        }
    }

    //delete
    private static void delet() throws IOException {
        System.out.println("Delete all contacts？ [Y/N]");
        String command = sc.nextLine();
        if ("y".equalsIgnoreCase(command)) {
            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write("");//empty
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            run();
        }
    }

}


