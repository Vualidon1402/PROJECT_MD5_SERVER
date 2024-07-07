package shop.com.shopdb.modules.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AddToCartDTO {
    private int ProductId;
    private int quantity;
    private Integer price;

    @Override
    public String toString() {
        return "AddToCartDTO{" +
                "productVariantId=" + ProductId +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
