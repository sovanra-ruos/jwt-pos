package co.itc.pos.features.auth;

import co.itc.pos.features.auth.dto.AuthRequest;
import co.itc.pos.features.auth.dto.AuthResponse;
import co.itc.pos.features.auth.dto.Refresh;
import co.itc.pos.security.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final TokenGenerator tokenGenerator;

    public AuthResponse login(AuthRequest request) {
        Authentication authentication = daoAuthenticationProvider
                .authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        return tokenGenerator.generateTokens(authentication);
    }

    @Override
    public AuthResponse refreshToken(Refresh refreshToken) {
        Authentication authentication = jwtAuthenticationProvider
                .authenticate(new BearerTokenAuthenticationToken(refreshToken.refreshToken()));
        return tokenGenerator.generateTokens(authentication);
    }

}
