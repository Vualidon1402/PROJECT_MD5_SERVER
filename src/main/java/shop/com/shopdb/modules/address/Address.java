package shop.com.shopdb.modules.address;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.com.shopdb.modules.user.User;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String fullAddress;

    private String receivePhone;

    private  String receiveName;
}