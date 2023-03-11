package com.weCode.bookStore.controller;


import com.weCode.bookStore.dto.AuthenticationResponse;
import com.weCode.bookStore.dto.UserDto;
import com.weCode.bookStore.model.Usuario;
import com.weCode.bookStore.repository.UsuarioRepository;
import com.weCode.bookStore.service.UserService;
import com.weCode.bookStore.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.weCode.bookStore.dto.AuthCredentialsRequest;
import com.weCode.bookStore.service.UserDetailsServiceImpl;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /*@Autowired
    UsuarioRepository usuarioRepository;*/

    @Autowired
    private UserService userService;

    /*@Autowired
    private UsuarioRepository usuarioRepository;*/


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthCredentialsRequest request){
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUsername(), request.getPassword()
                            )
                    );

           /* Usuario user = (Usuario) authenticate.getPrincipal();
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            String token = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthenticationResponse("Bearer "+token));
                   /* .header(
                            HttpHeaders.AUTHORIZATION,
                            token
                    );



        } catch (BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();*/
        }


        catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            //throw new RuntimeException("username or password incorrect");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse("Bearer "+token));

    }

    @PostMapping("/register")
    public ResponseEntity<UUID> addUser(@Valid @RequestBody UserDto userDto){
        UUID uuid = userService.addUser(userDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(uuid);

    }
}
