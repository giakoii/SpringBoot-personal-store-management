package project.personal.personalstoremanagementproject.v1.controllers.loginscreen;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import project.personal.personalstoremanagementproject.v1.AbstractApiRequest;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class LoginScreenRequest extends AbstractApiRequest {
    @NotBlank
    String userName;
    @NotBlank
    String password;
}
