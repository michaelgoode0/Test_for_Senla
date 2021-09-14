package com.senla.console;

import com.senla.entity.Session;
import com.senla.services.SessionService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuController {

    private final SessionService service;
    private final Session session;
    private final Builder builder;
    private final Navigator navigator;

    public MenuController(SessionService service, Session session, Builder builder, Navigator navigator) {
        this.service = service;
        this.session = session;
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
                    System.out.println("Invalid menu item");
                }
                navigator.navigate(choice);
                if (service.isAuthenticated(session)) {
                    navigator.setCurrentMenu(builder.getRootMenu());
                }
                if (!service.isAuthenticated(session)) {
                    navigator.setCurrentMenu(builder.getAuthMenu());
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

