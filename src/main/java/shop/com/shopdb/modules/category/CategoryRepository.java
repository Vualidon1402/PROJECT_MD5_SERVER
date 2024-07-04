package shop.com.shopdb.modules.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.com.shopdb.modules.category.model.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByStatusTrue();
    // phương thức tìm kiếm toàn bộ category theo chuỗi tương đối
    @Query("SELECT c FROM Category c WHERE c.category_name LIKE %:name%")
    List<Category> findByCategoryNameContaining(@Param("name") String name);
}
