package org.example;

import com.google.inject.Inject;

import java.util.Scanner;

public class ConsoleApplication {
    private final AuthenticationService authenticationService;

    @Inject
    public ConsoleApplication(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome! Choose an option:");
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. Exit");
            System.out.println("4. Show database");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    handleSignUp(scanner);
                    break;
                case 2:
                    handleLogIn(scanner);
                    break;
                case 3:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void handleSignUp(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            boolean success = authenticationService.signIn(username, password);
            if (success) {
                System.out.println("Sign Up successful!");
            } else {
                System.out.println("Username already exists. Please try again.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void handleLogIn(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            boolean success = authenticationService.logIn(username, password);
            if (success) {
                System.out.println("Log In successful!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
