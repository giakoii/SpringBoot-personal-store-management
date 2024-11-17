package project.personal.personalstoremanagementproject.v1.controllers.user.profilescreen.models;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Value
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class SelectUserProfileModel{
    /**
     * Full name
     */
    String fullName;

    /**
     * Nick name
     */
    String nickName;

    /**
     * Phone number
     */
    String phoneNumber;

    /**
     * Email
     */
    String email;

    /**
     * Address
     */
    String address;

    /**
     * Image
     */
    String img;
}
