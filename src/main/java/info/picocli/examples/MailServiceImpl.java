package info.picocli.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("MailService")
public class MailServiceImpl implements IMailService {

    private static final Logger LOGGER= LoggerFactory.getLogger(MailServiceImpl.class);
    private static final String NOREPLY_ADDRESS = "noreply@picocli.info";

    @Autowired private JavaMailSender emailSender; 

    @Override
    public void sendMessage(List<String> to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage(); // create message
            message.setFrom(NOREPLY_ADDRESS);                    // compose message
            for (String recipient : to) { message.setTo(recipient); }
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);                           // send message

            LOGGER.info("Mail to {} sent! Subject: {}, Body: {}", to, subject, text); 
        }
        catch (MailException e) { e.printStackTrace(); }
    }
}
