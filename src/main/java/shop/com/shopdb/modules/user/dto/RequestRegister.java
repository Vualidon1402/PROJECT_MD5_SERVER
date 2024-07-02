package shop.com.shopdb.modules.user.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestRegister {
    private String userName;
    private String email;
    private String password;
}
