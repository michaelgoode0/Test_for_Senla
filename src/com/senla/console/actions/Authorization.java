package com.senla.console.actions;

import com.senla.entity.Card;
import com.senla.services.CardService;
import com.senla.enums.Status;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Authorization implements IAction {

    private Card card;

    private CardService cardService;

    public Authorization(CardService cardService) {
        this.cardService = cardService;
    }

    public void execute() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd/HH:mm");
        System.out.println("Enter card number");
        String inputCardNumber = new Scanner(System.in).nextLine();
        if (cardService.compareCardNumber(inputCardNumber)) {
            if (cardService.findCardNumber(inputCardNumber)) {
                Card card = cardService.getCard(inputCardNumber);
                cardService.unBlockCard(simpleDateFormat, card);
                if (card.getStatus() != Status.BLOCKED) {
                    System.out.println("Enter pin-code");
                    String inputCode = new Scanner(System.in).nextLine();
                    findCode(inputCode,simpleDateFormat,card);
                } else {
                    System.out.println("Card blocked");
                    System.out.println("Unblock after: " + (1400-cardService.unBlockCard(simpleDateFormat,card)) + " minutes");
                }
            }
        }
    }
    public void findCode(String inputCode,SimpleDateFormat simpleDateFormat,Card card) throws Exception {
        try {
            if (cardService.compareCardCode(Integer.parseInt(inputCode))) {
                card.setUnCorrectInputCount(0);
                cardService.update(card);
                setCard(card);
            } else {
                card.setUnCorrectInputCount(card.getUnCorrectInputCount() + 1);
                cardService.update(card);
                if (card.getUnCorrectInputCount() == 3) {
                    cardService.blockCard(simpleDateFormat, card);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Enter the numerical value of the pin-code");
        }
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
}
