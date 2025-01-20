package co.itc.pos.features.user.dto;

import lombok.Builder;

@Builder
public record UserRequest(
        String userName,
        String email,
        String password,
        String confirm_password
) {
}
