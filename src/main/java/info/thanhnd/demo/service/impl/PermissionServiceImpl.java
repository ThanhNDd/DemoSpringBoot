package info.thanhnd.demo.service.impl;

import info.thanhnd.demo.Exception.CommonException;
import info.thanhnd.demo.Exception.ErrorCode;
import info.thanhnd.demo.dto.request.PermissionRequest;
import info.thanhnd.demo.dto.response.PermissionResponse;
import info.thanhnd.demo.entity.Permission;
import info.thanhnd.demo.mapper.PermissionMapper;
import info.thanhnd.demo.repository.PermissionRepository;
import info.thanhnd.demo.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionServiceImpl implements PermissionService {

    PermissionRepository permissionRepository;

    PermissionMapper permissionMapper;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public PermissionResponse create(PermissionRequest request) {
        if (permissionRepository.existsById(request.getName())) {
            throw new CommonException(ErrorCode.PERMISSION_EXISTED);
        }

//        Permission permission = permissionMapper.toPermission(request);
        Permission permission = Permission.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
        Permission permissionSaved = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permissionSaved);
    }

    @Override
    public List<PermissionResponse> getAll() {
        return permissionRepository.findAll()
                .stream()
                .map(permissionMapper::toPermissionResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(@RequestBody PermissionRequest request) {
        if (!permissionRepository.existsById(request.getName())) {
            throw new CommonException(ErrorCode.PERMISSION_NOT_FOUND);
        }

        permissionRepository.deleteById(request.getName());
    }
}
