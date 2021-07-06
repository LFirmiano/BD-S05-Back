package com.income.management.repository;

import com.income.management.conf.DatabaseConfig;
import com.income.management.exception.GenericTransactionException;
import com.income.management.model.account.Account;
import com.income.management.model.account.AccountWithValue;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepository {

    private final DatabaseConfig dbConfig;

    public AccountRepository(DatabaseConfig dbConfig) {
        this.dbConfig = dbConfig;
    }

    public void createAccount(String name) throws GenericTransactionException {
        String query = String.format("INSERT INTO UserAccount (userAccountName) values (\"%s\")", name);
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

    public void changeAccountName(long id, String newName) throws GenericTransactionException {
        String query = String.format("UPDATE UserAccount SET userAccountName = %s WHERE id = %d", newName, id);

        try {
            var conn = this.dbConfig.getConnection();
            var stm = conn.createStatement();
            var ok = stm.execute(query);

            stm.close();
            if (ok) throw new GenericTransactionException("Not Updated");
        } catch (Exception e) {
            throw new GenericTransactionException(e.getMessage(), e);
        }
    }

    public List<AccountWithValue> findAccounts() throws GenericTransactionException {
        String query = "SELECT ab.ACCID, ab.ACCNAME, SUM(ab2.VALOR_ENTRADA) - SUM(ab.VALOR_SAIDA) AS TOTAL " +
                "FROM ACCOUNT_DESPESAS ab " +
                "left join ACCOUNT_ENTRADAS ab2 " +
                "on ab.ACCID = ab2.ACCID GROUP BY ab.ACCID ORDER BY ab.ACCNAME";

        List<AccountWithValue> accounts = new ArrayList<>();
        try {
            var conn = this.dbConfig.getConnection();
            var stm = conn.createStatement();
            stm.execute("CREATE OR REPLACE VIEW ACCOUNT_DESPESAS as " +
                    "SELECT ua.id as ACCID," +
                    " ua.userAccountName AS ACCNAME," +
                    " tr.transValue      AS VALOR_SAIDA " +
                    "FROM UserAccount AS ua" +
                    "         LEFT JOIN Transaction tr" +
                    "                   on tr.userAccountOutId = ua.id;");
            var stm3 = conn.createStatement();
            String query2 = " CREATE OR REPLACE VIEW ACCOUNT_ENTRADAS as " +
                    " SELECT ua.id              as ACCID, " +
                    "       ua.userAccountName AS ACCNAME," +
                    "       tr.transValue      AS VALOR_ENTRADA " +
                    "FROM UserAccount AS ua" +
                    "         LEFT JOIN Transaction tr" +
                    "                   on tr.userAccountInId = ua.id";
            stm3.execute(query2);
            stm.close();
            stm3.close();

            var stm2 = conn.createStatement();
            var rs = stm2.executeQuery(query);

            while (rs.next()) accounts.add(
                    new AccountWithValue(
                            rs.getLong("ACCID"),
                            rs.getString("ACCNAME"),
                            rs.getFloat("TOTAL")
                    )
            );

            rs.close();
            stm2.close();
        } catch (Exception e) {
            throw new GenericTransactionException(e.getMessage(), e);
        }

        return accounts;
    }

    public void deleteAccount(long id) throws GenericTransactionException {
        String query = String.format("DELETE FROM UserAccount WHERE id = %d", id);

        try {
            var conn = this.dbConfig.getConnection();
            var stm = conn.createStatement();
            var rs = stm.execute(query);

            if (rs) throw new GenericTransactionException("No deleted.");
        } catch (Exception e) {
            throw new GenericTransactionException(e.getMessage(), e);
        }
    }
}
