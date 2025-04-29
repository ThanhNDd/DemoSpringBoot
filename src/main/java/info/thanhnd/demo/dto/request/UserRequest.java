package info.thanhnd.demo.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    @NotBlank
    private String name;

    @Email(message = "incorrect email format")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @Size(min = 6, message = "Password at least 6 character")
    private String password;
}
