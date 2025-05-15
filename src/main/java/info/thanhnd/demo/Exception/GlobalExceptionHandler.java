package info.thanhnd.demo.Exception;

import info.thanhnd.demo.dto.response.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// annotation đánh dấu đây là exception handler global
@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(value = RuntimeException.class)
//    ResponseEntity<APIResponse> handleRuntimeException(RuntimeException exception) {
//        APIResponse apiResponse = new APIResponse();
//        apiResponse.setCode(401);
//        apiResponse.setMessage(exception.getMessage());
//        apiResponse.setSuccess(Boolean.FALSE);
//        return ResponseEntity.badRequest().body(apiResponse);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse> handleException(Exception exception) {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        apiResponse.setSuccess(Boolean.FALSE);
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse> handleException(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();

        ErrorCode errorCode = ErrorCode.UNCATEGORIZED_EXCEPTION;
        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException ex) {

        }
        APIResponse apiResponse = new APIResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        apiResponse.setSuccess(Boolean.FALSE);
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<APIResponse> handleCommonException(CommonException exception) {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setCode(exception.getErrorCode().getCode());
        apiResponse.setMessage(exception.getErrorCode().getMessage());
        apiResponse.setSuccess(Boolean.FALSE);
        return ResponseEntity.status(exception.getErrorCode().getStatusCode()).body(apiResponse);
    }


    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<APIResponse> handleCommonException(AuthorizationDeniedException exception) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

        APIResponse apiResponse = new APIResponse();
        apiResponse.setMessage(errorCode.getMessage());
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setSuccess(Boolean.FALSE);
        return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse);
    }
}
