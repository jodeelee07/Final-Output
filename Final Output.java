import java.util.Scanner;
import java.util.HashMap;

class Accounts {
    // Private fields to store accounts information
    private int id;
    private int pin;
    private String name;
    private double balance;

    // Constructor
    public Accounts(int id, int pin, String name, double balance) {
        this.id = id;
        this.pin = pin;
        this.name = name;
        this.balance = balance;
    }

    // Methods
    public int getId() {
        return id;
    }
    public int getPin() {
        return pin;
    }
    public String getName() {
        return name;
    }
    public double getBalance() {
        return balance;
    }
    // Setter methods to update user information
    public void setPin(int pin) {
        this.pin = pin;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
class BankingApp {
    private Scanner scanner;
    // A hash map to store users by their id
    private HashMap<Integer, Accounts> users;
    // A user object to store the current logged in user
    private Accounts currentUser;
    // A constructor to initialize the banking
    public BankingApp() {
        scanner = new Scanner(System.in);
        users = new HashMap<Integer, Accounts>();
        currentUser = null;
    }
    // Method
    public void addUser(Accounts user) {
        users.put(user.getId(), user);
    }
    // A method to display the main menu and prompt the user to choose an option
    public void displayMenu() {
        System.out.println("Welcome to D'Bank!");
        System.out.println("Please choose an option:");
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.print("Enter here: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                exit();
                break;
            default:
                System.out.println("Invalid choice. Please try again.\n");
                displayMenu();
                break;
        }
    }
    // A method to login the user by asking for their id and pin
    public void login() {
        System.out.print("Enter your user id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter your pin: ");
        int pin = scanner.nextInt();
        scanner.nextLine();
        // Check if the user id exists in the hash map
        if (users.containsKey(id)) {
        	Accounts user = users.get(id);
            // PIN Matches
            if (user.getPin() == pin) {
                // Set the current user to the logged in user
                currentUser = user;
                // Display the user menu
                displayUserMenu();
            } else {
                // Incorrect PIN
                System.out.println("Incorrect pin. Please try again.\n");
                // Go back to the main menu
                displayMenu();
            }
        } else {
            // Display an error message
            System.out.println("User id not found. Please try again.\n");
            // Go back to the main menu
            displayMenu();
        }
    }
    // A method to display the user menu and prompt the user to choose an option
    public void displayUserMenu() {
        System.out.println("Hello, " + currentUser.getName() + "!");
        System.out.println("Please choose an option:");
        System.out.println("1. Check Balance");
        System.out.println("2. Cash-in");
        System.out.println("3. Money Transfer");
        System.out.println("4. Logout");
        System.out.print("Enter here: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                checkBalance();
                break;
            case 2:
                cashIn();
                break;
            case 3:
                moneyTransfer();
                break;
            case 4:
                logout();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                displayUserMenu();
                break;
        }
    }
    // A method to check the balance
    public void checkBalance() {
        System.out.println("Your current balance is: " + currentUser.getBalance()+"\n");
        // Go back to the user menu
        displayUserMenu();
    }
    // A method to deposit
    public void cashIn() {
        System.out.print("Enter the amount to cash-in: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        // Check if the amount is positive
        if (amount > 0) {
            // Update the current user's balance
            currentUser.setBalance(currentUser.getBalance() + amount);
            // Display a success message
            System.out.println("You have successfully cashed-in " + amount+ "\n");
            // Go back to the user menu
            displayUserMenu();
        } else {
            // Display an error message
            System.out.println("Invalid amount. Please try again.");
            // Go back to the user menu
            displayUserMenu();
        }
    }
    // A method to transfer money from the current user to another user
    public void moneyTransfer() {
        System.out.print("Enter the user id of the recipient: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        // Check if the user id exists in the hash map
        if (users.containsKey(id)) {
        	Accounts recipient = users.get(id);
            System.out.print("Enter the amount to transfer: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character
            // Check if the amount is positive and less than or equal to the current user's balance
            if (amount > 0 && amount <= currentUser.getBalance()) {
                // Update the current user's balance
                currentUser.setBalance(currentUser.getBalance() - amount);
                // Update the recipient's balance
                recipient.setBalance(recipient.getBalance() + amount);
                // Display a success message
                System.out.println("You have successfully transferred " + amount + " to " + recipient.getName()+"\n");
                // Go back to the user menu
                displayUserMenu();
            } else {
                // Display an error message
                System.out.println("Invalid amount. Please try again.\n");
                // Go back to the user menu
                displayUserMenu();
            }
        } else {
            // Display an error message
            System.out.println("User id not found. Please try again.\n");
            // Go back to the user menu
            displayUserMenu();
        }
    }
    // A method to logout the current user and go back to the main menu
    public void logout() {
        System.out.println("You have successfully logged out.\n");
        // Set the current user to null
        currentUser = null;
        // Go back to the main menu
        displayMenu();
    }
    // A method to exit the banking
    public void exit() {
        System.out.println("Thank you for using D'Bank!");
        // Close the scanner object
        scanner.close();
        // Terminate the program
        System.exit(0);
    }
}
// A main method to test the banking
public class Banking {
    public static void main(String[] args) {
        // Create a banking object
        BankingApp app = new BankingApp();
        // Add some users to the banking
        app.addUser(new Accounts(412435, 7452, "Chris Anderson", 32000));
        app.addUser(new Accounts(264863, 1349, "James Maregmen", 100000));
        // Display the main menu
        app.displayMenu();
    }
}
