package info.thanhnd.demo.controller;

import info.thanhnd.demo.dto.request.UserRequest;
import info.thanhnd.demo.dto.response.APIResponse;
import info.thanhnd.demo.dto.response.UserResponse;
import info.thanhnd.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// khai báo đây là  1 RESTFul API controller
@RestController
// Khai báo đường dẫn endpoint
@RequestMapping("/users")
// đây là 1 annotation của Lombok giúp khởi tạo 1 constructor mà không cần phải code
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {

    // inject lớp UserService vào class
    UserService userService;

    // annotation method post trong REST API
    @PostMapping
    public APIResponse<UserResponse> createUser(@Valid @RequestBody UserRequest request) {
        APIResponse<UserResponse> apiResponse = new APIResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping("/{id}")
    public APIResponse<UserResponse> getUserById(@PathVariable Long id) {

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        log.info("JWT Claims: {}", jwt.getClaims());

        log.info("username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority ->
                log.info(grantedAuthority.getAuthority())
        );

        APIResponse<UserResponse> apiResponse = new APIResponse<>();
        apiResponse.setResult(userService.getUserById(id));
        return apiResponse;
    }

    @GetMapping
    public APIResponse<List<UserResponse>> getAllUsers() {
        APIResponse<List<UserResponse>> apiResponse = new APIResponse<>();
        apiResponse.setResult(userService.getAllUsers());
        return apiResponse;
    }

    @PutMapping("/{id}")
    public APIResponse<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest request) {
        APIResponse<UserResponse> apiResponse = new APIResponse<>();
        apiResponse.setResult(userService.updateUser(id, request));
        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
