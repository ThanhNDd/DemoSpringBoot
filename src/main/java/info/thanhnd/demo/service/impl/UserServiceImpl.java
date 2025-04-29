package info.thanhnd.demo.service.impl;

import info.thanhnd.demo.dto.UserDTO;
import info.thanhnd.demo.dto.request.UserRequest;
import info.thanhnd.demo.entity.User;
import info.thanhnd.demo.repository.UserRepository;
import info.thanhnd.demo.service.UserService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    // inject UserRepository vào class này
    private final UserRepository userRepository;
    // inject PasswordEncoder vào class
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user  = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return  mapToDTO(user);
    }

    @Override
    public UserDTO createUser(UserRequest userRequest) {
        if(userRepository.existsByEmail(userRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .build();
        User userSave = userRepository.save(user);
        return mapToDTO(userSave);
    }

    @Override
    public UserDTO updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userRequest.getName());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        User updateUser = userRepository.save(user);
        return  mapToDTO(updateUser);
    }

    @Override
    public void deleteUser(Long id) {
        if(!userRepository.existsById(id)) {
            throw  new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }

    private UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
