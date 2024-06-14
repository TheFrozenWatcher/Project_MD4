package module4.projectmd4.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FormProduct {
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be empty")
    @NotEmpty(message = "Name cannot be empty")
    private String productName;
    private String description;

    @NotNull(message = "Unit price is mandatory")
    @Positive(message = "Unit price must be positive")
    private Double unitPrice;

    @NotNull(message = "Stock quantity is mandatory")
    @Positive(message = "Stock quantity must be positive")
    private Integer stockQuantity;

    private String image;

    @NotNull(message = "Category ID is mandatory")
    private Long categoryId;
}
