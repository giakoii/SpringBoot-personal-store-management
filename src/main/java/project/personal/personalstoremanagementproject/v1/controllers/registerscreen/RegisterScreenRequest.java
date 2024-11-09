package project.personal.personalstoremanagementproject.v1.controllers.registerscreen;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Value;
import project.personal.personalstoremanagementproject.v1.AbstractApiRequest;

import java.io.Serializable;

/**
 * DTO for {@link project.personal.personalstoremanagementproject.entities.User}
 */
@Value
public class RegisterScreenRequest extends AbstractApiRequest implements Serializable {
    @NotBlank(message = "UserName is required")
    String userName;
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @NotBlank(message = "Password is required")
    String password;
    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Email is invalid")
    String email;
}