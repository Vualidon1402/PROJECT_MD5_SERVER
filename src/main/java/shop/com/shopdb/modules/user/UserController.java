package shop.com.shopdb.modules.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.com.shopdb.modules.user.dto.RequestLogin;
import shop.com.shopdb.modules.user.dto.RequestRegister;
import org.mindrot.jbcrypt.BCrypt;
import shop.com.shopdb.modules.user.dto.ResponeRegister;
import shop.com.shopdb.modules.user.mail.Mailservice;
import shop.com.shopdb.modules.user.mail.SingleMail;
import shop.com.shopdb.modules.user.service.UserService;

@RestController
public class UserController {
    @Autowired
    private Mailservice mailservice;
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<ResponeRegister> register(@Valid @RequestBody RequestRegister requestRegister) {
        requestRegister.setPassword(BCrypt.hashpw(requestRegister.getPassword(), BCrypt.gensalt()));
        // save user to database
               ResponeRegister response = userService.save(requestRegister);
        // send email
       if(response.getData() != null) {
            mailservice.sendMail(new SingleMail(response.getData().getEmail(), "ok", "ok khong thien"));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody RequestLogin requestLogin) {
        // check user in database
        User user = userService.findByEmail(requestLogin.getEmail());
        if (user != null) {
            // check password
            if (BCrypt.checkpw(requestLogin.getPassword(), user.getPassword())) {
                // return token
                return new ResponseEntity<>("token", HttpStatus.OK);
            }
        }
        // if user exist, return token
        // else return login
        return new ResponseEntity<>("login", HttpStatus.OK);
    }
}
