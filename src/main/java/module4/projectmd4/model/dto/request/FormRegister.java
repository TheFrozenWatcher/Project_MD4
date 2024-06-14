package module4.projectmd4.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
//@PasswordMatches
public class FormRegister {
    @NotNull(message = "Username can not be null")
    @NotBlank(message = "Username can not be empty")
    @NotEmpty(message = "Username can not be empty")
    String userName;
    //    @UniqueEmail
//    @ValidEmail
    String email;
    @NotNull(message = "Full name can not be null")
    @NotBlank(message = "Full name can not be empty")
    @NotEmpty(message = "Full name can not be empty")
    String fullName;
    //    @ValidPassword
    String password;
    String repeatPassword;
    @NotNull(message = "Address can not be null")
    @NotBlank(message = "Address can not be empty")
    @NotEmpty(message = "Address can not be empty")
    String fullAddress;
    //    @ValidPhoneNumber
//    @UniquePhoneNumber
    String phone;
    private Set<String> roles;
//    private MultipartFile userAvatar;
//    Integer googleAccountId;
}
