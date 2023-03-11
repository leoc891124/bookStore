package com.weCode.bookStore.service;

import com.weCode.bookStore.config.PasswordConfig;
import com.weCode.bookStore.dto.UserDto;
import com.weCode.bookStore.model.Usuario;
import com.weCode.bookStore.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /*@Autowired
    private PasswordConfig passwordConfig;*/

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public UUID addUser(UserDto userDto){
        Usuario user = modelMapper.map(userDto, Usuario.class);

        //user.setPassword(passwordConfig.getPasswordEncoder().encode(userDto.getPassword()));
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setId(null);

        Usuario createdUser = usuarioRepository.save(user);


        return createdUser.getId();
    }

    //using just for testing purpose
    public UserDto getUserByUsername(String username){

        Optional<Usuario> byUsername = usuarioRepository.findByUsername(username);

        UserDto user = modelMapper.map(byUsername, UserDto.class);

        return user;

    }


}
