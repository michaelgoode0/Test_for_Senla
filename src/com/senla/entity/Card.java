package com.senla.entity;

import com.senla.enums.Status;

public class Card {

    private String number;
    private int balance;
    private int code;
    private Status status;
    private int unCorrectInputCount;
    private String dateOfBlock;
    public Card(){

    }

    public Card(String number, int code, int balance){
        this.number=number;
        this.balance=balance;
        this.code=code;
        this.status=Status.ACTIVE;
        this.unCorrectInputCount=0;
        dateOfBlock=null;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getUnCorrectInputCount() {
        return unCorrectInputCount;
    }

    public void setUnCorrectInputCount(int unCorrectInputCount) {
        this.unCorrectInputCount = unCorrectInputCount;
    }

    public String getDateOfBlock() {
        return dateOfBlock;
    }

    public void setDateOfBlock(String dateOfBlock) {
        this.dateOfBlock = dateOfBlock;
    }
}
