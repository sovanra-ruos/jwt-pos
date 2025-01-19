package co.itc.pos.features.auth.dto;

import lombok.Builder;

@Builder
public record AuthResponse(
        Long userId,
        String accessToken,
        String refreshToken
) {
}
