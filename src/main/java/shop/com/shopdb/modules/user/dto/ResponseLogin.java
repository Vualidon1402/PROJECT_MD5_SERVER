package shop.com.shopdb.modules.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseLogin {
    String message;
    String token;
}
