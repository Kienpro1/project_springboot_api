package com.project.kien.identity_service.dto.response;

import com.project.kien.identity_service.entity.Permission;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse {
String name;
String description;
Set<PermissionResponse> permissions;

}
