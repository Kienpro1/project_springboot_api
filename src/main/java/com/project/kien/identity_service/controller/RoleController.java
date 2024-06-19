package com.project.kien.identity_service.controller;

import com.project.kien.identity_service.Service.RoleService;
import com.project.kien.identity_service.dto.request.ApiResponse;
import com.project.kien.identity_service.dto.request.RoleRequest;
import com.project.kien.identity_service.dto.response.RoleResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;
    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request){
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.createRole(request))
                .build();
    }
    @GetMapping
    ApiResponse<List<RoleResponse>> getAll(){
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }
    @DeleteMapping("/{roleName}")
    ApiResponse<String> deleteRole(@PathVariable String roleName){
        roleService.deleteRole(roleName);
        return ApiResponse.<String>builder()
                .result("xoá thành công")
                .build();

    }
}
