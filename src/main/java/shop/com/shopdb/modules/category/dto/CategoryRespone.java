package shop.com.shopdb.modules.category.dto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryRespone {
    private Integer category_id;
    private String category_name;
    private Boolean status;
    private String image;
}
