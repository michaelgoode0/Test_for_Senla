package com.senla.services;

import com.senla.dao.CardDao;
import com.senla.entity.Card;
import com.senla.enums.Status;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardService {

    private final CardDao cardDAO;

    public CardService(CardDao cardDAO) {
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
            System.out.println("Invalid card number");
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
            System.out.println("Balance was filled with " + sum);
        } else {
            System.out.println("Replenishment: amount is too high");
        }
    }

    public void withdrawBalance(int sum, Card card) throws Exception {
        if (sum <= card.getBalance()) {
            card.setBalance(card.getBalance() - sum);
            cardDAO.update(card);
            System.out.println("Withdraw sum: "+sum);
        } else {
            System.out.println("Withdraw sum is more than balance");
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
        if(card.getStatus()==Status.BLOCKED) {
            Date currentDate = new Date();
            Date dateOfBlock = simpleDateFormat.parse(card.getDateOfBlock());
            long min = ((currentDate.getTime() - dateOfBlock.getTime()) / 60000);
            if (min > 1400) {
                card.setStatus(Status.ACTIVE);
                card.setUnCorrectInputCount(0);
                card.setDateOfBlock(null);
            }
            update(card);
            return min;
        }
        return 0;
    }
}
