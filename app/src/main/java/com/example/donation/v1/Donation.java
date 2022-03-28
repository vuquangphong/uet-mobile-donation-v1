package com.example.donation.v1;

import androidx.annotation.NonNull;

public class Donation {
    private int id;
    private int amount;
    private String method;

    public Donation(int amount, String method) {
        this.amount = amount;
        this.method = method;
    }

    public Donation() {
        this.amount = 0;
        this.method = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public String getMethod() {
        return method;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @NonNull
    public String toString() {
        return id + ", " + amount + ", " + method;
    }
}
