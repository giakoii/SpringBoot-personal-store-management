package project.personal.personalstoremanagementproject.v1;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import project.personal.personalstoremanagementproject.exceptions.ErrorCode;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public abstract class AbstractApiResponse<T> {
    /**
     * Success
     */
     boolean success;

    /**
     * MessageId
     */
     ErrorCode messageId;

    /**
     * Message
     */
     String message;

     /**
      * DetailErrorList
      */
     List<DetailError> detailErrorList ;

     /**
      * Response
      */
     T Response ;

    /**
     * Set Message
     * @param messageId
     * @param message
     */
    public void setMessage(ErrorCode messageId, String message) {
        this.message = message;
        this.messageId = messageId;
    }

}
