package co.itc.pos.features.auth.dto;

import lombok.Builder;

@Builder
public record Refresh(
        String refreshToken
) {
}
