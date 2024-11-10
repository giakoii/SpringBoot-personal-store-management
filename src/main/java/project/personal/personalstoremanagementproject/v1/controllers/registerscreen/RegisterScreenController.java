package project.personal.personalstoremanagementproject.v1.controllers.registerscreen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.personal.personalstoremanagementproject.utils.constants.ConstantEnum;
import project.personal.personalstoremanagementproject.entities.User;
import project.personal.personalstoremanagementproject.exceptions.ErrorCode;
import project.personal.personalstoremanagementproject.repositories.UserRepository;
import project.personal.personalstoremanagementproject.utils.StringUtil;
import project.personal.personalstoremanagementproject.v1.AbstractApiController;
import project.personal.personalstoremanagementproject.exceptions.DetailError;
import project.personal.personalstoremanagementproject.v1.controllers.loginscreen.LoginScreenResponse;

import java.util.List;

@RequestMapping("api/v1/register")
@RestController
public class RegisterScreenController extends AbstractApiController<RegisterScreenRequest, RegisterScreenResponse, String> {

    @Autowired
    private UserRepository userRepository;

    /**
     * Main processing
     * @param request the request to process
     * @return
     */
    @Override
    protected RegisterScreenResponse exec(RegisterScreenRequest request) {
        // Find user by username and email
        var user = userRepository.existsByEmailAndUserName(request.getEmail(), request.getUserName());
        if (user) {
            throw new RuntimeException("User already exists");
        }
        // Generate password encoder
        var passwordEncoder = new BCryptPasswordEncoder(10);
        // Create new user
        var newUser = User.builder()
                .userId(StringUtil.createId(new User(), userRepository))
                .userName(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(ConstantEnum.Role.CUSTOMER)
                .lastLogin(null)
                .build();
        newUser.setIsActive(true);
        userRepository.save(newUser);

        // True
        RegisterScreenResponse registerResponse = new RegisterScreenResponse();
        registerResponse.setSuccess(true);
        registerResponse.setMessage(ErrorCode.SUCCESS,"Create user successful", detailErrorList);
        return registerResponse;
    }

    /**
     * Error check
     * @param request the request to check
     * @param detailErrorList list of detected errors
     * @return
     */
    @Override
    protected RegisterScreenResponse errorCheck(RegisterScreenRequest request, List<DetailError> detailErrorList) {
        if (!detailErrorList.isEmpty()) {
            RegisterScreenResponse response = new RegisterScreenResponse();
            response.setSuccess(false);
            response.setMessage(ErrorCode.VALIDATION_ERROR, "Validation errors occurred", detailErrorList);
            return response;
        }
        return null;
    }
}
