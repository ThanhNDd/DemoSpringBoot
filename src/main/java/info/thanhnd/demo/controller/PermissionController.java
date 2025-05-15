package info.thanhnd.demo.controller;

import info.thanhnd.demo.dto.request.PermissionRequest;
import info.thanhnd.demo.dto.response.APIResponse;
import info.thanhnd.demo.dto.response.PermissionResponse;
import info.thanhnd.demo.service.PermissionService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/permissions")
public class PermissionController {

    PermissionService permissionService;

    @PostMapping
    public APIResponse<PermissionResponse> create(@Valid @RequestBody PermissionRequest request) {
        APIResponse<PermissionResponse> apiResponse = new APIResponse<>();
        apiResponse.setResult(permissionService.create(request));
        return apiResponse;
    }

    @GetMapping
    public List<PermissionResponse> getPermissions() {
        return permissionService.getAll();
    }

    @DeleteMapping
    public void delete(@Valid @RequestBody PermissionRequest request) {
        permissionService.delete(request);
    }

}
