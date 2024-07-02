package shop.com.shopdb.modules.user.mail;

import jakarta.transaction.Transactional;
import org.hibernate.internal.util.collections.ConcurrentReferenceHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class Mailservice {
    @Autowired
    private JavaMailSender emailSender;

    public void sendMail(SingleMail singleMail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("quangtrungdn1994@gmail.com");
        message.setTo(singleMail.getTo());
        message.setSubject(singleMail.getSubject());
        message.setText(singleMail.getText());
        emailSender.send(message);
    }

    public void senMail (MutilMail mutilMail){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("quangtrungdn1994@gmail.com");
        message.setTo(mutilMail.getTo());
        message.setSubject(mutilMail.getSubject());
        message.setText(mutilMail.getText());
        emailSender.send(message);
    }

}