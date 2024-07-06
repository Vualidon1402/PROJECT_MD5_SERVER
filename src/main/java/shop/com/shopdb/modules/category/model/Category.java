package shop.com.shopdb.modules.category.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import shop.com.shopdb.modules.product.model.Product;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer category_id;
    @Column(name = "category_name", nullable = false, unique = true)
    private String category_name;
    @Column(name = "status", columnDefinition = "BIT DEFAULT 1")
    private Boolean status = true;
    private String image;
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Product> products;
}
