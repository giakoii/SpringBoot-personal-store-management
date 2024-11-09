package project.personal.personalstoremanagementproject.v1.controllers.LoginScreen;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE )
@Data
@Builder
@Getter
public class LoginScreenEntity {
    String token;
    String refreshToken;
    String expirationTime;
}
