package shop.com.shopdb.modules.user.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    public JavaMailSender emailSender;


    public void sendMail(Option option) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mieuandpokemon@gmail.com");

        String[] toArray = new String[option.getTo().size()];
        option.getTo().toArray(toArray);
        message.setTo(toArray);

        message.setSubject(option.getSubject());
        message.setText(option.getText());
        emailSender.send(message);
    }


    public void sendMail(SingleOption option) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mieuandpokemon@gmail.com");
        message.setTo(option.getTo());
        message.setSubject(option.getSubject());
        message.setText(option.getText());
        emailSender.send(message);
    }

    public void sendMailHtml(SingleOption option) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom("mieuandpokemon@gmail.com");
            helper.setTo(option.getTo());
            helper.setSubject(option.getSubject());
            helper.setText(option.getText(), true); // true indicates that the text is HTML

            emailSender.send(message);
        } catch (MessagingException e) {

        }
    }
}
