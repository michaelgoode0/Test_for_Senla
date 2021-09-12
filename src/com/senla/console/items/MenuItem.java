package com.senla.console.items;

import com.senla.console.Menu;
import com.senla.console.actions.IAction;

import java.io.IOException;

public class MenuItem {

	private String title;
	private IAction action;
	private Menu nextMenu;

	public MenuItem(String title, IAction action, Menu nextMenu) {
		this.title = title;
		this.action = action;
		this.nextMenu = nextMenu;
	}

	public void doAction() throws Exception {
		getAction().execute();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public IAction getAction() {
		return action;
	}

	public void setAction(IAction action) {
		this.action = action;
	}

	public Menu getNextMenu() {
		return nextMenu;
	}

	public void setNextMenu(Menu nextMenu) {
		this.nextMenu = nextMenu;
	}
}
