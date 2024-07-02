package shop.com.shopdb.modules.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.com.shopdb.modules.user.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponeRegister {
    public String message;
    public User data;
}
