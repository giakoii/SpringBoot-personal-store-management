package project.personal.personalstoremanagementproject.common;

import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import project.personal.personalstoremanagementproject.entities.User;
import project.personal.personalstoremanagementproject.repositories.EmailTemplateRepository;
import project.personal.personalstoremanagementproject.repositories.UserRepository;
import project.personal.personalstoremanagementproject.utils.StringUtil;

@Service
public class CommonLogic {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailTemplateRepository emailTemplateRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private StringUtil stringUtil;

    @Value("${frontend.url[0]}")
    private String url;

    @Value("${spring.mail.username}")
    private String mailFrom;

    /**
     * Send mail
     * @param screenName
     * @param user
     * @param <T>
     * @throws Exception
     */
    public <T> void sendMail(String screenName, User user) throws Exception {
        var userName = user.getUsername();
        // Find email template by screen name

        var emailTemplate = emailTemplateRepository.findByScreenName(screenName);
        var verificationLink = url + "/verify?token=" + stringUtil.encrypt(user.getUserId(), userName);

        // If email template is present
        if (emailTemplate.isPresent()) {
            // Replace placeholders in email template
            String bodyTemplate = emailTemplate.get().getMailBody();
            bodyTemplate = bodyTemplate.replace("${userName}", userName);
            bodyTemplate = bodyTemplate.replace("${verificationLink}", verificationLink);

            // Send mail
            var message = javaMailSender.createMimeMessage();
            var helper = new MimeMessageHelper(message);
            helper.setTo(user.getEmail());
            helper.setFrom(mailFrom);
            helper.setSubject(emailTemplate.get().getMailTitle());
            helper.setText(bodyTemplate);

            javaMailSender.send(message);
        }
    }

}

