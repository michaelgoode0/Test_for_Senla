package com.senla.services;

import com.senla.dao.CardDAO;
import com.senla.entity.Card;
import com.senla.enums.Status;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    public List<Card> getListOfCards() throws Exception {
        return cardDAO.getListOfCards();
    }

    public void update(Card card) throws Exception {
        cardDAO.update(card);
    }

    public boolean compareCardNumber(String cardNumber) {

        String REGEX = "\\d\\d\\d\\d-\\d\\d\\d\\d-\\d\\d\\d\\d-\\d\\d\\d\\d";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(cardNumber);
        if (matcher.matches()) {
            return matcher.matches();
        } else {
            System.out.println("Невалидный номер карты\n" +
                    "Введите валидный номер карты");
            return false;
        }
    }

    public boolean findCardNumber(String cardNumber) throws Exception {
        for (var item : getListOfCards()) {
            if (item.getNumber().equals(cardNumber)) {
                return true;
            }
        }
        System.out.println("Card with number: " + cardNumber + " doesn't exist");
        return false;
    }

    public boolean compareCardCode(int code) throws Exception {
        for (var item : getListOfCards()) {
            if (item.getCode()==code) {
                return true;
            }
        }
        System.out.println("Incorrect pin-code");
        return false;
    }

    public float checkBalance(Card card) {
        return card.getBalance();
    }

    public void addToBalance(int sum, Card card) throws Exception {
        if (sum <= 1000000) {
            card.setBalance(card.getBalance() + sum);
            cardDAO.update(card);
            System.out.println("Баланс пополнен на " + sum);
        } else {
            System.out.println("Cумма пополнения не должна превышать 1 000 000");
        }
    }

    public void withdrawBalance(int sum, Card card) throws Exception {
        if (sum <= card.getBalance()) {
            card.setBalance(card.getBalance() - sum);
            cardDAO.update(card);
            System.out.println("Сумма снятия: "+sum);
        } else {
            System.out.println("Cумма снятие не должна превышать баланс");
        }
    }

    public Card getCard(String cardNumber) throws  Exception {
        return cardDAO.getCard(cardNumber);
    }

    public void blockCard(SimpleDateFormat simpleDateFormat, Card card) throws Exception {
        Date date=new Date();
        card.setDateOfBlock(simpleDateFormat.format(date));
        card.setStatus(Status.BLOCKED);
        update(card);
    }
    public long unBlockCard(SimpleDateFormat simpleDateFormat,Card card) throws Exception {
        if(!card.getDateOfBlock().equals("0")) {
            Date currentDate = new Date();
            Date dateOfBlock = simpleDateFormat.parse(card.getDateOfBlock());
            long min = ((currentDate.getTime() - dateOfBlock.getTime()) / 60000);
            if (min > 3) {
                card.setStatus(Status.ACTIVE);
                card.setUnCorrectInputCount(0);
                card.setDateOfBlock("0");
            }
            update(card);
            return min;
        }
        else return 0;
    }

}
