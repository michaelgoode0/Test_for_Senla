package com.senla.entity;

public class Session {
    private Card card;

    public Session(){

    }
    public Session(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
