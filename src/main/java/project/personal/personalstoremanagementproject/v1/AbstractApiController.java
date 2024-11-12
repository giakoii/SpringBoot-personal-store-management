package project.personal.personalstoremanagementproject.v1;

import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.personal.personalstoremanagementproject.exceptions.DetailError;

import java.util.List;
import java.util.logging.Logger;

@RestController
public abstract class AbstractApiController<T extends AbstractApiRequest, U extends AbstractApiResponse<V>, V> {

    protected Logger logger = Logger.getLogger(this.getClass().getName());
    protected List<DetailError> detailErrorList;

    /**
     * API entry point
     * @param request the request body
     * @return ResponseEntity containing the response
     */
    @Transactional
    @PostMapping
    public U post(@Valid @RequestBody T request) throws Exception {
        List<DetailError> detailErrorList = validate(request);

        // Error check
        U errorResponse = errorCheck(request, detailErrorList);
        if (errorResponse != null) {
            return errorResponse;
        }

        // Main processing
        return exec(request);
    }

    /**
     * Main processing
     * @param request the request to process
     * @return ResponseEntity containing the response
     */
    protected abstract U exec (T request) throws Exception;

    /**
     * Error check
     * @param request the request to check
     * @param detailErrorList list of detected errors
     * @return ResponseEntity with error response or null if no errors found
     */
    protected abstract U errorCheck(T request, List<DetailError> detailErrorList);

    /**
     * Validation method for request
     * @param request the request to validate
     * @return List of detected errors
     */
    private List<DetailError> validate(T request) {
        // Implement validation logic here and return a list of errors
        return List.of();
    }
}
