package com.senla.console.actions;

import com.senla.entity.Session;
import com.senla.services.CardService;

public class CheckBalanceAction implements IAction{
    private final Session session;
    private final CardService cardService;

    public CheckBalanceAction(Session session, CardService cardService) {
        this.session = session;
        this.cardService = cardService;
    }

    @Override
    public void execute() throws Exception {
        System.out.println("Your balance: " + cardService.checkBalance(session.getCard()));
    }
}
