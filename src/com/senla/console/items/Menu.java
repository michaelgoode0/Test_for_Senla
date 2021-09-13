package com.senla.console.items;


import com.senla.console.items.MenuItem;

public class Menu {


	private String name;
	private MenuItem[] menuItems;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MenuItem[] getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(MenuItem[] menuItems) {
		this.menuItems = menuItems;
	}

}
