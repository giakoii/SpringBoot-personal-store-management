package org.example.personalstoremanagementproject.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.example.personalstoremanagementproject.dtos.UserDTO;
import org.example.personalstoremanagementproject.entities.Status;
import org.example.personalstoremanagementproject.entities.User;
import org.example.personalstoremanagementproject.repositories.UserRepository;
import org.example.personalstoremanagementproject.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements IUserService {

    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserDTO userDTO) {
        if(userRepository.findByEmail(userDTO.getEmail()) != null || userRepository.findByUserName(userDTO.getUserName()) != null){
            throw new RuntimeException("Email" + userDTO.getEmail() + "is already in use");
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        Util util = new Util();
        String userId = util.createId(new User(), userRepository);
        try {
            User user = User.builder()
                    .userId(userId)
                    .userName(userDTO.getUserName())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .email(userDTO.getEmail())
                    .fullName(userDTO.getFullName())
                    .nickName(userDTO.getNickName())
                    .phone(userDTO.getPhone())
                    .address(userDTO.getAddress())
                    .role(userDTO.getRole())
                    .build();
            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public User userLogin(String userName, String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        User user = userRepository.findByUserName(userName).orElseThrow(() -> new RuntimeException("User not found"));
        if (user.getStatus().equals(Status.ENABLED)){
            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new RuntimeException("Invalid username or password");
            }
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user;
    }

    @Override
    public User getUserByUserName(String userName) {
        User user = userRepository.findByUserName(userName).orElseThrow(() -> new RuntimeException("User not found"));
        return user;
    }


    @Override
    public User updateInformationUser(String userId, UserDTO userDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        if(userDTO.getFullName() != null){
            user.setFullName(userDTO.getFullName());
        }
        if(userDTO.getNickName() != null){
            user.setNickName(userDTO.getNickName());
        }
        if(userDTO.getPhone() != null){
            user.setPhone(userDTO.getPhone());
        }
        if(userDTO.getAddress() != null){
            user.setAddress(userDTO.getAddress());
        }
        return userRepository.save(user);
    }

    @Override
    public User deleteUser(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent() && user.get().getStatus().equals(Status.ENABLED)) {
            user.get().setStatus(Status.DISABLED);
            return userRepository.save(user.get());
        }
        return null;
    }

    //hàm OTP Email
    public void sendOtpEmail(String email) {
        String opt = generateOtp();
        try {
            sendOtpEmail(email, opt);
        } catch (MessagingException e) {
            throw new RuntimeException("Error sending email");
        }

    }

    private String generateOtp() {
        SecureRandom random = new SecureRandom();
        int otp = 100001 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    private void sendOtpEmail(String email, String otp) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(email);
        helper.setSubject("OTP");

        String text = "Dear User,\n\n"
                + "Your OTP is: " + otp + "\n\n"
                + "This OTP is valid for 5 minutes. Do not share this OTP with anyone for security reasons.\n\n"
                + "Best regards,\n"
                + "Valuation Diamond";
        helper.setText(text);

        javaMailSender.send(message);
    }
}
