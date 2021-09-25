package com.senla.dao;

import com.senla.entity.Card;
import com.senla.tools.Converter;
import com.senla.tools.fileworker.FileWorker;
import com.senla.tools.fileworker.Parser;

import java.util.List;

public class CardDao implements ICardDao {
    private final Parser parser;
    private final FileWorker fileWorker;
    private final String path;

    public CardDao(Parser parser, FileWorker fileWorker, String path) {
        this.fileWorker = fileWorker;
        this.parser = parser;
        this.path = path;
    }

    public void saveCard(Card card) {
        fileWorker.writeString(Converter.convertCardToString(card), true);
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
            update.append(Converter.convertCardToString(i));
        }
        fileWorker.writeString(update.toString(), false);
    }
}
