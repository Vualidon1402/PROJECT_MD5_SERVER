package shop.com.shopdb.modules.user.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
public class SingleOption {
    String subject;
    String text;
    String to;

    public  SingleOption (String subject, String text, String to) {
        this.subject = subject;
        this.text = text;
        this.to = to;
    }
}
