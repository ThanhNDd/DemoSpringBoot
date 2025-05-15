package info.thanhnd.demo.mapper;

import info.thanhnd.demo.dto.request.PermissionRequest;
import info.thanhnd.demo.dto.response.PermissionResponse;
import info.thanhnd.demo.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
