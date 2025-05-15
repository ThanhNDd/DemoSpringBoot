package info.thanhnd.demo.Exception;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED(103, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(104, "You do not have permission", HttpStatus.FORBIDDEN),
    USER_EXISTED(200, "User existed", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(201, "User not found", HttpStatus.NOT_FOUND),
    PASSWORD_INVALID(202, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    PERMISSION_EXISTED(300, "Permission existed", HttpStatus.BAD_REQUEST),
    PERMISSION_NOT_FOUND(300, "Permission not fount", HttpStatus.NOT_FOUND);

    int code;
    String message;
    HttpStatusCode statusCode;

}
