package com.project.kien.identity_service.Service;

import com.project.kien.identity_service.dto.request.UserCreationRequest;
import com.project.kien.identity_service.dto.request.UserUpdateRequest;
import com.project.kien.identity_service.dto.response.UserResponse;
import com.project.kien.identity_service.entity.User;
import com.project.kien.identity_service.exception.AppException;
import com.project.kien.identity_service.exception.ErrorCode;
import com.project.kien.identity_service.mapper.UserMapper;
import com.project.kien.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public User createUser(UserCreationRequest request) {


        if (request.getDob().getYear() > 2024)
            throw new AppException(ErrorCode.INVALID_YEAR);

        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);
        User user = userMapper.toUser(request);
        return userRepository.save(user);


    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public UserResponse getUser(String username) {
        if (!userRepository.existsByUsername(username))
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        else
        return userMapper.toUserResponse(userRepository.findUserByUsername(username));

    }

    public UserResponse updateUser(String username, UserUpdateRequest request) {
        User user = userRepository.findUserByUsername(username);
        if (!userRepository.existsByUsername(username))
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        else
        userMapper.updateUser(user, request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
