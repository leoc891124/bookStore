package com.weCode.bookStore.service;

import com.weCode.bookStore.dto.UserDto;
import com.weCode.bookStore.model.Usuario;
import com.weCode.bookStore.repository.UsuarioRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

   /* @InjectMocks
    UserService userService;

    @Mock
    ModelMapper modelMapper;

    @Mock
    UsuarioRepository usuarioRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    public UUID addUser(UserDto userDto){
        Usuario user = modelMapper.map(userDto, Usuario.class);

        user.setPassword();
    }*/


}
