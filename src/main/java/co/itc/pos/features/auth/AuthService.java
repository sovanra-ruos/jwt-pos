package co.itc.pos.features.auth;

import co.itc.pos.features.auth.dto.AuthRequest;
import co.itc.pos.features.auth.dto.AuthResponse;
import co.itc.pos.features.auth.dto.Refresh;

public interface AuthService {

    AuthResponse login(AuthRequest request);

    AuthResponse refreshToken(Refresh refreshToken);

}
