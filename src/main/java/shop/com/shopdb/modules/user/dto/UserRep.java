package shop.com.shopdb.modules.user.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRep {
    private Integer id;
    private String userName;
    private String email;
    private boolean emailConfirm;
    private boolean role = false;
    private boolean status = false;
    private String phone;
    private String address;
}
