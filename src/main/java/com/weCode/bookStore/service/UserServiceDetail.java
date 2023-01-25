package com.weCode.bookStore.service;

import com.weCode.bookStore.dto.AuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    private AuthenticationRequest request;

    public UserDetailService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    //this is the function which is used by spring security for loading user from db
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //System.out.println(passwordEncoder.encode("password123"));
        //request.setEmail("peter@gmail.com");
        //request.setPassword(passwordEncoder.encode("password123"));
        return new User("peter@gmail.com",passwordEncoder.encode("password123"), new ArrayList<>());


    }
}
