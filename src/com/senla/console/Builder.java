package com.senla.console;

import com.senla.console.actions.Authorization;
import com.senla.console.actions.*;
import com.senla.console.items.Menu;
import com.senla.console.items.MenuItem;
import com.senla.services.CardService;

public class Builder {


	private Menu rootMenu;
	private Menu authMenu;
	private final Authorization authorization;
	private final CardService cardService;

	public Builder(Authorization authorization, CardService cardService) {
		this.authorization = authorization;
		this.cardService = cardService;
	}

	public Menu getAuthMenu() {
		return authMenu;
	}

	public void setAuthMenu(Menu authMenu) {
		this.authMenu = authMenu;
	}

	public void buildMenu() {
		Menu rootMenu = new Menu();
		Menu authMenu=new Menu();
		authMenu.setName("Authorization:");
		MenuItem auth = new MenuItem("Authorization", authorization, authMenu);
		MenuItem exit = new MenuItem("Exit", new ExitAction(), authMenu);
		authMenu.setMenuItems(new MenuItem[] {auth,exit});
		setAuthMenu(authMenu);

		rootMenu.setName("Root Menu Options:");
		MenuItem checkBalance= new MenuItem("Check balance",new CheckBalanceAction(authorization,cardService),rootMenu);
		MenuItem addToBalance = new MenuItem("Add to balance", new AddToBalanceAction(authorization,cardService),rootMenu);
		MenuItem withDrawBalance = new MenuItem("Withdraw", new WithdrawAction(authorization,cardService),rootMenu);
		MenuItem exit1 = new MenuItem("Log out", new LogOutAction(authorization), rootMenu);

		rootMenu.setMenuItems(new MenuItem[] {checkBalance,addToBalance,withDrawBalance,exit1});
		setRootMenu(rootMenu);
	}

	public Menu getRootMenu() {
		return rootMenu;
	}

	public void setRootMenu(Menu rootMenu) {
		this.rootMenu = rootMenu;
	}

}
