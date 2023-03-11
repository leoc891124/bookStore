package com.weCode.bookStore.service;

import com.weCode.bookStore.dto.UserDto;
import com.weCode.bookStore.model.Usuario;
import com.weCode.bookStore.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

   @InjectMocks
    UserService userService;

    @Mock
    ModelMapper modelMapper;

    @Mock
    UsuarioRepository usuarioRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    public void shouldReturnUserIdWhenUserData(){
        UUID id = UUID.randomUUID();
        when(usuarioRepository.save(any())).thenReturn(getUser(id));
        when(modelMapper.map(any(), any())).thenReturn(getUser(id));

        UUID uuid = userService.addUser(getUserDto());

        assertThat(uuid).isNotNull();
        assertThat(uuid).isEqualTo(id);

    }

    @Test
    public void shouldReturnUserWhenEmailExist(){
        UUID id = UUID.randomUUID();
        when(usuarioRepository.findByUsername(anyString())).thenReturn(Optional.ofNullable(getUser(id)));
        when(modelMapper.map(any(), any())).thenReturn(getUserDto());

        UserDto username = userService.getUserByUsername("email");


        assertThat(username).isNotNull();
        assertThat(username.getUsername()).isEqualTo("test@gmail.com");

    }

    private UserDto getUserDto() {
        return UserDto.builder()
                .password("password")
                .id(UUID.randomUUID())
                .username("test@gmail.com")
                .build();
    }

    private Usuario getUser(UUID uuid) {
        return Usuario.builder()
                .password("password")
                .id(uuid)
                .username("test2@gmail.com")
                .build();
    }


}
