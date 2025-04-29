package info.thanhnd.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Column(unique = true)
    private String email;

    @Size(min = 6, message = "Password at least 6 character")
    private String password;

}
