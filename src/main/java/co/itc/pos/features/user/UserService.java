package co.itc.pos.features.user;

import co.itc.pos.features.user.dto.UserRequest;
import co.itc.pos.features.user.dto.UserResponse;
import org.springframework.security.core.Authentication;

public interface UserService {

    void register(UserRequest userRequest);

    UserResponse getMe (Authentication authentication);

}
