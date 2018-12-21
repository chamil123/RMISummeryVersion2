/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pegasus.View;

/**
 *
 * @author Chamil123
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {

    public void send2() {

        final String username = "vikumturf@gmail.com"; //ur email
        final String password = "vikum@2017";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.auth", true);

//        props.put("mail.smtp.starttls.enable", true);
//         props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.setProperty("mail.debug", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            DateFormat dfd = new SimpleDateFormat("yyyy-MM-dd");
            Date dateobj = new Date();
            String pdfName = dfd.format(dateobj);

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("vikumturf@gmail.com"));//ur email
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("chamiljay88@gmail.com"));//u will send to
            message.setSubject("Subject");
            message.setText("PFA");
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();

            //attached 1 --------------------------------------------
            String file = "C:\\pegasusReports/" + pdfName + "kochchikade.pdf";
            String fileName = "AnyName1.pdf";
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(file);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);
    //------------------------------------------------------    

            //>>>>>>>>
            message.setContent(multipart);

            System.out.println("sending");
            Transport.send(message);
            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void send3() {

        final String username = "vikumturf@gmail.com"; //ur email
        final String password = "vikum@2017";

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            DateFormat dfd = new SimpleDateFormat("dd-MM-yyyy");
            Date dateobj = new Date();
            String pdfName = dfd.format(dateobj);

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("chamiljay88@gmail.com"));//ur email
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("madushangunawardhana@gmail.com"));//u will send to
            message.setSubject(pdfName + " jsp with email");
            message.setText("JSP");
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();

            //attached 1 --------------------------------------------
            String file = "C:\\pegasusReports/" + pdfName + "corrections.pdf";
            String fileName = pdfName + "corrections.pdf";
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(file);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);

            //>>>>>>>>
            message.setContent(multipart);

//            System.out.println("sending");
            Transport.send(message);
//            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
