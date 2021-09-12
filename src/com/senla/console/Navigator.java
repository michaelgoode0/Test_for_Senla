package com.senla.console;

import com.senla.console.items.MenuItem;

import java.io.IOException;



public class Navigator {

	private Menu currentMenu;

	public void printMenu() {
		System.out.println(System.lineSeparator() + getCurrentMenu().getName());
		int itemOrdinalNumber = 0;
		for (var item: getCurrentMenu().getMenuItems()) {
			itemOrdinalNumber++;
			System.out.println(itemOrdinalNumber + " " + item.getTitle());
		}
	}

	public void navigate(Integer index) throws Exception {
		MenuItem menuItem = currentMenu.getMenuItems()[index - 1];
		if (menuItem.getAction() != null) {
			menuItem.doAction();
			System.out.println("Press enter to continue");
			System.in.read();
		}
		clearConsole();
	/*	setCurrentMenu(menuItem.getNextMenu());
		printMenu();*/
	}

	private void clearConsole() {

	}

	public Menu getCurrentMenu() {
		return currentMenu;
	}

	public void setCurrentMenu(Menu currentMenu) {
		this.currentMenu = currentMenu;
	}

}
