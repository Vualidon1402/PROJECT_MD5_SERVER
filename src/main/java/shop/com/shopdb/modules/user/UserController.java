package shop.com.shopdb.modules.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.com.shopdb.modules.user.dto.*;
import org.mindrot.jbcrypt.BCrypt;
import shop.com.shopdb.modules.user.mail.MailService;
import shop.com.shopdb.modules.user.mail.SingleOption;
import shop.com.shopdb.modules.user.service.UserService;
import shop.com.shopdb.util.jwt.JwtBuilder;
import shop.com.shopdb.util.jwt.dto.EmailConfirmDTO;

@Controller
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private MailService mailService;
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<ResponeRegister> registerUser(@RequestHeader("lng") String lng, @Valid @RequestBody RequestRegister requestRegister) {
        try {
            // Hash password
            requestRegister.setPassword(BCrypt.hashpw(requestRegister.getPassword(), BCrypt.gensalt()));

            // Create user
            CreateResponse result = userService.create(requestRegister);

            if (result.getErr() == null) {
                // Send confirmation email
                String emailContent = String.format("<!DOCTYPE html>\n" +
                                "<html lang=\"en\">\n" +
                                "<head>\n" +
                                "    <meta charset=\"UTF-8\">\n" +
                                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                                "    <title>Xác Thực Email</title>\n" +
                                "</head>\n" +
                                "<body>\n" +
                                "    <h1>Rất hân hạnh được phục vụ quý khách</h1>\n" +
                                "    <h2>Bấm vào link bên dưới để xác thực tài khoản Pet Shop của bạn</h2>\n" +
                                "    <a href=\"%s\">\n" +
                                "        <button>\n" +
                                "            Xác Thực Ngay\n" +
                                "        </button>\n" +
                                "    </a>\n" +
                                "</body>\n" +
                                "</html>",
                        "http://localhost:8080/user/confirm-email?token=" + JwtBuilder.createTokenForConfirmEmail(new EmailConfirmDTO(result.getData().getEmail(), result.getData().getId())));
                mailService.sendMailHtml(new SingleOption("Vui lòng xác thực Email để tiếp tục sử dụng dịch vụ của chúng tôi", emailContent, result.getData().getEmail()));
                return new ResponseEntity<>(new ResponeRegister(result.getMessage(), null), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ResponeRegister(result.getErr().getText(lng), null), HttpStatus.BAD_REQUEST);
            }
        } catch (DataIntegrityViolationException e) {
            // Handle duplicate key error
            return new ResponseEntity<>(new ResponeRegister("Email đã tồn tại trong hệ thống", null), HttpStatus.CONFLICT);
        } catch (Exception e) {
            // Log the exception (use a logging framework in real applications)
            e.printStackTrace();
            return new ResponseEntity<>(new ResponeRegister("Email/UserName đã tồn tại trong hệ thống", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseLogin> loginUser(@RequestBody RequestLogin body) throws IllegalAccessException {
        User user = userService.findByLoginId(body.getLoginId());

        if(user == null) {
            return new ResponseEntity<ResponseLogin>(new ResponseLogin("Tài khoản không tồn tại", null), HttpStatus.BAD_REQUEST);
        }else {
            if(!BCrypt.checkpw(body.getPassword(), user.getPassword())) {
                return new ResponseEntity<ResponseLogin>(new ResponseLogin("Mật khẩu sai", null), HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity<ResponseLogin>(new ResponseLogin("Đăng nhập thành công", JwtBuilder.createTokenUser(user)), HttpStatus.OK);
            }
        }
    }

    @PostMapping("/user/verify")
    public ResponseEntity<VerifyAuth> verifyUser(@RequestBody ResponseLogin body) {
        User user = JwtBuilder.verifyTokenUser(body.getToken());
        System.out.println("user " + user);
        if(user == null) {
            return new ResponseEntity<VerifyAuth>(new VerifyAuth("Token không hợp lệ", null), HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<VerifyAuth>(new VerifyAuth("Token hợp lệ", user), HttpStatus.OK);
        }
    }

    @GetMapping("/user/confirm-email")
    public String confirmEmail(@RequestParam("token") String token) throws IllegalAccessException {
        EmailConfirmDTO data = JwtBuilder.verifyTokenForEmailConfirm(token);
        User oldData = userService.findById(data.getId());
        oldData.setEmailConfirm(true);
        User newData = userService.update(oldData);
        if(newData != null) {
            return "email_verified.html";
        }else {
            return "email_fail.html";
        }
    }
}
