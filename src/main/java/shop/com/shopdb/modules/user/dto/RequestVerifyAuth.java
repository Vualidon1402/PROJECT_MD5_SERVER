package shop.com.shopdb.modules.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RequestVerifyAuth {
    String token;
    String test;
}
