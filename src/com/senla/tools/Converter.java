package com.senla.tools;

import com.senla.entity.Card;
import com.senla.enums.Status;

public class Converter {
    public static String convertCardToString(Card card){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder
                .append(card.getNumber())
                .append(" ")
                .append(card.getCode())
                .append(" ")
                .append(card.getBalance())
                .append(" ")
                .append(card.getStatus())
                .append(" ")
                .append(card.getUnCorrectInputCount());
        if(card.getStatus()== Status.BLOCKED){
            stringBuilder.append(" ").append(card.getDateOfBlock());
        }
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }
}
