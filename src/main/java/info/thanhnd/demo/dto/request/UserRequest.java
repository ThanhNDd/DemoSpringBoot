package info.thanhnd.demo.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    @NotBlank
    String name;

    @Email(message = "incorrect email format")
    @NotBlank(message = "Email cannot be blank")
    String email;

    @Size(min = 6, message = "PASSWORD_INVALID")
    String password;

    Set<String> roles;
}
