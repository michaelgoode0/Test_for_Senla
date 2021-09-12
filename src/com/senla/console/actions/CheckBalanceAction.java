package com.senla.console.actions;

import com.senla.services.CardService;

public class CheckBalanceAction implements IAction{
    private Authorization authorization;
    private CardService cardService;

    public CheckBalanceAction(Authorization authorization, CardService cardService) {
        this.authorization = authorization;
        this.cardService = cardService;
    }

    @Override
    public void execute() throws Exception {
        System.out.println("Ваш баланс равен: " + authorization.getCard().getBalance());
    }
}
