package com.income.management.service.category;

import com.income.management.exception.GenericTransactionException;
import com.income.management.model.category.Category;
import com.income.management.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    final private CategoryRepository catRepo;

    public CategoryService(CategoryRepository catRepo) {
        this.catRepo = catRepo;
    }

    public void createCategory(String name) throws GenericTransactionException {
        this.catRepo.createCategory(name);
    }

    public List<Category> findAllCategories() throws GenericTransactionException {
        return this.catRepo.findCategories();
    }

    public void deleteCategory(long id) throws GenericTransactionException {
        this.catRepo.deleteCategory(id);
    }
}
