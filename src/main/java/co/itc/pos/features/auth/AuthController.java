package co.itc.pos.features.auth;

import co.itc.pos.features.auth.dto.AuthRequest;
import co.itc.pos.features.auth.dto.AuthResponse;
import co.itc.pos.features.auth.dto.Refresh;
import co.itc.pos.features.user.UserService;
import co.itc.pos.features.user.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequest userRequest) {
        userService.register(userRequest);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.login(authRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody Refresh refreshToken) {
        return ResponseEntity.ok(authService.refreshToken(refreshToken));
    }

}
