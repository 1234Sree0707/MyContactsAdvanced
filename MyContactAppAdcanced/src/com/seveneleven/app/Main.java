package com.seveneleven.app;


import java.util.Scanner;

import com.seveneleven.model.User;
import com.seveneleven.service.UserService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserService service = new UserService();

        try {

            System.out.println("Enter Name:");
            String name = sc.nextLine();

            System.out.println("Enter Email:");
            String email = sc.nextLine();

            System.out.println("Enter Password:");
            String password = sc.nextLine();

            System.out.println("Enter User Type (FREE / PREMIUM):");
            String type = sc.nextLine();

            User user = service.registerUser(email, password, name, type);

            System.out.println("User Registered Successfully!");
            System.out.println("User Type: " + user.getUserType());
            System.out.println("Email: " + user.getEmail());

        } catch (Exception e) {
            System.out.println("Registration Failed: " + e.getMessage());
        }

        sc.close();
    }
}