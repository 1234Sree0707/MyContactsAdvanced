package com.seveneleven.app;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import com.seveneleven.builder.ContactBuilder;
import com.seveneleven.command.*;
import com.seveneleven.model.Contact;
import com.seveneleven.model.User;
import com.seveneleven.repository.ContactRepository;
import com.seveneleven.service.*;
import com.seveneleven.session.SessionManager;
import com.seveneleven.view.*;
import com.seveneleven.search.*;
import com.seveneleven.filter.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UserService userService = new UserService();
        AuthService authService = new AuthService();
        ProfileService profileService = new ProfileService();

        ContactRepository contactRepository = new ContactRepository();
        ContactService contactService = new ContactService(contactRepository);

        CommandManager manager = new CommandManager();
        

        try {

            // =========================
            // UC1: Register
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
            // UC2: Login
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
            // UC3: Profile Update
            // =========================
            User user = SessionManager.getInstance().getLoggedInUser();

            System.out.println("\n=== Profile Update ===");

            System.out.print("Enter new name: ");
            String newName = sc.nextLine();

            UpdateNameCommand cmd = new UpdateNameCommand(user, newName);
            profileService.executeCommand(cmd);

            System.out.println("Updated Name: " + user.getName());

            System.out.print("Enter new password: ");
            password = sc.nextLine();

            ChangePasswordCommand cmd1 = new ChangePasswordCommand(user, password);
            profileService.executeCommand(cmd1);

            // =========================
            // CONTACT MENU
            // =========================
            while (true) {

                System.out.println("\n====== CONTACT MENU ======");
                System.out.println("1. Add Contact");
                System.out.println("2. View Contacts");
                System.out.println("3. Edit Contact");
                System.out.println("4. Undo Edit");
                System.out.println("5. Redo Edit");
                System.out.println("6. Delete Contact");
                System.out.println("7. Tag contacts");
                System.out.println("8. Search Contacts");
                System.out.println("9. Advanced Search");
                System.out.println("9. View tagged contacts");
                System.out.println("11. Exit");


                System.out.print("Choose option: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    // =========================
                    // UC4: Add Contact
                    // =========================
                    case 1:

                        System.out.println("\n=== Add Contact ===");

                        System.out.print("Enter Contact Name: ");
                        name = sc.nextLine();

                        System.out.print("Enter Phone Number: ");
                        String phone = sc.nextLine();

                        System.out.print("Enter Email Address: ");
                        email = sc.nextLine();

                        System.out.print("Enter Contact Type (PERSON / ORG): ");
                        type = sc.nextLine();

                        Contact contact =
                                new ContactBuilder()
                                        .setName(name)
                                        .addPhone(phone)
                                        .addEmail(email)
                                        .setType(type)
                                        .build();

                        contactService.addContact(contact);

                        System.out.println("Contact Added Successfully!");

                        break;

                    // =========================
                    // UC5: View Contact
                    // =========================
                    case 2:

                        System.out.println("\n=== Contact List ===");

                        for (Contact c : contactRepository.getAllContacts()) {

                            ContactView view = new ContactViewImpl(c);

                            view = new UpperCaseNameDecorator(view);
                            view = new MaskEmailDecorator(view);

                            System.out.println(view.display());
                            System.out.println("--------------------");
                        }

                        break;

                    // =========================
                    // UC6: Edit Contact
                    // =========================
                    case 3:

                        System.out.print("Enter contact name to edit: ");
                        name = sc.nextLine();

                        Contact editContact = contactRepository.findByName(name);

                        if (editContact == null) {
                            System.out.println("Contact not found");
                            break;
                        }

                        System.out.print("Enter new name: ");
                        String newContactName = sc.nextLine();

                        EditContactCommand editCmd =
                                new EditContactCommand(editContact, newContactName);

                        manager.executeCommand(editCmd);

                        System.out.println("Contact updated: " + editContact.getName());

                        break;

                    case 4:

                        manager.undo();
                        System.out.println("Undo performed");

                        break;

                    case 5:

                        manager.redo();
                        System.out.println("Redo performed");

                        break;

                    // =========================
                    // UC7: Delete Contact
                    // =========================
                    case 6:

                        System.out.print("Enter contact name to delete: ");
                        name = sc.nextLine();

                        Contact deleteContact = contactRepository.findByName(name);

                        if (deleteContact == null) {

                            System.out.println("Contact not found");

                        } else {

                            System.out.print("Confirm delete? (Y/N): ");
                            String confirm = sc.nextLine();

                            if (confirm.equalsIgnoreCase("Y")) {

                                contactService.softDelete(deleteContact);

                                System.out.println("Contact deleted successfully");

                            } else {

                                System.out.println("Deletion cancelled");
                            }
                        }

                        break;

                    case 7:

                        System.out.println("\n=== Tag Contacts ===");

                        System.out.print("Enter tag name: ");
                        String tag = sc.nextLine();

                        List<Contact> contacts = contactRepository.getAllContacts();

                        contactService.tagContacts(contacts, tag);

                        System.out.println("Tag added successfully!");

                        break;
                    case 8:
                    	System.out.println("\n=== Search Contacts ===");

                    	System.out.print("Enter name to search: ");
                    	 name = sc.nextLine();

                    	SearchCriteria criteria = new NameCriteria(name);

                    	List<Contact> results = contactService.searchContacts(criteria);

                    	results.forEach(c -> System.out.println(c.getName()));
                    	
                    case 9:
                    	System.out.println("\n=== Advanced Filter ===");

                    	System.out.print("Enter tag: ");
                    	 tag = sc.nextLine();

                    	ContactFilter tagFilter = new TagFilter(tag);

                    	ContactSortStrategy strategy = new SortByNameStrategy();

                    	List<Contact> results1 = contactService.filterContacts(tagFilter, strategy);

                    	results1.forEach(c -> System.out.println(c.getName()));
                    
                    case 10:
                    	System.out.println("\n=== Tag Contacts ===");

                    	System.out.print("Enter tag name: ");
                    	String tagName = sc.nextLine();

                    	List<Contact> contacts1 = contactRepository.getAllContacts();

                    	contactService.tagContacts(contacts1, tagName);

                    case 11:

                        System.out.println("Exiting application...");
                        sc.close();
                        return;
                }
                
            }

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}