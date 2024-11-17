package project.personal.personalstoremanagementproject.confiugation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * MailConfig - Configuration for Mail
 */
public class MailConfig {

    /**
     * Mail Host
     */
    @Value("${spring.mail.host}")
    String mailHost;

    /**
     * Mail Port
     */
    @Value("${spring.mail.port}")
    String mailPort;

    /**
     * Mail Username
     */
    @Value("${mail.username}")
    String userName;

    /**
     * Mail Password
     */
    @Value("${spring.mail.password}")
    String password;

    /**
     * Configures the JavaMailSender object for sending mails.
     *
     * @return an instance of {@link JavaMailSender} object.
     */
    @Bean
    public JavaMailSender getJavaMailSender(){
        // Create a JavaMailSenderImpl object
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // Set the mail host, port, username, and password
        mailSender.setHost(mailHost);
        mailSender.setPort(Integer.parseInt(mailPort));
        mailSender.setUsername(userName);
        mailSender.setPassword(password);

        // Set the JavaMail properties
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.starttls.enable", "true");
        return mailSender;
    }

}
