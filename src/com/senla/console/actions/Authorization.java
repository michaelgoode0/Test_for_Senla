package com.senla.console.actions;

import com.senla.entity.Card;
import com.senla.services.CardService;

import java.io.IOException;
import java.util.Scanner;

public class Authorization implements IAction{

    private Card card;

    private CardService cardService;

    public Authorization(CardService cardService) {
        this.cardService = cardService;
    }

    public void execute() throws IOException {
        String inputCardNumber = "";
        String inputCode = "";
        do {
            System.out.println("Введите номер карты или exit");
            do {
                inputCardNumber = new Scanner(System.in).nextLine();
            } while (!cardService.compareCardNumber(inputCardNumber));
            System.out.println("Введите пин-код");
            inputCode = new Scanner(System.in).nextLine();
        } while (!cardService.findCardNumber(inputCardNumber, Integer.parseInt(inputCode)));
        setCard(cardService.getCard(inputCardNumber));
        System.out.println(isAuthenticated());

    }

    public boolean isAuthenticated() {
        return this.card != null;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public CardService getCardService() {
        return cardService;
    }

    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }
}
