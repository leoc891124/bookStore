package com.weCode.bookStore.repository;

import com.weCode.bookStore.model.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;


public interface UsuarioRepository extends CrudRepository<Usuario, UUID> {

    Optional<Usuario> findByUsername(String username);


}
