package com.talhaguller.todolist.service;

import com.talhaguller.todolist.dto.RegisterRequest;
import com.talhaguller.todolist.entity.User;
import com.talhaguller.todolist.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public User registerUser(RegisterRequest registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPasasword()));
        return userRepository.save(user);
    }

    public User findUserByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(username +" adında kullanıcı adı bulunamadı"));
    }
}
