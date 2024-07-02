package shop.com.shopdb.modules.user.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.com.shopdb.modules.user.User;
import shop.com.shopdb.modules.user.dto.RequestRegister;
import shop.com.shopdb.modules.user.dto.ResponeRegister;
import shop.com.shopdb.modules.user.repository.UserRepository;

@Service
@Transactional
public class UserService {
     @Autowired
        private UserRepository userRepository;

        public User findByEmail(String email) {
            return userRepository.findByEmail(email);
        }

        public User findByUserName(String userName) {
            return userRepository.findByUserName(userName);
        }

        public ResponeRegister save(RequestRegister requestRegister) {
            User user = new User();
            user.setUserName(requestRegister.getUserName());
            user.setEmail(requestRegister.getEmail());
            user.setPassword(requestRegister.getPassword());
            User savedUser = userRepository.save(user);
            ResponeRegister response = new ResponeRegister();
            response.setData(savedUser);
            response.setMessage("User created successfully");
            return response;

        }
}
