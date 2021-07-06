package com.income.management.service.transaction;

import com.income.management.exception.GenericTransactionException;
import com.income.management.model.GenericTransaction;
import com.income.management.model.Transaction;
import com.income.management.model.TransferTransaction;
import com.income.management.repository.TransactionRepository;
import com.income.management.resources.transaction.dto.TransactionDTO;
import com.income.management.resources.transaction.dto.TransferDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepo;

    public TransactionService(TransactionRepository transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    //-----Spend -----
    public void createSpent(TransactionDTO spent) throws GenericTransactionException {
        this.transactionRepo.createSpentTransaction(spent.getName(), spent.getValue(), spent.getAccount(), spent.getCategory());
    }

    public GenericTransaction findSpend(long id) throws GenericTransactionException {
        return this.transactionRepo.findSpent(id);
    }

    public List<GenericTransaction> findAllSpends() throws GenericTransactionException {
        return this.transactionRepo.findAllSpents();
    }

    public void deleteSpent(long id) throws GenericTransactionException {
        this.transactionRepo.deleteTransaction(id);
    }

    //-----Revenue -----
    public void createRevenue(TransactionDTO revenue) throws GenericTransactionException {
        this.transactionRepo.createRevenueTransaction(revenue.getName(), revenue.getValue(), revenue.getAccount(), revenue.getCategory());
    }

    public GenericTransaction findRevenue(long id) throws GenericTransactionException {
        return this.transactionRepo.findRevenue(id);
    }

    public List<GenericTransaction> findAllRevenues() throws GenericTransactionException {
        return this.transactionRepo.findAllRevenues();
    }

    public void deleteRevenue(long id) throws GenericTransactionException {
        this.transactionRepo.deleteTransaction(id);
    }

    //-----Transfer -----
    public void createTransfer(TransferDTO transfer) throws GenericTransactionException {
        this.transactionRepo.createTransferTransaction(
                transfer.getName(),
                transfer.getValue(),
                transfer.getAccount_in(),
                transfer.getAccount_out(),
                transfer.getCategory()
        );
    }

    public TransferTransaction findTransfer(long id) throws GenericTransactionException {
        return this.transactionRepo.findTransfer(id);
    }

    public List<TransferTransaction> findAllTransfers() throws GenericTransactionException {
        return this.transactionRepo.findAllTransfer();
    }

    public void deleteTransfer(long id) throws GenericTransactionException {
        this.transactionRepo.deleteTransaction(id);
    }

}
