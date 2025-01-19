package co.itc.pos.features.Product.dto;

import lombok.Builder;

@Builder
public record ProductResponse(
        String uuid,
        String name,
        String description,
        float price,
        String image
) {
}
