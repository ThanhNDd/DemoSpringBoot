package info.thanhnd.demo.mapper;

import info.thanhnd.demo.dto.request.UserRequest;
import info.thanhnd.demo.dto.response.APIResponse;
import info.thanhnd.demo.dto.response.UserResponse;
import info.thanhnd.demo.entity.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequest request);

    UserResponse toUserResponse(User user);
}
