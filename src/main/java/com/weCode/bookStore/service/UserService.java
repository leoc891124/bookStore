package com.weCode.bookStore.service;

import com.weCode.bookStore.config.PasswordConfig;
import com.weCode.bookStore.dto.UserDto;
import com.weCode.bookStore.model.Usuario;
import com.weCode.bookStore.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordConfig passwordConfig;

    @Autowired
    private ModelMapper modelMapper;

    public UUID addUser(UserDto userDto){
        Usuario user = modelMapper.map(userDto, Usuario.class);

        user.setPassword(passwordConfig.getPasswordEncoder().encode(userDto.getPassword()));

        Usuario createdUser = usuarioRepository.save(user);

        return createdUser.getId();
    }


}
