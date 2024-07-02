package shop.com.shopdb.modules.category;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.com.shopdb.modules.category.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
