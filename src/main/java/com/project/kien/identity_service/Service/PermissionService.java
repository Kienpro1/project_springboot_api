package com.project.kien.identity_service.Service;

import com.project.kien.identity_service.dto.request.PermissionRequest;
import com.project.kien.identity_service.dto.response.PermissionResponse;
import com.project.kien.identity_service.entity.Permission;
import com.project.kien.identity_service.mapper.PermissionMapper;
import com.project.kien.identity_service.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;
    public PermissionResponse createPermission(PermissionRequest request){
Permission permission = permissionMapper.toPermission(request);
permissionRepository.save(permission);
return permissionMapper.toPermisonResponse(permission);
     }
   public List<PermissionResponse> getAll(){
        var permissions= permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermisonResponse).toList();

     }
    public void deletePermision(String permissionName){
           permissionRepository.deleteById(permissionName);
     }
}
