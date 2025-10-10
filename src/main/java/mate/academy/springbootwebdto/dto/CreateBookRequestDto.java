package mate.academy.springbootwebdto.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class CreateBookRequestDto {
    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 200, message = "Title must be 1–200 characters")
    private String title;

    @NotBlank(message = "Author is required")
    @Size(min = 2, max = 120, message = "Author must be 2–120 characters")
    private String author;

    @NotBlank(message = "ISBN is required")
    private String isbn;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.00", inclusive = true, message = "Price must be ≥ 0.00")
    private BigDecimal price;

    @NotBlank(message = "Description is required")
    @Size(max = 2000, message = "Description must be ≤ 2000 characters")
    private String description;

    @NotBlank(message = "Cover image URL is required")
    @URL(message = "Cover image must be a valid URL (http/https)")
    private String coverImage;
}
