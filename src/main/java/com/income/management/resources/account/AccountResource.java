package com.income.management.resources.account;

import com.income.management.exception.GenericTransactionException;
import com.income.management.model.account.AccountWithValue;
import com.income.management.resources.account.dot.AccountDTO;
import com.income.management.service.account.AccountService;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController()
public class AccountResource {

    private final AccountService accountService;

    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/accounts")
    public void createAccount(@Valid @RequestBody AccountDTO acc) throws GenericTransactionException {
        this.accountService.createAccount(acc.getName());
    }

    @PutMapping("/accounts/{id}")
    public void changeAccountName(@Valid @RequestBody AccountDTO acc, @PathVariable(value = "id") long id) throws GenericTransactionException {
        this.accountService.changeAccountName(id, acc.getName());
    }

    @GetMapping("/accounts")
    public List<AccountWithValue> getAllAccounts() throws GenericTransactionException {
        return this.accountService.findAccounts();
    }


    @DeleteMapping("/accounts/{id}")
    public void deleteAccount(@PathVariable(value = "id") long id) throws GenericTransactionException {
        this.accountService.deleteAccount(id);
    }

}
