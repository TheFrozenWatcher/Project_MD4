package module4.projectmd4.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FormLogin {
    @NotNull(message = "Email must be not null")
    @NotEmpty(message = "Email must be not empty")
    @NotBlank(message = "Email must be not blank")
    private String email;
    @NotNull(message = "Password must be not null")
    @NotEmpty(message = "Password must be not empty")
    @NotBlank(message = "Password must be not blank")
    private String password;
}
