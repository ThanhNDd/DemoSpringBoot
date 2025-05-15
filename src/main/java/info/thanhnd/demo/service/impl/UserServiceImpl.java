package info.thanhnd.demo.service.impl;

import info.thanhnd.demo.Exception.CommonException;
import info.thanhnd.demo.Exception.ErrorCode;
import info.thanhnd.demo.dto.request.UserRequest;
import info.thanhnd.demo.dto.response.UserResponse;
import info.thanhnd.demo.entity.User;
import info.thanhnd.demo.enums.Roles;
import info.thanhnd.demo.mapper.UserMapper;
import info.thanhnd.demo.repository.UserRepository;
import info.thanhnd.demo.service.UserService;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.stream.Collectors;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    // inject UserRepository vào class này
    UserRepository userRepository;
    // inject PasswordEncoder vào class
    PasswordEncoder passwordEncoder;

    UserMapper userMapper;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    @PostAuthorize("hasRole('ADMIN') || returnObject.email == authentication.name")
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new CommonException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse createUser(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new CommonException(ErrorCode.USER_EXISTED);
        }

        HashSet<String> roles = new HashSet<String>();
        roles.add(Roles.USER.name());

        User user = User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .roles(roles)
                .build();
        User userSave = userRepository.save(user);
        return userMapper.toUserResponse(userSave);
    }


    @Override
    @PostAuthorize("hasRole('ADMIN') || returnObject.email == authentication.name")
    public UserResponse updateUser(Long id, UserRequest userRequest) {

        User user = userRepository.findById(id).orElseThrow(() -> new CommonException(ErrorCode.USER_NOT_FOUND));
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRoles(userRequest.getRoles());

        User updateUser = userRepository.save(user);
        return userMapper.toUserResponse(updateUser);
    }


    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }

}
