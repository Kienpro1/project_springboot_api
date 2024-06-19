package com.project.kien.identity_service.configuration;

import com.project.kien.identity_service.Enums.Role;
import com.project.kien.identity_service.entity.User;
import com.project.kien.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Bean
 ApplicationRunner applicationRunner(UserRepository userRepository) {
     return args -> {
         if(userRepository.findByUsername("admin").isEmpty()){
             var roles = new HashSet<String>();
             roles.add(Role.ADMIN.name());
                       User user = User.builder()
                               .username("admin")
                               .password(passwordEncoder.encode("admin"))
//                               .roles(roles)
                               .build();
                       userRepository.save(user);
                       log.warn("admin user has been generate with password admin");
         }
     };
 }
}
