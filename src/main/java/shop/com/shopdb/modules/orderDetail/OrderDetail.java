package shop.com.shopdb.modules.orderDetail;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.com.shopdb.modules.order.Orders;
import shop.com.shopdb.modules.product.model.Product;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Orders orders;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    private double price;
    private int quantity;


    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", orders=" + orders +
                ", productvariant id=" + product.getProduct_id() +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
