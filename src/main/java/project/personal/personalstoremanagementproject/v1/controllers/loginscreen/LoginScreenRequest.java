package project.personal.personalstoremanagementproject.v1.controllers.loginscreen;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import project.personal.personalstoremanagementproject.v1.AbstractApiRequest;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class LoginScreenRequest extends AbstractApiRequest {
    @NotBlank(message = "Username cannot be blank")
    String userName;
    @NotBlank(message = "Password cannot be blank")
    String password;
}
