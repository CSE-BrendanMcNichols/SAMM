package backEnd;

import java.util.Scanner;

public class ApplicationUI {
    private static ApplicationFacade applicationFacade = new ApplicationFacade();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Welcome to the Application");
            System.out.println("1. Login");
            System.out.println("2. Create Account");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = applicationFacade.loginUser(username, password);
        if (user != null) {
            System.out.println("Login successful!");
            // Here you could show more options or menus depending on the user type
        } else {
            System.out.println("Login failed. Please check your credentials.");
        }
    }

    private static void createAccount() {
        System.out.println("Creating a new account.");
        System.out.println("First Name: ");
        String firstName = scanner.nextLine();
        System.out.println("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.println("USC ID: ");
        String uscid = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        
        System.out.println("Are you a Student, Advisor, or Administrator?");
        String userTypeString = scanner.nextLine();
        UserType userType = UserType.valueOf(userTypeString.toUpperCase());

        boolean success = applicationFacade.registerUser(userType, firstName, lastName, uscid, email, username, password);

        if (success) {
            System.out.println("Account successfully created. You can now login.");
        } else {
            System.out.println("Account creation failed. User may already exist.");
        }
    }
}