package com.seveneleven.app;

import java.util.Scanner;
import com.seveneleven.view.*;
import com.seveneleven.command.*;

import com.seveneleven.service.UserService;
import com.seveneleven.service.AuthService;
import com.seveneleven.service.ProfileService;
import com.seveneleven.session.SessionManager;
import com.seveneleven.view.ContactViewImpl;
import com.seveneleven.model.User;
import com.seveneleven.command.UpdateNameCommand;
import com.seveneleven.command.ChangePasswordCommand;
import com.seveneleven.model.Contact;
import com.seveneleven.service.ContactService;
import com.seveneleven.builder.ContactBuilder;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UserService userService = new UserService();
        AuthService authService = new AuthService();
        ProfileService profileService = new ProfileService();

        try {

            // =========================
            // UC1 : Register
            // =========================

            System.out.println("=== Register User ===");

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Email: ");
            String email = sc.nextLine();

            System.out.print("Password: ");
            String password = sc.nextLine();

            System.out.print("Type (FREE/PREMIUM): ");
            String type = sc.nextLine();

            userService.registerUser(email, password, name, type);

            System.out.println("Registration Successful!\n");


            // =========================
            // UC2 : Login
            // =========================

            System.out.println("=== Login ===");

            System.out.print("Email: ");
            String loginEmail = sc.nextLine();

            System.out.print("Password: ");
            String loginPassword = sc.nextLine();

            boolean loginSuccess = authService.login(loginEmail, loginPassword);

            if (!loginSuccess) {
                System.out.println("Login failed. Exiting...");
                return;
            }


            // =========================
            // UC3 : Profile Update
            // =========================

            User user = SessionManager.getInstance().getLoggedInUser();

            System.out.println("\n=== Profile Update ===");

            System.out.print("Enter new name: ");
            String newName = sc.nextLine();

            UpdateNameCommand cmd = new UpdateNameCommand(user, newName);

            profileService.executeCommand(cmd);

            System.out.println("Profile updated successfully!");
            System.out.println("Updated Name: " + user.getName());
            System.out.println("Enter new password");
            password=sc.nextLine();
            ChangePasswordCommand cmd1 = new ChangePasswordCommand(user, password);

            profileService.executeCommand(cmd1);
            ContactService contactService = new ContactService();
            Contact contact;

            while (true) {

                System.out.println("\n=== Add Contact ===");

                System.out.print("Enter Contact Name: ");
                 name = sc.nextLine();

                System.out.print("Enter Phone Number: ");
                String phone = sc.nextLine();

                System.out.print("Enter Email Address: ");
                 email = sc.nextLine();

                System.out.print("Enter Contact Type (PERSON / ORG): ");
                 type = sc.nextLine();

                 contact =
                        new ContactBuilder()
                                .setName(name)
                                .addPhone(phone)
                                .addEmail(email)
                                .setType(type)
                                .build();

                contactService.addContact(contact);

                System.out.println("Contact Added Successfully!");

                // Ask user if they want to add another contact
                System.out.print("\nDo you want to add another contact? (Y/N): ");
                String choice = sc.nextLine();

                if (!choice.equalsIgnoreCase("Y")) {
                    break;
                }
                
            }
            ContactView view = new ContactViewImpl(contact);

            view = new UpperCaseNameDecorator(view);
            view = new MaskEmailDecorator(view);

            System.out.println("\n=== Contact Details ===");
            System.out.println(view.display());
            CommandManager manager = new CommandManager();

            while (true) {

                System.out.println("\n=== Edit Contact Menu ===");
                System.out.println("1. Edit Contact Name");
                System.out.println("2. Undo Last Edit");
                System.out.println("3. Redo Last Edit");
                System.out.println("4. Exit");

                System.out.print("Choose option: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:

                        System.out.print("Enter new name: ");
                        String newName1 = sc.nextLine();

                        EditContactCommand cmd2 = new EditContactCommand(contact, newName1);

                        manager.executeCommand(cmd2);

                        System.out.println("Contact name updated: " + contact.getName());

                        break;

                    case 2:

                        manager.undo();

                        System.out.println("Undo performed. Current name: " + contact.getName());

                        break;

                    case 3:

                        manager.redo();

                        System.out.println("Redo performed. Current name: " + contact.getName());

                        break;

                    case 4:

                        System.out.println("Exiting edit menu...");
                        return;

                    default:

                        System.out.println("Invalid option.");
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}