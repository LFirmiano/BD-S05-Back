package com.income.management.model.account;

public class AccountWithValue extends Account{

    public AccountWithValue(long id, String name, float totalValue) {
        super(id, name);
        this.totalValue = totalValue;
    }

    private float totalValue;

    public float getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(float totalValue) {
        this.totalValue = totalValue;
    }
}
