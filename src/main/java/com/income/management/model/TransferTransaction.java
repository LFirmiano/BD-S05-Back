package com.income.management.model;

import java.sql.Date;

public class TransferTransaction {
    private final long id;
    private long accountIn;
    private long accountOut;
    private float value;
    private Date date;

    public TransferTransaction(long id, long accountIn, long accountOut, float value, Date date) {
        this.id = id;
        this.accountIn = accountIn;
        this.accountOut = accountOut;
        this.value = value;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public long getAccountIn() {
        return accountIn;
    }

    public void setAccountIn(long accountIn) {
        this.accountIn = accountIn;
    }

    public long getAccountOut() {
        return accountOut;
    }

    public void setAccountOut(long accountOut) {
        this.accountOut = accountOut;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
