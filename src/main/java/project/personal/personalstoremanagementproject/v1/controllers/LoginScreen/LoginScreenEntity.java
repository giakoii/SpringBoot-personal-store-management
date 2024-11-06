package project.personal.personalstoremanagementproject.v1.controllers.LoginScreen;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE )
@Data
@Builder
public class LoginScreenEntity {
    String fullName;
    String nickName;
    String phoneNumber;
    String email;
    String address;
}
