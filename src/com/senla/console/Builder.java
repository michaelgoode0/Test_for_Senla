package com.senla.console;

import com.senla.console.actions.AuthorizationAction;
import com.senla.console.actions.*;
import com.senla.console.items.Menu;
import com.senla.console.items.MenuItem;
import com.senla.entity.Session;
import com.senla.services.CardService;
import com.senla.services.SessionService;

public class Builder {
	private Menu rootMenu;
	private Menu authMenu;
	private final Session session;
	private final CardService cardService;
	private final SessionService sessionService;

	public Builder(Session session, CardService cardService, SessionService sessionService) {
		this.session = session;
		this.cardService = cardService;
		this.sessionService = sessionService;
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
		MenuItem auth = new MenuItem("Authorization", new AuthorizationAction(session,cardService), authMenu);
		MenuItem exit = new MenuItem("Exit", new ExitAction(), authMenu);
		authMenu.setMenuItems(new MenuItem[] {auth,exit});
		setAuthMenu(authMenu);

		rootMenu.setName("Root Menu Options:");
		MenuItem checkBalance= new MenuItem("Check balance",new CheckBalanceAction(session,cardService),rootMenu);
		MenuItem addToBalance = new MenuItem("Add to balance", new AddToBalanceAction(session,cardService),rootMenu);
		MenuItem withDrawBalance = new MenuItem("Withdraw", new WithdrawAction(session,cardService),rootMenu);
		MenuItem exit1 = new MenuItem("Log out", new LogOutAction(session,sessionService), rootMenu);

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
