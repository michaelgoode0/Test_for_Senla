package com.senla.fileworker;

import com.senla.entity.Card;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final FileWorker fileWorker;
    public Parser(FileWorker fileWorker){
        this.fileWorker=fileWorker;
    }
    public List<Card> parseCards(String path) throws IOException {
        String[] items= fileWorker.getString(path).split(" ");
        List<Card> cards= new ArrayList<>();
        for(var item:items){
            if(!item.equals("")){
                String[] values=item.split(String.valueOf(","));
                cards.add(convertToCard(values));
            }
        }
        return cards;
    }
    public Card convertToCard(String []values){
        Card card=new Card();
        card.setNumber(values[0]);
        card.setCode(Integer.parseInt(values[1]));
        card.setBalance(Integer.parseInt(values[2]));

        return card;
    }
}
