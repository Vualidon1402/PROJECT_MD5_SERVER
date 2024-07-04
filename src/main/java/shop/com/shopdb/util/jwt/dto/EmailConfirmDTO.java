package shop.com.shopdb.util.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EmailConfirmDTO {
    public String email;
    public Integer id;

    @Override
    public String toString() {
        return "EmailConfirmDTO{" +
                "email='" + email + '\'' +
                ", id=" + id +
                '}';
    }
}
