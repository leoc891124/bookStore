package com.weCode.bookStore.controller;

import com.weCode.bookStore.config.JwtUtil;
import com.weCode.bookStore.dto.AuthenticationRequest;
import com.weCode.bookStore.dto.AuthenticationResponse;
import com.weCode.bookStore.service.UserServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1")
public class AuthController {
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final UserServiceDetail userServiceDetail;
    @Autowired
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, UserServiceDetail userServiceDetail, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userServiceDetail = userServiceDetail;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        try{
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        } catch (BadCredentialsException ex){
            throw new RuntimeException("Username or password is incorrect");
        }

        UserDetails userDetails = userServiceDetail.loadUserByUsername(request.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        System.out.println("toke "+token);

        return ResponseEntity.ok(new AuthenticationResponse("Bearer "+token));

    }
}
