package project.personal.personalstoremanagementproject.v1;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class AbstractApiRequest {
 /**
  * API Caller ID
  */
  String apiCallerId;
   /**
    * API Caller Name
    */
  boolean isOnlyValidation;
}
