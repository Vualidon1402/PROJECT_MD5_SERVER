package shop.com.shopdb.modules.category.dto;

import lombok.*;
import shop.com.shopdb.modules.category.model.Category;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddCategoryRes {
    Category data;
    String message;
}
