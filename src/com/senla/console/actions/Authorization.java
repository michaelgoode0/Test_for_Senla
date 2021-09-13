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
        System.out.println("Введите номер карты");
        String inputCardNumber = new Scanner(System.in).nextLine();
        if (cardService.compareCardNumber(inputCardNumber)) {
            if (cardService.findCardNumber(inputCardNumber)) {
                Card card = cardService.getCard(inputCardNumber);
                cardService.unBlockCard(simpleDateFormat, card);
                String inputCode = "";
                if (card.getStatus() != Status.BLOCKED) {
                    System.out.println("Введите пин-код");
                    inputCode = new Scanner(System.in).nextLine();

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
                        System.out.println("Введите числовое значение пин-кода");
                    }
                } else {
                    System.out.println("Карта заблокирована");
                    long min = 1400-cardService.unBlockCard(simpleDateFormat, card);
                    long hours = 0;
                    if (min > 60) {
                        hours = min / 60;
                        min -= hours * 60;
                    }
                    System.out.println("Разблокировка через: " + hours + " часов " + min + " минут");
                }
            }
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

    public CardService getCardService() {
        return cardService;
    }

    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }
}
