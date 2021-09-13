package com.senla.dao;

import com.senla.entity.Card;

import java.util.List;

public interface ICardDAO {
    void saveCard(Card card);

    List<Card> getListOfCards() throws Exception;

    Card getCard(String cardNumber) throws Exception;

    void update(Card card) throws Exception;
}
