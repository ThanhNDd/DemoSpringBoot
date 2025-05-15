package info.thanhnd.demo.service;

import info.thanhnd.demo.dto.request.PermissionRequest;
import info.thanhnd.demo.dto.response.PermissionResponse;

import java.util.List;

public interface PermissionService {
    PermissionResponse create(PermissionRequest request);

    List<PermissionResponse> getAll();

    void delete(PermissionRequest request);
}
