package com.income.management.repository;

import com.income.management.conf.DatabaseConfig;
import com.income.management.exception.GenericTransactionException;
import com.income.management.model.GenericTransaction;
import com.income.management.model.TransferTransaction;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepository {


    private final DatabaseConfig dbConfig;

    public TransactionRepository(DatabaseConfig dbConfig) {
        this.dbConfig = dbConfig;
    }


    // SPENT

    public List<GenericTransaction> findAllSpents() throws GenericTransactionException {
        String query = ("SELECT tr.id, tr.transValue, tr.transDate, tr.userAccountOutId " +
                "FROM Transaction as tr where tr.userAccountInId IS NULL");
        List<GenericTransaction> trans = new ArrayList<>();

        try {
            var con = this.dbConfig.getConnection();
            var stm = con.createStatement();
            var rs = stm.executeQuery(query);

            while (rs.next()) trans.add(new GenericTransaction(rs.getLong("id"),
                    rs.getLong("userAccountOutId"),
                    rs.getFloat("transValue"), rs.getDate("transDate")));


            rs.close();
            stm.close();
        } catch (Exception e) {
            throw new GenericTransactionException(e.getMessage(), e);
        }

        return trans;
    }

    public void createSpentTransaction(String name,
                                       float transValue,
                                       long accountOut,
                                       long category
    )
            throws GenericTransactionException {
        String query = String.format("INSERT INTO Transaction " +
                        "(transactionName, transDate, transValue, userAccountOutId, categoryId) " +
                        "values (\"%s\", \"%s\", %f, %d, %d)",
                name, new Date(new java.util.Date().getTime()).toString(), transValue, accountOut, category);

        creationQuery(query);
    }


    public GenericTransaction findSpent(long id) throws GenericTransactionException {
        return this.findRegularTrans(id, false);
    }

    public void deleteTransaction(long id) throws GenericTransactionException {
        String query = String.format("DELETE FROM Transaction AS tr WHERE tr.id = %d", id);
        try {
            var conn = this.dbConfig.getConnection();
            var stm = conn.createStatement();
            var ok = stm.execute(query);

            stm.close();
            if (ok) throw new GenericTransactionException("Not created");
        } catch (Exception e) {
            throw new GenericTransactionException(e.getMessage(), e);
        }
    }


    public GenericTransaction findRegularTrans(long id, boolean isRevenue) throws GenericTransactionException {
        String query = String.format("SELECT tr.id, tr.transValue, tr.transDate, tr.userAccountInId " +
                "FROM Transaction as tr where tr.id = \"%d\" and tr.%s = NULL", id, isRevenue ? "userAccountOutId" : "userAccountInId");

        GenericTransaction gen = null;

        try {
            var con = this.dbConfig.getConnection();
            var stm = con.createStatement();
            var rs = stm.executeQuery(query);

            while (rs.next()) gen = new GenericTransaction(rs.getLong("id"),
                    rs.getLong("userAccountInId"),
                    rs.getFloat("transValue"), rs.getDate("transDate"));

            rs.close();
            stm.close();
        } catch (Exception e) {
            throw new GenericTransactionException(e.getMessage(), e);
        }

        return gen;
    }

    // REVENUE

    public List<GenericTransaction> findAllRevenues() throws GenericTransactionException {
        String query = ("SELECT tr.id, tr.transValue, tr.transDate, tr.userAccountInId " +
                "FROM Transaction as tr where tr.userAccountOutId = NULL");
        List<GenericTransaction> trans = new ArrayList<>();

        try {
            var con = this.dbConfig.getConnection();
            var stm = con.createStatement();
            var rs = stm.executeQuery(query);

            while (rs.next()) trans.add(new GenericTransaction(rs.getLong("id"),
                    rs.getLong("userAccountInId"),
                    rs.getFloat("transValue"), rs.getDate("transDate")));


            rs.close();
            stm.close();
        } catch (Exception e) {
            throw new GenericTransactionException(e.getMessage(), e);
        }

        return trans;
    }


    public void createRevenueTransaction(String name, float transValue, long accountIn, long category)
            throws GenericTransactionException {
        String query = String.format("INSERT INTO Transaction " +
                        "(transactionName, transDate, transValue, userAccountInId, categoryId) " +
                        "values (\"%s\", \"%s\", %f, %d, %d)",
                name, new Date(new java.util.Date().getTime()).toString(), transValue, accountIn, category);

        creationQuery(query);
    }

    public GenericTransaction findRevenue(long id) throws GenericTransactionException {
        return this.findRegularTrans(id, true);
    }

    private void creationQuery(String query) throws GenericTransactionException {
        try {
            var conn = this.dbConfig.getConnection();
            var stm = conn.createStatement();
            var ok = stm.execute(query);

            stm.close();
            if (ok) throw new GenericTransactionException("Not created");
        } catch (Exception e) {
            throw new GenericTransactionException(e.getMessage(), e);
        }
    }


    private long getMaxId() throws GenericTransactionException {
        String query = "SELECT MAX(id) FROM Transaction";
        long id;
        try {
            var con = this.dbConfig.getConnection();
            var stm = con.createStatement();
            var rsSet = stm.executeQuery(query);

            rsSet.next();
            id = rsSet.getLong(1);

            rsSet.close();
            stm.close();
        } catch (Exception e) {
            throw new GenericTransactionException(e.getMessage(), e);
        }

        return id;
    }

    public TransferTransaction findTransfer(long id) throws GenericTransactionException {
        String query = String.format("SELECT tr.id, tr.transValue, tr.transDate, tr.userAccountInId " +
                "FROM Transaction as tr where tr.userAccountOutId IS NOT NULL AND tr.userAccountInId IS NOT NULL AN tr.id = %d", id);

        TransferTransaction trans = null;

        try {
            var con = this.dbConfig.getConnection();
            var stm = con.createStatement();
            var rs = stm.executeQuery(query);

            while (rs.next()) trans = new TransferTransaction(
                    rs.getLong("id"),
                    rs.getLong("userAccountInId"),
                    rs.getLong("userAccountInId"),
                    rs.getFloat("transValue"),
                    rs.getDate("transDate")
            );

            rs.close();
            stm.close();
        } catch (Exception e) {
            throw new GenericTransactionException(e.getMessage(), e);
        }

        return trans;
    }

    public List<TransferTransaction> findAllTransfer() throws GenericTransactionException {
        String query = "SELECT tr.id, tr.transValue, tr.transDate, tr.userAccountInId, tr.userAccountOutId " +
                "FROM Transaction as tr where tr.userAccountOutId IS NOT NULL AND tr.userAccountInId IS NOT NULL";

        List<TransferTransaction> trans = new ArrayList<>();

        try {
            var con = this.dbConfig.getConnection();
            var stm = con.createStatement();
            var rs = stm.executeQuery(query);

            while (rs.next())  trans.add(new TransferTransaction(
                    rs.getLong("id"),
                    rs.getLong("userAccountInId"),
                    rs.getLong("userAccountInId"),
                    rs.getFloat("transValue"),
                    rs.getDate("transDate")
            ));

            rs.close();
            stm.close();
        } catch (Exception e) {
            throw new GenericTransactionException(e.getMessage(), e);
        }

        return trans;
    }

    public void createTransferTransaction(String name,
                                          float value,
                                          long accountIn,
                                          long accountOut,
                                          long category)
            throws GenericTransactionException {
        String query = String.format("INSERT INTO Transaction " +
                        "(transactionName, transDate, trans_value, userAccountInId, userAccountOutId, categoryId) " +
                        "values (%s, %s, %a, %d, %d, %d)",
                name, new Date(new java.util.Date().getTime()).toString(), value, accountIn, accountOut, category);

        creationQuery(query);
    }
}
