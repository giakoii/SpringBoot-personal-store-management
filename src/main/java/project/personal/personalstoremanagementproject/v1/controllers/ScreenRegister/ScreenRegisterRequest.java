package project.personal.personalstoremanagementproject.v1.controllers.ScreenRegister;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import project.personal.personalstoremanagementproject.v1.AbstractApiRequest;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class ScreenRegisterRequest extends AbstractApiRequest {
    String userName;
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    String password;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email must be in the format")
    String email;
    @Pattern(regexp = "^[a-zA-Z\\s]{1,255}$", message = "First name must contain only letters and spaces")
    String fullName;
    @Pattern(regexp = "^[a-zA-Z\\s]{1,255}$", message = "Nick name must contain only letters and spaces")
    String nickName;
    @Pattern(regexp = "^(0)\\d{9,10}$", message = "Phone number must be in the format +84xxxxxxxxx or 0xxxxxxxxx")
    String phoneNumber;
    @Pattern(regexp = "^[a-zA-Z\\s]{1,255}$", message = "Address name must contain only letters and spaces")
    String address;
}
