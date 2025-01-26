package co.itc.pos.features.Category.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CategoryRequest(
        @NotBlank(message = "Name can not be null or blank")
        String name,
        String image
) {
}
