package shop.com.shopdb.modules.order;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.com.shopdb.modules.order.enums.Status;
import shop.com.shopdb.modules.orderDetail.OrderDetail;
import shop.com.shopdb.modules.user.User;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private double totalPrices = 0;

    @Enumerated(EnumType.STRING)
    private Status status = Status.SHOPPING;

    private String note;

    private String createDate;

    private String updateDate;

    @OneToMany(mappedBy = "orders")
    @JsonManagedReference
    private List<OrderDetail> details = new ArrayList<>();


    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", user=" + user +
                ", totalPrices=" + totalPrices +
                ", status=" + status +
                ", note='" + note + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }
}
