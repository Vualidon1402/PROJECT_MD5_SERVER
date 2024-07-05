package shop.com.shopdb.modules.product.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.com.shopdb.modules.category.model.Category;

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
    // tên sản phẩm
    @Column(name = "product_name", nullable = false, unique = true)
    private String product_name;
    // mã sản phẩm
    private Integer sku;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;
    private Integer unitPrice;
    private Integer stock_quantity;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageProduct> images;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;
    @Column(name = "status", columnDefinition = "BIT DEFAULT 1")
    private Boolean status;
    @Temporal(TemporalType.DATE)
    private Date created_at;
    @Temporal(TemporalType.DATE)
    private Date updated_at;


    public Integer generateSku() {
        String productName = this.getProduct_name().replaceAll("\\s+", "").toUpperCase();
        String prefix = productName.length() > 3 ? productName.substring(0, 3) : productName;
        this.sku = (int) (Math.random() * 1000000) + Integer.parseInt(prefix);
        return this.sku;
    }
}
