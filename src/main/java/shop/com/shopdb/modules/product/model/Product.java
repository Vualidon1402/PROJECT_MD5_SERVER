package shop.com.shopdb.modules.product.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.com.shopdb.modules.category.model.Category;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_id;
    private String product_name;
    // mã sản phẩm
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sku;
    private String description;
    @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal unitPrice;
    private Integer stock_quantity;
    private String image;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(name = "status", columnDefinition = "BIT DEFAULT 1")
    private Boolean status;
    @Temporal(TemporalType.DATE)
    private Date created_at;
    @Temporal(TemporalType.DATE)
    private Date updated_at;

}
