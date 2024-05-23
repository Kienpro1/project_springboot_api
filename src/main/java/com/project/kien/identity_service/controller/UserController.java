package com.project.kien.identity_service.controller;

import com.project.kien.identity_service.Service.UserService;
import com.project.kien.identity_service.dto.request.ApiResponse;
import com.project.kien.identity_service.dto.request.UserCreationRequest;
import com.project.kien.identity_service.dto.request.UserUpdateRequest;
import com.project.kien.identity_service.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping
    List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    User getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    User updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("{userId}")
    ApiResponse<String> deleteUser(@PathVariable String userId) {
        ApiResponse apiResponse = new ApiResponse();
        User user =userService.getUser(userId);
        apiResponse.setCode(8888);
        apiResponse.setMessage("user with username"+user.getUsername()+" has been deleted");
        userService.deleteUser(userId);
        return apiResponse;
    }
}

