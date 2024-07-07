package shop.com.shopdb.modules.orderDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query(value = "SELECT * FROM orderdetail o WHERE o.order_id = :orderId and o.product_id = :productId", nativeQuery = true)
    OrderDetail findItem(@Param("orderId") int orderId, @Param("productId") int productId);
}
