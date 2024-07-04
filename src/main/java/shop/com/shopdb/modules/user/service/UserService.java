package shop.com.shopdb.modules.user.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.com.shopdb.ErrMessage.ErrLib;
import shop.com.shopdb.modules.user.User;
import shop.com.shopdb.modules.user.dto.CreateResponse;
import shop.com.shopdb.modules.user.dto.RequestRegister;

import java.util.Optional;

@Service
@Transactional
public class UserService {
     @Autowired
     IUserService iUserService;


    public CreateResponse create(RequestRegister data) {
        try {
            User user = new User();

            BeanUtils.copyProperties(data, user);
            User savedUser = iUserService.save(user);

            return new CreateResponse("User created successfully", savedUser, null);
        } catch (Exception e) {

            return new CreateResponse(null, null, ErrLib.userNameExisted);
        }
    }

    public User findByLoginId(String loginId) {
        try {
            User user = iUserService.findByLoginId(loginId);
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    public User update(User data) {
        try {
            User user = iUserService.save(data);

            return user;
        } catch (Exception e) {
            return null;
        }
    }

    public User findById(int userId) {
        try {
            Optional<User> userF = iUserService.findById(userId);
            if (userF.isPresent()) {
                return userF.get();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
