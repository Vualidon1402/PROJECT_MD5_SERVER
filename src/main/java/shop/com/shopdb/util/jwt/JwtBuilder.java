package shop.com.shopdb.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import shop.com.shopdb.util.jwt.dto.EmailConfirmDTO;
import shop.com.shopdb.modules.user.User;

import java.lang.reflect.Field;

public class JwtBuilder {
    private static String secretKey = "QuangTrungDepTrai";

    public static String createTokenForConfirmEmail(EmailConfirmDTO data) throws IllegalAccessException {
        JWTCreator.Builder builder = JWT.create().withIssuer("auth0");

        Field[] fields = EmailConfirmDTO.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(data);
            if (value != null) {
                builder.withClaim(field.getName(), value.toString());
            }
        }

        return builder.sign(Algorithm.HMAC256(secretKey));
    }

    public static EmailConfirmDTO verifyTokenForEmailConfirm(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            DecodedJWT jwt = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build()
                    .verify(token);

            String email = jwt.getClaim("email").asString();
            Integer id = Integer.parseInt(jwt.getClaim("id").asString());
            EmailConfirmDTO emailConfirm = new EmailConfirmDTO(email, id);

            return emailConfirm;
        } catch (JWTVerificationException exception){
            return null;
        }
    }

    public static String createTokenUser(User data) throws IllegalAccessException {
        JWTCreator.Builder builder = JWT.create().withIssuer("auth0");
        Field[] fields = User.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(data);
            if (value != null) {
                builder.withClaim(field.getName(), value.toString());
            }
        }

        return builder.sign(Algorithm.HMAC256(secretKey));
    }

    public static User verifyTokenUser(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            DecodedJWT jwt = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build()
                    .verify(token);

            String email = jwt.getClaim("email").asString();
            Integer id = Integer.parseInt(jwt.getClaim("id").asString());
            Boolean role = Boolean.parseBoolean(jwt.getClaim("role").asString());
            Boolean status = Boolean.parseBoolean(jwt.getClaim("status").asString());
            String userName = jwt.getClaim("userName").asString();
            String password = jwt.getClaim("password").asString();
            Boolean emailConfirm = Boolean.parseBoolean(jwt.getClaim("emailConfirm").asString());
            String phone = jwt.getClaim("phone").asString();
            String address = jwt.getClaim("address").asString();
            String updateDate = jwt.getClaim("updateDate").asString();


            return new User(id, userName, email, emailConfirm, password,updateDate, role, status, phone, address);
        } catch (JWTVerificationException exception){
            return null;
        }
    }
}
