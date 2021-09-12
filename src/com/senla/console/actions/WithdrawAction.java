package com.senla.console.actions;

import com.senla.entity.Card;
import com.senla.services.CardService;

import java.util.Scanner;

public class WithdrawAction implements IAction{

    private Authorization authorization;
    private CardService cardService;

    public WithdrawAction(Authorization authorization, CardService cardService) {
        this.authorization = authorization;
        this.cardService = cardService;
    }

    @Override
    public void execute() throws Exception {
        System.out.println("Введите сумму");
        String sum=new Scanner(System.in).nextLine();
        Card card= authorization.getCard();
        cardService.withdrawBalance(Integer.parseInt(sum),card);
        System.out.println("Сумма снятия: "+sum);
    }
}
