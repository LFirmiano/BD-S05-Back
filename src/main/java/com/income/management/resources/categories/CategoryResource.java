package com.income.management.resources.categories;

import com.income.management.exception.GenericTransactionException;
import com.income.management.model.category.Category;
import com.income.management.resources.categories.dto.CategoryDTO;
import com.income.management.service.category.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CategoryResource {

    final private CategoryService catService;

    public CategoryResource(CategoryService catService) {
        this.catService = catService;
    }

    @GetMapping("/categories")
    public List<Category> findAllCategories() throws GenericTransactionException {
        return this.catService.findAllCategories();
    }

    @PostMapping("/categories")
    public void createCategory(@Valid @RequestBody CategoryDTO catDto) throws GenericTransactionException {
        this.catService.createCategory(catDto.getName());
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable(value = "id") long id) throws GenericTransactionException {
        this.catService.deleteCategory(id);
    }
}
