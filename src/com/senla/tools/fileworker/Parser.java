package com.senla.tools.fileworker;

import com.senla.entity.Card;
import com.senla.enums.Status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final FileWorker fileWorker;
    public Parser(FileWorker fileWorker){
        this.fileWorker=fileWorker;
    }
    public List<Card> parseCards(String path) throws IOException {
        String[] items= fileWorker.getString(path).split("\n");
        List<Card> cards= new ArrayList<>();
        for(var item:items){
            if(!item.equals("")){
                String[] values=item.split(" ");
                cards.add(convertToCard(values));
            }
        }
        return cards;
    }
    public Card convertToCard(String []values) {
        Card card=new Card();
        card.setNumber(values[0]);
        card.setCode(Integer.parseInt(values[1]));
        card.setBalance(Integer.parseInt(values[2]));
        card.setStatus(Status.valueOf(values[3]));
        card.setUnCorrectInputCount(Integer.parseInt(values[4]));
        if(card.getStatus()==Status.BLOCKED) {
            card.setDateOfBlock(values[5]);
        }
        return card;
    }
}
