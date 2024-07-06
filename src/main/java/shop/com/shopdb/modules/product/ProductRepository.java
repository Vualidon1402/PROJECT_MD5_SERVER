package shop.com.shopdb.modules.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shop.com.shopdb.modules.product.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findByStatusTrue(Pageable pageable);
    // phương thức tìm kiếm toàn bộ category theo chuỗi tương đối
    @Query("SELECT p FROM Product p WHERE p.product_name LIKE %:name% AND p.status = true")
    Page<Product> findByProductNameContainingAndStatusTrue(String name, Pageable pageable);
}
