package com.income.management.repository;

import com.income.management.conf.DatabaseConfig;
import com.income.management.exception.GenericTransactionException;
import com.income.management.model.account.AccountWithValue;
import com.income.management.model.category.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryRepository {


    private final DatabaseConfig dbConfig;

    public CategoryRepository(DatabaseConfig dbConfig) {
        this.dbConfig = dbConfig;
    }

    public void createCategory(String name) throws GenericTransactionException {
        String query = String.format("INSERT INTO Category (categoryName) values (\"%s\")", name);
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

    public List<Category> findCategories() throws GenericTransactionException {
        String query = "SELECT * FROM Category";

        List<Category> categories = new ArrayList<>();

        try {
            var conn = this.dbConfig.getConnection();
            var stm = conn.createStatement();
            var rs = stm.executeQuery(query);

            while (rs.next()) categories.add(
                    new Category(rs.getLong("id"), rs.getString("categoryName"))
                );

            rs.close();
            stm.close();
        } catch (Exception e) {
            throw new GenericTransactionException(e.getMessage(), e);
        }

        return categories;
    }

    public void deleteCategory(long id) throws GenericTransactionException {
        String query = String.format("DELETE FROM Category WHERE id = %d", id);

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
