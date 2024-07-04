package shop.com.shopdb.modules.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String userName;

    @Column(unique = true)
    private String email;

    private boolean emailConfirm = false;



    private String password;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String updateDate = new Date().toString();
    private boolean role = false;
    private boolean status = false;

    private String phone;
    private String address = null;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }

}
