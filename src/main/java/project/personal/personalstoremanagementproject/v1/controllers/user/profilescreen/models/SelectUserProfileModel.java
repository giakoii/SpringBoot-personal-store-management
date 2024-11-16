package project.personal.personalstoremanagementproject.v1.controllers.user.profilescreen.models;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Value
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class SelectUserProfileModel{

    String fullName;
    String nickName;
    String phoneNumber;
    String email;
    String address;
    String img;
}
