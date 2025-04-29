package info.thanhnd.demo.controller;

import info.thanhnd.demo.dto.UserDTO;
import info.thanhnd.demo.dto.request.UserRequest;
import info.thanhnd.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// khai báo đây là  1 RESTFul API controller
@RestController
// Khai báo đường dẫn endpoint
@RequestMapping("/api")
// đây là 1 annotation của Lombok giúp khởi tạo 1 constructor mà không cần phải code
@RequiredArgsConstructor
public class UserController {

    // inject lớp UserService vào class
    private final UserService userService;

    // annotation method post trong REST API
    @PostMapping("/users")
    public UserDTO createUser(@Valid @RequestBody UserRequest  request) {
        return  userService.createUser(request);
    }

    @GetMapping("/user/{userId}")
    public UserDTO getUserById(@PathVariable Long userId) {
        return  userService.getUserById(userId);
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/user/{userId}")
    public UserDTO updateUser(@PathVariable Long userId, @Valid @RequestBody UserRequest request) {
        return  userService.updateUser(userId, request);
    }

    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

}
