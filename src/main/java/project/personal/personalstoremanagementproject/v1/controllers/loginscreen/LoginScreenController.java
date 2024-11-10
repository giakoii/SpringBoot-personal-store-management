package project.personal.personalstoremanagementproject.v1.controllers.loginscreen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.personal.personalstoremanagementproject.exceptions.ErrorCode;
import project.personal.personalstoremanagementproject.repositories.UserRepository;
import project.personal.personalstoremanagementproject.services.JwtService;
import project.personal.personalstoremanagementproject.v1.AbstractApiController;
import project.personal.personalstoremanagementproject.exceptions.DetailError;

import java.util.List;

@RequestMapping("/api/v1/login")
@RestController
public class LoginScreenController extends AbstractApiController<LoginScreenRequest, LoginScreenResponse, LoginScreenModel> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * Main processing
     *
     * @param request the request to process
     * @return
     */
    @Override
    protected LoginScreenResponse exec(LoginScreenRequest request) {
        try {
            // Verify login information
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid username or password");
        }

        // Find user by username in database
        var user = userRepository.findByUserName(request.getUserName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Generate token and refresh token
        var token = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        // Create LoginScreenEntity
        var loginEntity = LoginScreenModel.builder()
                .token(token)
                .refreshToken(refreshToken)
                .expirationTime("24Hrs")
                .build();

        // True
        LoginScreenResponse loginResponse = new LoginScreenResponse();
        loginResponse.setSuccess(true);
        loginResponse.setMessage(ErrorCode.SUCCESS, "Login successful", detailErrorList);
        loginResponse.setResponse(loginEntity);
        return loginResponse;
    }

    /**
     * Error check
     * @param request         the request to check
     * @param detailErrorList list of detected errors
     * @return
     */
    @Override
    protected LoginScreenResponse errorCheck(LoginScreenRequest request, List<DetailError> detailErrorList) {
        if (!detailErrorList.isEmpty()) {
            LoginScreenResponse response = new LoginScreenResponse();
            response.setSuccess(false);
            response.setMessage(ErrorCode.VALIDATION_ERROR, "Validation errors occurred", detailErrorList);
            return response;
        }
        return null;
    }
}
