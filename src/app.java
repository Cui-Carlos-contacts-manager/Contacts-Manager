import java.util.ArrayList;
import java.util.Scanner;

public class app {
    public static void main(String[] args) {
        ArrayList<info> contacts = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        //Home page
        while (true) {

            System.out.println("===================Home Page===================");
            System.out.println("1. View contacts.");
            System.out.println("2. Add a new contact.");
            System.out.println("3. Search a contact by name.");
            System.out.println("4. Delete an existing contact.");
            System.out.println("5. Exit.");
            System.out.println("Enter an option (1, 2, 3, 4 or 5):");
            int command = sc.nextInt();

            switch (command) {
                case 1:
                    // show all
                    break;
                case 2:
                    // add new contact
                    break;
                case 3:
                    // search
                    break;
                case 4:
                    // delete
                    break;
                case 5:
                    // Exit
                    return;
                default:
                    System.out.println("Command Error");
            }
        }

    }

}
