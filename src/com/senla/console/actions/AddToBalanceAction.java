package com.senla.console.actions;

import com.senla.entity.Card;
import com.senla.services.CardService;

import java.util.Scanner;

public class AddToBalanceAction implements IAction{

    private Authorization authorization;
    private CardService cardService;

    public AddToBalanceAction(Authorization authorization, CardService cardService) {
        this.authorization = authorization;
        this.cardService = cardService;
    }

    @Override
    public void execute() throws Exception {
        System.out.println("Введите сумму для пополнения");
        String sum=new Scanner(System.in).nextLine();
        Card card = authorization.getCard();
        cardService.addToBalance(Integer.parseInt(sum),card);
        System.out.println("Баланс пополнен на " + sum);
    }
}
