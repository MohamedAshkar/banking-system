package com.servlet.register;

public class Transaction {
    private String transactionType;
    private String date;
    private double amount;

    public Transaction(String transactionType, String date, double amount) {
        this.transactionType = transactionType;
        this.date = date;
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
