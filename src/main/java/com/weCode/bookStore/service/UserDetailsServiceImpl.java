package com.weCode.bookStore.service;

import com.weCode.bookStore.config.PasswordConfig;
import com.weCode.bookStore.dto.UserDto;
import com.weCode.bookStore.model.Usuario;
import com.weCode.bookStore.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordConfig passwordEncoder;

   @Autowired
    private UsuarioRepository usuarioRepository;

    /*@Autowired
    private UserService userService;*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*Usuario user = new Usuario();
        user.setUsername("leoc@gmail.com");
        user.setPassword(passwordEncoder.getPasswordEncoder().encode("password123"));
        user.setId(1L);
        return user;*/
        //Usuario user = usuarioRepository.findByUsername(username);
       Optional<Usuario> user = usuarioRepository.findByUsername(username);

       return user.orElseThrow(()-> new UsernameNotFoundException("invalid credencials"));
        /*Optional<UserDto> user = Optional.ofNullable(userService.getUserByUsername(username));

        return user.orElseThrow(()-> new UsernameNotFoundException("invalid credencials"));*/
        //return user;

    }
}
