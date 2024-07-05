package shop.com.shopdb.modules.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.com.shopdb.modules.category.model.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Page<Category> findByStatusTrue(Pageable pageable);
    // phương thức tìm kiếm toàn bộ category theo chuỗi tương đối
    @Query("SELECT c FROM Category c WHERE c.category_name LIKE %:name% AND c.status = true")
    Page<Category> findByCategoryNameContainingAndStatusTrue(String name, Pageable pageable);
}
