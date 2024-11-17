package project.personal.personalstoremanagementproject.v1.controllers.user.profilescreen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.personal.personalstoremanagementproject.exceptions.DetailError;
import project.personal.personalstoremanagementproject.exceptions.ErrorCode;
import project.personal.personalstoremanagementproject.utils.FunctionUtil;
import project.personal.personalstoremanagementproject.v1.AbstractApiController;
import project.personal.personalstoremanagementproject.v1.controllers.user.profilescreen.models.SelectUserProfileModel;
import project.personal.personalstoremanagementproject.v1.controllers.user.profilescreen.request.SelectUserProfileRequest;
import project.personal.personalstoremanagementproject.v1.controllers.user.profilescreen.responses.SelectUserProfileResponse;
import java.util.List;

/**
 * Controller for selecting user profile
 */
@RestController
@RequestMapping("/api/v1/user/select-profile")
public class SelectUserProfileController extends AbstractApiController<SelectUserProfileRequest, SelectUserProfileResponse, SelectUserProfileModel>{

    @Autowired
    private FunctionUtil functionUtil;

    /**
     * Main process of the controller
     * @param request the request to process
     * @return
     */
    @Override
    protected SelectUserProfileResponse exec(SelectUserProfileRequest request) throws Exception {
        var response = new SelectUserProfileResponse();

        var user = functionUtil.getCurrentUser();
        // Check if user is not authenticated
        if (user == null) {
            response.setSuccess(false);
            response.setMessage(ErrorCode.NOT_FOUND, "User not authenticated", null);
            return response;
        }
        // Create model
        var model = SelectUserProfileModel.builder()
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .img(user.getImg())
                .email(user.getEmail())
                .nickName(user.getNickName())
                .build();

        // True
        response.setSuccess(true);
        response.setMessage(ErrorCode.SUCCESS, "Select user information successful", detailErrorList);
        response.setResponse(model);
        return response;
    }

    /**
     * Error check
     * @param request
     * @param detailErrorList
     * @return
     */
    @Override
    protected SelectUserProfileResponse errorCheck(SelectUserProfileRequest request, List<DetailError> detailErrorList) {
        if (!detailErrorList.isEmpty()) {
            SelectUserProfileResponse response = new SelectUserProfileResponse();
            response.setSuccess(false);
            response.setMessage(ErrorCode.VALIDATION_ERROR, "Validation errors occurred", detailErrorList);
            return response;
        }
        return null;
    }
}
