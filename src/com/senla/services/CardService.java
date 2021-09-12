package com.senla.services;

import com.senla.dao.CardDAO;
import com.senla.entity.Card;
import com.senla.fileworker.FileWorker;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardService {

    private CardDAO cardDAO;

    public CardService(CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

    public void saveCard(Card card) {
        cardDAO.saveCard(card);
    }

    public List<Card> getListOfCards() throws IOException {
        return cardDAO.getListOfCards();
    }

    public void update(Card card) throws Exception {
        cardDAO.update(card);
    }

    public boolean compareCardNumber(String cardNumber) {

        String REGEX = "[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(cardNumber);
        if(matcher.matches()) {
            return matcher.matches();
        }
        else {
            System.out.println("Невалидный номер карты\n" +
                    "Введите валидный номер карты");
            return false;
        }
    }

    public boolean findCardNumber(String cardNumber, int code) throws IOException {
        for (var item : getListOfCards()) {
            if (item.getNumber().equals(cardNumber)) {
                if (item.getCode() == code) {
                    return true;
                } else {
                    System.out.println("Неверный пин-код");
                }
            }
        }
        System.out.println("Карта не найдена");
        return false;
    }

    public float checkBalance(Card card) {
        return card.getBalance();
    }

    public void addToBalance(int sum, Card card) throws Exception {
        if (sum <= 1000000) {
            card.setBalance(card.getBalance() + sum);
            cardDAO.update(card);
        } else {
            System.out.println("Cумма пополнения не должна превышать 1 000 000");
        }
    }

    public void withdrawBalance(int sum, Card card) throws Exception {
        if (sum <= card.getBalance()) {
            card.setBalance(card.getBalance() - sum);
            cardDAO.update(card);
        } else {
            System.out.println("Cумма снятие не должна превышать баланс");
        }
    }

    public Card getCard(String cardNumber) throws IOException {
        return cardDAO.getCard(cardNumber);
    }

}
