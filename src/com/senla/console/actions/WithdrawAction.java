package com.senla.console.actions;

import com.senla.entity.Card;
import com.senla.services.CardService;

import java.util.Scanner;

public class WithdrawAction implements IAction{

    private final Authorization authorization;
    private final CardService cardService;

    public WithdrawAction(Authorization authorization, CardService cardService) {
        this.authorization = authorization;
        this.cardService = cardService;
    }

    @Override
    public void execute() throws Exception {
        System.out.println("Введите сумму");
        String sum=new Scanner(System.in).nextLine();
        Card card= authorization.getCard();
        try{
        cardService.withdrawBalance(Integer.parseInt(sum),card);
        }catch (NumberFormatException e){
            System.out.println("Некорректная сумма");
        }
    }
}
