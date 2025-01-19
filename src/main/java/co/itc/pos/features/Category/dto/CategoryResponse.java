package co.itc.pos.features.Category.dto;

public record CategoryResponse(
        String uuid,
        String name,
        boolean active
) {
}
