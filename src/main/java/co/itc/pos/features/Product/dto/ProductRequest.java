package co.itc.pos.features.Product.dto;

import lombok.Builder;

@Builder
public record ProductRequest(
        String name,
        String description,
        float price,
        String categoryName,
        String image
) {
}
