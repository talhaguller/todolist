package com.talhaguller.todolist.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderUtil {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encode(String password){
        return passwordEncoder.encode(password);
    }

    public static boolean matches(String rawPassword, String encodedPasword){
        return passwordEncoder.matches(rawPassword,encodedPasword);
    }
}
