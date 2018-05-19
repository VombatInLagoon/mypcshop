package org.amin.pcshop.helper;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Address;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;


/**
 * This class acts as a helper to send email in the ContactUs section of the
 * Web site.
 * @author amin
 */
public class MailUtilGmail {

    public static void sendMail(final String to, final String from,
                                final String subject, final String body,
                                final boolean bodyIsHTML) throws MessagingException {
        // 1 - get a mail session
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtps");
        properties.put("mail.smtps.host", "smtp.gmail.com");
        properties.put("mail.smtps.port", 465);
        properties.put("mail.smtps.auth", "true");
        properties.put("mail.smtps.quitwait", "false");
        Session session = Session.getDefaultInstance(properties);
        session.setDebug(true);

        // 2 - create a message
        Message message = new MimeMessage(session);
        message.setSubject(subject);

        if (bodyIsHTML) {
            message.setContent(body, "text/html");
        } else {
            message.setText(body);
        }

        // 3 - address the message
        Address fromAddress = new InternetAddress(from);
        Address toAddress = new InternetAddress(to);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);

        // 4 - send the message
        Transport transport = session.getTransport();
        transport.connect("aminrock2000@gmail.com", "hvudnxqeonxsenas");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}