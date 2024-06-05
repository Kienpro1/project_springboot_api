package com.project.kien.identity_service.Service;


import com.project.kien.identity_service.Enums.Role;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder ;


    public UserResponse createUser(UserCreationRequest request){
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUsers() {
        log.info("in method get user") ;
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }
    //postauthorize thuc thi ham xong moi check role pre la truoc
@PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUser(String username) {
        log.info("in method get user") ;
        if (!userRepository.existsByUsername(username))
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        else
        return userMapper.toUserResponse(userRepository.findByUsername(username).orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND)));

    }
public UserResponse getMyInfo(){

     var context =SecurityContextHolder.getContext();
     String name =context.getAuthentication().getName();
     String princi =context.getAuthentication().getPrincipal().toString();
log.info("username : {}",name) ;
log.info("principal : {}",princi) ;
  User user = userRepository.findByUsername(name).orElseThrow(() ->new AppException(ErrorCode.USER_NOT_EXISTED));
  return userMapper.toUserResponse(user);
}
    public UserResponse updateUser(String username, UserUpdateRequest request) {
        User user = userRepository.findByUsername(username).orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND));
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
