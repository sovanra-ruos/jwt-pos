package co.itc.pos.features.user;


import co.itc.pos.domain.Role;
import co.itc.pos.domain.User;
import co.itc.pos.features.user.dto.UserRequest;
import co.itc.pos.features.user.dto.UserResponse;
import co.itc.pos.mapper.UserMapper;
import co.itc.pos.utils.RandomUUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final RandomUUID randomUUID;


    @Override
    public void register(UserRequest userRequest) {

        User user = userMapper.toEntity(userRequest);

        Role role = roleRepository.findByName("USER").orElseThrow(
                ()-> new NoSuchElementException("Role not found")
        );

        user.setUuid(randomUUID.generateUser("ITC"));
        user.setUserName(userRequest.userName());
        user.setEmail(userRequest.email());
        user.setRoles(List.of(role));
        user.setProfileImage("default.jpg");
        user.setPassword(new BCryptPasswordEncoder().encode(userRequest.password()));
        user.setConfirm_password(new BCryptPasswordEncoder().encode(userRequest.confirm_password()));


        userRepository.save(user);
    }

    @Override
    public UserResponse getMe(Authentication authentication) {

        User user = userRepository.findUserByEmail(authentication.getName()).orElseThrow(
                ()-> new NoSuchElementException("User not found")
        );

        return userMapper.toResponse(user);
    }


}
