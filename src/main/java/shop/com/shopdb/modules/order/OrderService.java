package shop.com.shopdb.modules.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.com.shopdb.modules.order.enums.Status;
import shop.com.shopdb.modules.orderDetail.OrderDetail;
import shop.com.shopdb.modules.orderDetail.OrderDetailRepository;
import shop.com.shopdb.modules.product.ProductRepository;
import shop.com.shopdb.modules.product.model.Product;
import shop.com.shopdb.modules.user.User;
import shop.com.shopdb.modules.user.service.IUserService;
import shop.com.shopdb.modules.user.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;
    public Orders findCart(Integer userId) {
        try {
            return orderRepository.findByLoginId(userId);
        } catch (Exception e) {
            return null;
        }
    }

    public void addToCart(int userId, int productId, int quantity, double price) {
        try {
            Orders cart = this.findCart(userId);
            Optional<User> user = userService.findById(userId);
            Optional<Product> productVariant = productRepository.findById(productId);

            if(cart == null) {
                // user chưa có giỏ hàng - tạo mới + thêm sản phẩm
                Orders newCart = new Orders();
                if (user.isPresent()) {
                    newCart.setUser(user.get());
                    newCart.setNote("Không ghi chú gì");
                    newCart.setCreateDate(new Date().toString());
                    newCart.setUpdateDate(new Date().toString());

                    cart = orderRepository.save(newCart);

                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrders(cart);
                    orderDetail.setPrice(price);
                    orderDetail.setQuantity(quantity);
                    if(productVariant.isPresent()) {
                        orderDetail.setProduct(productVariant.get());
                    }else {
                        // lỗi
                    }

                    orderDetailRepository.save(orderDetail);


                    System.out.println("cart  " + cart);
                }else {
                    // lỗi
                }
            }else {

                // đã tồn tại 1 giỏ hàng - kiểm tra sp có trong giỏ hàng chưa, nếu có tăng quantity
                OrderDetail orderDetailExisted = orderDetailRepository.findItem(cart.getId(), productId);

                if(orderDetailExisted != null) {
                    // san pham da ton tai trong gio hang
                    orderDetailExisted.setQuantity(orderDetailExisted.getQuantity() + quantity);
                    orderDetailRepository.save(orderDetailExisted)  ;
                }else {
                    // san pham chua co trong gio hang
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrders(cart);
                    orderDetail.setPrice(price);
                    orderDetail.setQuantity(quantity);
                    if(productVariant.isPresent()) {
                        orderDetail.setProduct(productVariant.get());
                    }else {
                        // lỗi
                    }
                    orderDetailRepository.save(orderDetail);
                }
            }
        }catch (Exception e) {

        }
    }

    public List<Orders> findByUserId(int userId) {
        return orderRepository.findByUserId(userId);
    }

    public void checkOut(int orderId, int totalAmount) {
       try {
           System.out.println("đã vào");
           Orders order = orderRepository.findById(orderId).get();
           order.setStatus(Status.WAITING);
           order.setTotalPrices(totalAmount);
           orderRepository.save(order);
         }catch (Exception e) {
           System.out.println("Error: " + e.getMessage());
       }
    }

}
