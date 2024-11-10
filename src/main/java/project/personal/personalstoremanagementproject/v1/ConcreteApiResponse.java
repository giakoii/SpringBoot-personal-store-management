package project.personal.personalstoremanagementproject.v1;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import project.personal.personalstoremanagementproject.exceptions.DetailError;
import project.personal.personalstoremanagementproject.exceptions.ErrorCode;

import java.util.List;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConcreteApiResponse<T> extends AbstractApiResponse<T> {
     boolean success;
     ErrorCode messageId;
     String message;
     List<DetailError> detailErrorList;
     T response;
}