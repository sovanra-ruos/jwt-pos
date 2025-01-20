package co.itc.pos.features.user.dto;

import lombok.Builder;

@Builder
public record UserResponse(
        String username,
        String email,
        String profileImage
) {
}
