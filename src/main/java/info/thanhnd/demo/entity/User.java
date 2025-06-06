package info.thanhnd.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @NotBlank(message = "Email cannot be blank")
    @Column(unique = true)
    String email;

    @Size(min = 6, message = "PASSWORD_INVALID")
    String password;

    @CreationTimestamp
    LocalDateTime createdDate;

    @CreationTimestamp
    LocalDateTime updatedDate;

    Set<String> roles;

}
