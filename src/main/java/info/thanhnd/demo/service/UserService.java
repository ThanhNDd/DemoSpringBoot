package info.thanhnd.demo.service;

import info.thanhnd.demo.dto.request.UserRequest;
import info.thanhnd.demo.dto.response.APIResponse;
import info.thanhnd.demo.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    UserResponse createUser(UserRequest userRequest);

    UserResponse updateUser(Long id, UserRequest userRequest);

    void deleteUser(Long id);
}
