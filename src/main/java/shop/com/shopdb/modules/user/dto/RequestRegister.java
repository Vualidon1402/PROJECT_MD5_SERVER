package shop.com.shopdb.modules.user.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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

//    @NotNull(message = "Email cannot be null")
//    @Email(message = "Invalid email format")
    private String password;
}
