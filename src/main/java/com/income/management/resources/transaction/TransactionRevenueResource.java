package com.income.management.resources.transaction;

import com.income.management.exception.GenericTransactionException;
import com.income.management.model.GenericTransaction;
import com.income.management.model.Transaction;
import com.income.management.resources.transaction.dto.TransactionDTO;
import com.income.management.service.transaction.TransactionService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController()
public class TransactionRevenueResource {

    private final TransactionService transactionService;

    public TransactionRevenueResource(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transactions/revenues")
    public void createRevenues(@Valid @RequestBody TransactionDTO revenue) throws GenericTransactionException {
        this.transactionService.createRevenue(revenue);
    }

    @GetMapping("/transactions/revenues/{id}")
    public GenericTransaction getRevenue(@PathVariable(value = "id") long id) throws GenericTransactionException {
        return this.transactionService.findRevenue(id);
    }

    @GetMapping("/transactions/revenues")
    public List<GenericTransaction> getAllRevenues() throws GenericTransactionException {
        return this.transactionService.findAllRevenues();
    }

    @DeleteMapping("/transactions/revenues/{id}")
    public void deleteRevenue(@PathVariable(value = "id") long id) throws GenericTransactionException {
        this.transactionService.deleteRevenue(id);
    }

}
