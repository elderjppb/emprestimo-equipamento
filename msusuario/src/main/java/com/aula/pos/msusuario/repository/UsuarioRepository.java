package com.aula.pos.msusuario.repository;

import com.aula.pos.msusuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    boolean existsByEmail(String email);

    boolean existsByMatricula(String matricula);
}
