package com.corner.app.service.impl;

import com.corner.app.dto.SignupRequest;
import com.corner.app.entity.Role;
import com.corner.app.entity.Users;
import com.corner.app.repository.RoleRepository;
import com.corner.app.repository.UserRepository;
import com.corner.app.service.AuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Override
    public Users createUser(SignupRequest signupRequest) {
        if(userRepository.existsByEmail(signupRequest.getEmail())){
            return null;
        }
        Optional<Role> userRoleOptional = roleRepository.findRoleByName("ROLE_USER");
        Role userRole = userRoleOptional.orElseGet(()->{
            Role newRole = new Role();
            newRole.setRoleName("ROLE_USER");
            return roleRepository.save(newRole);
        });
        Users users = new Users();
        BeanUtils.copyProperties(signupRequest, users); //instead of getting and setting we can use
        // this
        String hashedPassword = passwordEncoder.encode(signupRequest.getPassword());
        users.setPassword(hashedPassword);
        users.setRole(userRole);
        Users createdUser = userRepository.save(users);
        users.setId(createdUser.getId());
        return users;
    }
}
