package shop.com.shopdb.modules.category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.com.shopdb.modules.category.CategoryRepository;
import shop.com.shopdb.modules.category.dto.CategoryRequest;
import shop.com.shopdb.modules.category.dto.CategoryResponse;
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

    public Page<CategoryResponse> getAllCategory(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Category> categories = categoryRepository.findByStatusTrue(pageable);
        return categories.map(this::convertToCategoryResponse);
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
    public Page<CategoryResponse> searchCategoryByName(int page, int pageSize, String name) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Category> categories = categoryRepository.findByCategoryNameContainingAndStatusTrue(name, pageable);

        return categories.map(this::convertToCategoryResponse);
    }

    private CategoryResponse convertToCategoryResponse(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setCategory_id(category.getCategory_id());
        categoryResponse.setCategory_name(category.getCategory_name());
        categoryResponse.setStatus(category.getStatus());
        categoryResponse.setImage(category.getImage());
        return categoryResponse;
    }
}
