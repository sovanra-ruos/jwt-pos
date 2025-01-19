package co.itc.pos.features.Category.dto;

import lombok.Builder;

@Builder
public record CategoryRequest(
        String name
) {
}
