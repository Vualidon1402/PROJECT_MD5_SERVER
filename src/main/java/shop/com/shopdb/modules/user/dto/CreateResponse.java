package shop.com.shopdb.modules.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.com.shopdb.modules.user.User;
import shop.com.shopdb.ErrMessage.ErrText;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateResponse {
    private String message;
    private User data;
    private ErrText err;
}
