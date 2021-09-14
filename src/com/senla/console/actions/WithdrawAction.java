package com.senla.console.actions;

import com.senla.entity.Card;
import com.senla.entity.Session;
import com.senla.services.CardService;

import java.util.Scanner;

public class WithdrawAction implements IAction{

    private final Session session;
    private final CardService cardService;

    public WithdrawAction(Session session, CardService cardService) {
        this.session = session;
        this.cardService = cardService;
    }

    @Override
    public void execute() throws Exception {
        System.out.println("Enter sum");
        String sum=new Scanner(System.in).nextLine();
        Card card= session.getCard();
        try{
        cardService.withdrawBalance(Integer.parseInt(sum),card);
        }catch (NumberFormatException e){
            System.out.println("Incorrect sum");
        }
    }
}
