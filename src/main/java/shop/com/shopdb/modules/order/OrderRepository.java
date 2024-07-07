package shop.com.shopdb.modules.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
    @Query(value = "SELECT * FROM orders WHERE orders.status = \"SHOPPING\" and orders.user_id = :userId", nativeQuery = true)
    Orders findByLoginId(@Param("userId") int userId);

    @Query(value = "SELECT * FROM orders WHERE orders.user_id = :userId", nativeQuery = true)
    List<Orders> findByUserId(@Param("userId") int userId);
}
