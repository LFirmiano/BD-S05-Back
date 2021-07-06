package com.income.management.resources.transaction.dto;

public class TransferDTO {
    private long account_in;
    private long account_out;
    private float value;
    private long category;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAccount_in() {
        return account_in;
    }

    public void setAccount_in(long account_in) {
        this.account_in = account_in;
    }

    public long getAccount_out() {
        return account_out;
    }

    public void setAccount_out(long account_out) {
        this.account_out = account_out;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }
}
