package co.itc.pos.mapper;

import co.itc.pos.domain.User;
import co.itc.pos.features.user.dto.UserRequest;
import co.itc.pos.features.user.dto.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequest userRequest);

    UserResponse toResponse(User user);

}
