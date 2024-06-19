package com.project.kien.identity_service.mapper;

import ch.qos.logback.core.model.ComponentModel;
import com.project.kien.identity_service.dto.request.UserCreationRequest;
import com.project.kien.identity_service.dto.request.UserUpdateRequest;
import com.project.kien.identity_service.dto.response.UserResponse;
import com.project.kien.identity_service.entity.User;
import com.project.kien.identity_service.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", ignore = true)
    User toUser(UserCreationRequest request);
//    User toUser(UserUpdateRequest request);\
@Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
    @Mapping(source = "firstname",target = "lastname")
    UserResponse toUserResponse(User user);
}
