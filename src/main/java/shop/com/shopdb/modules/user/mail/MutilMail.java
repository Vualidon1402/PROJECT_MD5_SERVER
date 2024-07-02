package shop.com.shopdb.modules.user.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class MutilMail {
    public String[] to;
    public String subject;
    public String text;
}
