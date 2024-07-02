package shop.com.shopdb.modules.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.com.shopdb.modules.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByUserName(String userName);
}
