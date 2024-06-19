package com.project.kien.identity_service.mapper;

import com.project.kien.identity_service.dto.request.PermissionRequest;
import com.project.kien.identity_service.dto.response.PermissionResponse;
import com.project.kien.identity_service.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
//    User toUser(UserUpdateRequest request);
//    void updateUser(@MappingTarget User user, UserUpdateRequest request);
    PermissionResponse toPermisonResponse(Permission permission);
}
