package project.personal.personalstoremanagementproject.v1.controllers.LoginScreen;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.personal.personalstoremanagementproject.exceptions.AppException;
import project.personal.personalstoremanagementproject.exceptions.ErrorCode;
import project.personal.personalstoremanagementproject.repositories.UserRepository;
import project.personal.personalstoremanagementproject.v1.AbstractApiController;
import project.personal.personalstoremanagementproject.v1.DetailError;

import java.util.List;

@RestController
@RequestMapping("/api/v1/login")
public class LoginScreenController extends AbstractApiController<LoginScreenRequest, LoginScreenResponse, LoginScreenEntity> {

    @Autowired
    private UserRepository userRepository;

    /**
     * Main processing
     * @param request the request to process
     * @return
     */
    @Override
    protected LoginScreenResponse exec(LoginScreenRequest request) {
        // Generate password encoder
        var passwordEncoder = new BCryptPasswordEncoder(10);

        // Find user by username
        var user = userRepository.findByUserName(request.getUserName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        // Check if user is active
        if (!user.getIsActive()) {
            throw new RuntimeException("User not found");
        }

        // Check if password is correct
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        // Create LoginEntity object with user information
        var loginEntity = LoginScreenEntity.builder()
                .fullName(user.getFullName())
                .nickName(user.getNickName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .address(user.getAddress())
                .build();

        // True
        LoginScreenResponse loginResponse = new LoginScreenResponse();
        loginResponse.setSuccess(true);
        loginResponse.setMessage(ErrorCode.SUCCESS,"Login successful");
        return loginResponse;
    }

    /**
     * Error check
     * @param request the request to check
     * @param detailErrorList list of detected errors
     * @return
     */
    @Override
    protected LoginScreenResponse errorCheck(LoginScreenRequest request, List<DetailError> detailErrorList) {
        return null;
    }
}
