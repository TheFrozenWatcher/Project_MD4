package module4.projectmd4.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FormCategory {
    @NotNull(message = "Name can not be null")
    @NotBlank(message = "Name can not be empty")
    @NotEmpty(message = "Name can not be empty")
    private String categoryName;

    private String description;


}
