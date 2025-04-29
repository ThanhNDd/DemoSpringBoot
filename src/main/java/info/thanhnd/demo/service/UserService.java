package info.thanhnd.demo.service;

import info.thanhnd.demo.dto.UserDTO;
import info.thanhnd.demo.dto.request.UserRequest;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO createUser(UserRequest userRequest);
    UserDTO updateUser(Long id, UserRequest userRequest);
    void deleteUser(Long id);
}
