package shop.com.shopdb.modules.category;

import jakarta.persistence.GeneratedValue;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.com.shopdb.modules.category.dto.AddCategoryRes;
import shop.com.shopdb.modules.category.dto.CategoryRequest;
import shop.com.shopdb.modules.category.dto.CategoryUpdateRq;
import shop.com.shopdb.modules.category.model.Category;
import shop.com.shopdb.modules.category.service.CategoryService;

import java.util.List;
@CrossOrigin("*")
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getActiveCategories());
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

    @PostMapping("/category/add")
    public ResponseEntity<AddCategoryRes> addCategory(@Valid @RequestBody CategoryRequest categoryRq) {
        System.out.println("đã vào add" + categoryRq.getCategory_name());
        System.out.println("đã vào add" + categoryRq.getImage());
        try {
            Category category = categoryService.addCategory(categoryRq);
            System.out.println("Category added successfully");
            return new ResponseEntity<AddCategoryRes>(new AddCategoryRes(category, "thêm thành công"), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<AddCategoryRes>(new AddCategoryRes(null, "loi gi do"), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/category/update/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable Integer categoryId, @Valid @RequestBody CategoryUpdateRq categoryUpdate) {
        try {
            categoryService.updateCategory(categoryId, categoryUpdate);
            return ResponseEntity.ok("Category updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/category/delete/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer categoryId) {
        try {
            categoryService.deleteCategory(categoryId);
            return ResponseEntity.ok("Category deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
