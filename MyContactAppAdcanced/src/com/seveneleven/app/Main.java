package com.seveneleven.app;

import java.util.Scanner;

import com.seveneleven.service.UserService;
import com.seveneleven.service.AuthService;
import com.seveneleven.service.ProfileService;
import com.seveneleven.session.SessionManager;
import com.seveneleven.model.User;
import com.seveneleven.command.UpdateNameCommand;

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

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}