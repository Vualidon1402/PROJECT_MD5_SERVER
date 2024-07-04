package shop.com.shopdb.modules.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import shop.com.shopdb.modules.user.User;

@AllArgsConstructor
@Data
public class FindRes {
    User data;
    String message;
}
