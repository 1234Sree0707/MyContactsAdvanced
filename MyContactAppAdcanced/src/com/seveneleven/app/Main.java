package com.seveneleven.app;


import java.util.Scanner;

import com.seveneleven.model.User;
import com.seveneleven.service.UserService;


import com.seveneleven.service.UserService;
import com.seveneleven.service.AuthService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UserService userService = new UserService();
        AuthService authService = new AuthService();

        try {

            System.out.println("Register User");

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Email: ");
            String email = sc.nextLine();

            System.out.print("Password: ");
            String password = sc.nextLine();

            System.out.print("Type (FREE/PREMIUM): ");
            String type = sc.nextLine();

            userService.registerUser(email, password, name, type);

            System.out.println("Registration Successful!");

            System.out.println("\nLogin");

            System.out.print("Email: ");
            String loginEmail = sc.nextLine();

            System.out.print("Password: ");
            String loginPassword = sc.nextLine();

            boolean success = authService.login(loginEmail, loginPassword);

            if(success) {
                System.out.println("Login Successful!");
            } else {
                System.out.println("Invalid Credentials");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}