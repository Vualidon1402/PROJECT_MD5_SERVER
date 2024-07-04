package shop.com.shopdb.util.jwt.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserTokenDTO {
    private String email;
    private String id;
    private boolean role;
    private boolean status;
    private  String userName;
    private  boolean emailConfirm;
}
