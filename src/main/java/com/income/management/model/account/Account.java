package com.income.management.model.account;

public class Account {
    private long id;
    private String name;

    public Account(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Account(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
