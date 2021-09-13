package com.senla.console.actions;

import com.senla.services.CardService;

public class CheckBalanceAction implements IAction{
    private final Authorization authorization;
    private final CardService cardService;

    public CheckBalanceAction(Authorization authorization, CardService cardService) {
        this.authorization = authorization;
        this.cardService = cardService;
    }

    @Override
    public void execute() throws Exception {
        System.out.println("Your balance: " + cardService.checkBalance(authorization.getCard()));
    }
}
