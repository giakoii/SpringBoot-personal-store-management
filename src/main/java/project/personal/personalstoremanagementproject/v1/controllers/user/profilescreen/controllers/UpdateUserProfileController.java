package project.personal.personalstoremanagementproject.v1.controllers.user.profilescreen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.personal.personalstoremanagementproject.exceptions.DetailError;
import project.personal.personalstoremanagementproject.exceptions.ErrorCode;
import project.personal.personalstoremanagementproject.repositories.UserRepository;
import project.personal.personalstoremanagementproject.utils.FunctionUtil;
import project.personal.personalstoremanagementproject.v1.AbstractApiController;
import project.personal.personalstoremanagementproject.v1.controllers.user.profilescreen.request.UpdateUserProfileRequest;
import project.personal.personalstoremanagementproject.v1.controllers.user.profilescreen.responses.UpdateUserProfileResponse;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller for updating user profile
 */
@RestController
@RequestMapping("/api/v1/user/update-profile")
public class UpdateUserProfileController extends AbstractApiController<UpdateUserProfileRequest, UpdateUserProfileResponse, String> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FunctionUtil functionUtil;

    /**
     * Main process of the controller
     * @param request the request to process
     * @return
     */
    @Override
    protected UpdateUserProfileResponse exec(UpdateUserProfileRequest request) {
        var response = new UpdateUserProfileResponse();

        var user = functionUtil.getCurrentUser();
        if (user == null) {
            response.setSuccess(false);
            response.setMessage(ErrorCode.NOT_FOUND, "User not authenticated", null);
            return response;
        }
        // Update user information
        if (request.getFullName() != null){
            user.setFullName(request.getFullName());
        }
        if (request.getPhoneNumber() != null){
            user.setPhoneNumber(request.getPhoneNumber());
        }
        if (request.getAddress() != null){
            user.setAddress(request.getAddress());
        }
        if (request.getImg() != null){
            user.setImg(request.getImg());
        }
        user.setUpdatedAt(LocalDateTime.now());
        user.setUpdatedBy(user.getUserId());
        userRepository.save(user);

        // True
        response.setSuccess(true);
        response.setMessage(ErrorCode.SUCCESS ,"Update user information successful", detailErrorList);
        return response;
    }

    /**
     * Check for errors
     * @param request the request to check
     * @param detailErrorList the list of errors
     * @return
     */
    @Override
    protected UpdateUserProfileResponse errorCheck(UpdateUserProfileRequest request, List<DetailError> detailErrorList) {
        if (!detailErrorList.isEmpty()) {
            UpdateUserProfileResponse response = new UpdateUserProfileResponse();
            response.setSuccess(false);
            response.setMessage(ErrorCode.VALIDATION_ERROR, "Validation errors occurred", detailErrorList);
            return response;
        }
        return null;
    }
}
