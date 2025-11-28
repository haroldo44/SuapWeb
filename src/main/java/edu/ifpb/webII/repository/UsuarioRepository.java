package edu.ifpb.webII.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ifpb.webII.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByUsername(String username);
}
