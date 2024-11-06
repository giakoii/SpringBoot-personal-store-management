package project.personal.personalstoremanagementproject.v1.controllers.ScreenRegister;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.personal.personalstoremanagementproject.utils.constants.ConstantEnum;
import project.personal.personalstoremanagementproject.entities.User;
import project.personal.personalstoremanagementproject.exceptions.ErrorCode;
import project.personal.personalstoremanagementproject.repositories.UserRepository;
import project.personal.personalstoremanagementproject.utils.StringUtil;
import project.personal.personalstoremanagementproject.v1.AbstractApiController;
import project.personal.personalstoremanagementproject.v1.DetailError;

import java.util.List;

@RestController
@RequestMapping("api/v1/register")
public class ScreenRegisterController extends AbstractApiController<ScreenRegisterRequest, ScreenRegisterResponse, String> {

    private final UserRepository userRepository;

    public ScreenRegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Main processing
     * @param request the request to process
     * @return
     */
    @Override
    protected ScreenRegisterResponse exec(ScreenRegisterRequest request) {
        // Find user by username and email
        var user = userRepository.existsByEmailAndUserName(request.getEmail(), request.getUserName());
        if (user) {
            throw new RuntimeException("User already exists");
        }
        // Generate password encoder
        var passwordEncoder = new BCryptPasswordEncoder(10);
        var stringUtil = new StringUtil();
        // Create new user
        var newUser = User.builder()
                .userId(stringUtil.createId(new User(), userRepository))
                .userName(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .fullName(request.getFullName())
                .nickName(request.getNickName())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .role(ConstantEnum.Role.CUSTOMER)
                .lastLogin(null)
                .build();
        newUser.setIsActive(true);
        userRepository.save(newUser);

        // True
        ScreenRegisterResponse registerResponse = new ScreenRegisterResponse();
        registerResponse.setSuccess(true);
        registerResponse.setMessage(ErrorCode.SUCCESS,"Create user successful");
        return registerResponse;
    }

    /**
     * Error check
     * @param request the request to check
     * @param detailErrorList list of detected errors
     * @return
     */
    @Override
    protected ScreenRegisterResponse errorCheck(ScreenRegisterRequest request, List<DetailError> detailErrorList) {
        return null;
    }
}
