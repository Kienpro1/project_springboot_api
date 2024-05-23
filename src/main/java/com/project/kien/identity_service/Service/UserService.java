package com.project.kien.identity_service.Service;

import com.project.kien.identity_service.dto.request.UserCreationRequest;
import com.project.kien.identity_service.dto.request.UserUpdateRequest;
import com.project.kien.identity_service.entity.User;
import com.project.kien.identity_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User createUser(UserCreationRequest request){

          User user = new User();
        if(userRepository.existsByUsername(request.getUsername()))
            throw new RuntimeException("User Existed");
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setDob(request.getDob());
       return userRepository.save(user);


    }
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public User getUser(String id){
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("user not found"));
    }
    public User updateUser(String id,UserUpdateRequest request){
        User user = this.getUser(id);
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setPassword(request.getPassword());
        user.setDob(request.getDob());
        return userRepository.save(user);
    }
    public void deleteUser(String id){
        userRepository.deleteById(id);
    }
}
