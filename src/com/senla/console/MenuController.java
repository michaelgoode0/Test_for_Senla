package com.senla.console;

import com.senla.console.actions.Authorization;

import java.net.Authenticator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuController {

    private final Authorization authorization;
    private final Builder builder;
    private final Navigator navigator;

    public MenuController(Authorization authorization, Builder builder, Navigator navigator) {
        this.authorization = authorization;
        this.builder = builder;
        this.navigator = navigator;
    }

    public void run() {
        builder.buildMenu();
        navigator.setCurrentMenu(builder.getAuthMenu());
        try {
            Scanner scanner;
            while (true) {
                navigator.printMenu();
                int choice = 0;
                try {
                    scanner = new Scanner(System.in);
                    choice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Невалидный пункт меню");
                }
                navigator.navigate(choice);
                if (authorization.isAuthenticated()) {
                    navigator.setCurrentMenu(builder.getRootMenu());
                }
                if (!authorization.isAuthenticated()) {
                    navigator.setCurrentMenu(builder.getAuthMenu());
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

