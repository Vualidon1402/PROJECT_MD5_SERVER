package shop.com.shopdb.modules.category.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryRequest {
    private String category_name;
    private String image;
}
