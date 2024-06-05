package com.project.kien.identity_service.controller;

import com.project.kien.identity_service.Service.UserService;
import com.project.kien.identity_service.dto.request.ApiResponse;
import com.project.kien.identity_service.dto.request.UserCreationRequest;
import com.project.kien.identity_service.dto.request.UserUpdateRequest;
import com.project.kien.identity_service.dto.response.UserResponse;
import com.project.kien.identity_service.entity.User;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;
    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getUsers(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }

    @GetMapping("/{username}")
    ApiResponse<UserResponse> getUser(@PathVariable("username") String username) {

        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(username))
                .build();
    }
    @GetMapping("/myInfo")
ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @PutMapping("/{username}")
    UserResponse updateUser(@PathVariable String username, @RequestBody UserUpdateRequest request) {

        return userService.updateUser(username, request);
    }

    @DeleteMapping("{userId}")
    String deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return "user has been deleted";
    }
}

