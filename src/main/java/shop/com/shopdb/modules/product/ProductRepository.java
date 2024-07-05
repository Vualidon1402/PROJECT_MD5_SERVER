package shop.com.shopdb.modules.product;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.com.shopdb.modules.product.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByStatusTrue();
}
