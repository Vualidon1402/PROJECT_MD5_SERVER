package shop.com.shopdb.modules.user.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
public class Option {
    String subject;
    String text;
    ArrayList<String> to;

    public  Option (String subject, String text, ArrayList<String> to) {
        this.subject = subject;
        this.text = text;
        this.to = to;
    }
}
