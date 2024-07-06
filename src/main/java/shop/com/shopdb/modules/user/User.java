package shop.com.shopdb.modules.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import shop.com.shopdb.modules.address.Address;

import java.util.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;
}