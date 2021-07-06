package com.income.management.service.account;

import com.income.management.exception.GenericTransactionException;
import com.income.management.model.account.AccountWithValue;
import com.income.management.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepo;

    public AccountService(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }


    public void createAccount(String name) throws GenericTransactionException {
        this.accountRepo.createAccount(name);
    }

    public void changeAccountName(long id, String newName) throws GenericTransactionException {
        this.accountRepo.changeAccountName(id, newName);
    }

    public List<AccountWithValue> findAccounts() throws GenericTransactionException {
        return this.accountRepo.findAccounts();
    }

    public void deleteAccount(long id) throws GenericTransactionException {
        this.accountRepo.deleteAccount(id);
    }

}
