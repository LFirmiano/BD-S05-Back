package com.income.management.resources.transaction;

import com.income.management.exception.GenericTransactionException;
import com.income.management.model.GenericTransaction;
import com.income.management.model.TransferTransaction;
import com.income.management.resources.transaction.dto.TransactionDTO;
import com.income.management.resources.transaction.dto.TransferDTO;
import com.income.management.service.transaction.TransactionService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController()
public class TransactionTransferResource {

    private final TransactionService transactionService;

    public TransactionTransferResource(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transactions/transfers")
    public void createTransfer(@Valid @RequestBody TransferDTO transfer) throws GenericTransactionException {
        this.transactionService.createTransfer(transfer);
    }

    @GetMapping("/transactions/transfers/{id}")
    public TransferTransaction getTransfer(@PathVariable(value = "id") long id) throws GenericTransactionException {
        return this.transactionService.findTransfer(id);
    }

    @GetMapping("/transactions/transfers")
    public List<TransferTransaction> getAllTransfers() throws GenericTransactionException {
        return this.transactionService.findAllTransfers();
    }

    @DeleteMapping("/transactions/transfers/{id}")
    public void deleteTransfer(@PathVariable(value = "id") long id) throws GenericTransactionException {
        this.transactionService.deleteTransfer(id);
    }

}
