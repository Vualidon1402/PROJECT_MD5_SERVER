package shop.com.shopdb.modules.user.mail;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SingleMail {
    private String to;
    private String subject;
    private String text;
}
