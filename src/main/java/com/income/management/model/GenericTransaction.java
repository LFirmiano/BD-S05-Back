package com.income.management.model;

import java.sql.Date;

public class GenericTransaction {
    private final long id;
    private long account;
    private float value;
    private Date date;

    public GenericTransaction(long id, long account, float value, Date date) {
        this.id = id;
        this.account = account;
        this.value = value;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public long getAccount() {
        return account;
    }

    public void setAccount(long account) {
        this.account = account;
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
