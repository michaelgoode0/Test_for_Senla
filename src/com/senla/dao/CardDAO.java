package com.senla.dao;

import com.senla.entity.Card;
import com.senla.fileworker.FileWorker;
import com.senla.fileworker.Parser;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CardDAO implements ICardDAO {
    private final Parser parser;
    private final FileWorker fileWorker;
    private final String path;

    public CardDAO(Parser parser, FileWorker fileWorker, String path) {
        this.fileWorker = fileWorker;
        this.parser = parser;
        this.path = path;
    }

    public void saveCard(Card card) {
        fileWorker.writeString(card.toString(), true);
    }

    public List<Card> getListOfCards() throws Exception {
        return parser.parseCards(path);
    }
    public Card getCard(String cardNumber) throws Exception{
        for (var i : getListOfCards()) {
            if (i.getNumber().equals(cardNumber)) {
                return i;
            }
        }
        return null;
    }

    public void update(Card card) throws Exception {
        List<Card> listOfCards= getListOfCards();
        for (var i : listOfCards) {
            if (i.getNumber().equals(card.getNumber())) {
                var index = listOfCards.indexOf(i);
                listOfCards.set(index, card);
            }
        }
        StringBuilder update = new StringBuilder();
        for (var i : listOfCards) {
            update.append(i.toString()).append(" ");
        }
        fileWorker.writeString(update.toString(), false);
    }

}
