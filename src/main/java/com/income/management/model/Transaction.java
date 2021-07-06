package com.income.management.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.lang.Nullable;

import java.sql.Date;

@EqualsAndHashCode
@ToString
public class Transaction {
    private long id;
    private Date transDate;

    @Nullable
    private long accountIn;
    @Nullable
    private long accountOut;

    private float value;

    private long category;


    public Transaction(long id, Date transDate, long accountIn, long accountOut, long category) {
        this.id = id;
        this.transDate = transDate;
        this.accountIn = accountIn;
        this.accountOut = accountOut;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
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

    public long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }
}
