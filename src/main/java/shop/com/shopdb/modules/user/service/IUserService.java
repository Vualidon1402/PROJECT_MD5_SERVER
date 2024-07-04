package shop.com.shopdb.modules.user.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.com.shopdb.modules.user.User;

public interface IUserService extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);


    default User registerUser(User user) {
        return save(user);
    }

    @Query(value = "SELECT * FROM user WHERE user.userName = :loginId OR (user.email = :loginId AND user.status = false)", nativeQuery = true)
    User findByLoginId(@Param("loginId") String loginId);
}
