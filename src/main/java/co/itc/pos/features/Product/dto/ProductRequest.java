package co.itc.pos.features.Product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

    @Builder
    public record ProductRequest(
            String name,
            @NotBlank
            String description,
            @Pattern(regexp = "^[0-9]+(\\.[0-9]{1,2})?$")
            float price,
            String categoryName,
            String image
    ) {
    }
