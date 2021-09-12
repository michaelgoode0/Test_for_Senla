package com.senla.console;

import com.senla.console.actions.Authorization;

import java.net.Authenticator;
import java.util.Scanner;

public class MenuController {

	private Authorization authorization;
	private Builder builder;
	private Navigator navigator;

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
				scanner= new Scanner(System.in);
				int choice = scanner.nextInt();
				navigator.navigate(choice);
				if(authorization.isAuthenticated()){
					navigator.setCurrentMenu(builder.getRootMenu());
				}
				if(!authorization.isAuthenticated()) {
					navigator.setCurrentMenu(builder.getAuthMenu());
				}
			}
		} catch (Exception e) {
			System.out.println("d");
			e.printStackTrace();
		}
	}
}
