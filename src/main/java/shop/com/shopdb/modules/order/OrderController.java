package shop.com.shopdb.modules.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.com.shopdb.modules.order.dto.AddToCartDTO;
import shop.com.shopdb.modules.order.dto.TotalAmout;
import shop.com.shopdb.modules.user.User;
import shop.com.shopdb.util.jwt.JwtBuilder;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/order/add-to-cart")
    public String addToCart(@RequestHeader("token") String token, @RequestBody AddToCartDTO body) {

        try {
            User user = JwtBuilder.verifyTokenUser(token);
            if (user == null) {
                return null;
            }
            orderService.addToCart(user.getId(), body.getProductId(), body.getQuantity(), body.getPrice());
            return "order/add-to-cart";
        } catch (Exception e) {
            return "error";
        }
    }

//@GetMapping("/order/remove/{orderId}")
//    public String removeOrder(@PathVariable Integer orderId) {
//        try {
//            orderService.removeOrder(orderId);
//            return "order/remove";
//        } catch (Exception e) {
//            return "error";
//        }
//    }
@PostMapping("/order/check-out/{orderId}")
    public String checkout(@PathVariable Integer orderId, @RequestBody TotalAmout totalamout) {
        try {
            orderService.checkOut(orderId , totalamout.getTotalamout());
            return "order/checkout";
        } catch (Exception e) {
            return "error";
        }
    }
    @GetMapping("/order/find-cart")
    public List<Orders> findCart(@RequestHeader("token") String token) {
        try {
            User user = JwtBuilder.verifyTokenUser(token);
            assert user != null;
            return orderService.findByUserId(user.getId());
        }catch (Exception e){
            return null;
        }
    }


}
