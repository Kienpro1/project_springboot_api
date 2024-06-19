package com.project.kien.identity_service.controller;

import com.project.kien.identity_service.Service.PermissionService;
import com.project.kien.identity_service.dto.request.ApiResponse;
import com.project.kien.identity_service.dto.request.PermissionRequest;
import com.project.kien.identity_service.dto.response.PermissionResponse;
import com.project.kien.identity_service.entity.Permission;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionService permissionService;
    @PostMapping
    ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionRequest permissionRequest) {
    return ApiResponse.<PermissionResponse>builder()
            .result(permissionService.createPermission(permissionRequest))
            .build();
    }
    @GetMapping
ApiResponse<List<PermissionResponse>> getAll(){
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAll())
                .build();
}
@DeleteMapping("/{permissionName}")
    ApiResponse<String> deletePermission(@PathVariable String permissionName){

        permissionService.deletePermision(permissionName);
    return ApiResponse.<String>builder()
            .result("xoá thành công")
            .build();
}

}
