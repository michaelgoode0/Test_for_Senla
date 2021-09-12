package com.senla.entity;

public class Card {

    private String number;
    private int balance;
    private int code;

    public Card(){

    }
    public Card(String number, int code, int balance){
        this.number=number;
        this.balance=balance;
        this.code=code;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString(){
        return this.number+","+this.code+","+this.balance;
    }
}
