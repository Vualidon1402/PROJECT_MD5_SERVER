package shop.com.shopdb.modules.category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.com.shopdb.modules.category.CategoryRepository;
import shop.com.shopdb.modules.category.dto.CategoryRequest;
import shop.com.shopdb.modules.category.dto.CategoryRespone;
import shop.com.shopdb.modules.category.dto.CategoryUpdateRq;
import shop.com.shopdb.modules.category.model.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category addCategory(CategoryRequest categoryRq) {
        Category category = new Category();
        category.setCategory_name(categoryRq.getCategory_name());
        category.setImage(categoryRq.getImage());
       return categoryRepository.save(category);
    }

    public void deleteCategory(Integer categoryId) {
        Optional<Category> categoryOpt = categoryRepository.findById(categoryId);
        if(categoryOpt.isPresent()){
            // chuyeern status = false
            Category category = categoryOpt.get();
            category.setStatus(false);
            categoryRepository.save(category);
        }else {
            throw new RuntimeException("Category not found");
        }
    }

    public List<Category> getActiveCategories() {
        return categoryRepository.findByStatusTrue();
    }


    public void updateCategory(Integer categoryId, CategoryUpdateRq categoryRq) {
        Optional<Category> categoryOpt = categoryRepository.findById(categoryId);
        if(categoryOpt.isPresent()){
            Category category = categoryOpt.get();
            category.setCategory_name(categoryRq.getCategory_name());
            category.setStatus(categoryRq.getStatus());
            System.out.println("Image: " + categoryRq.getImage());
            category.setImage(categoryRq.getImage());
            categoryRepository.save(category);
        }else {
            throw new RuntimeException("Category not found");
        }
    }


    public Category getCategoryById(Integer categoryId) {
        Optional<Category> categoryOpt = categoryRepository.findById(categoryId);
        if(categoryOpt.isPresent()){
            return categoryOpt.get();
        }else {
            throw new RuntimeException("Category not found");
        }
    }
    public List<Category> searchCategoryByName(String name) {
        return categoryRepository.findByCategoryNameContaining(name);
    }
}
