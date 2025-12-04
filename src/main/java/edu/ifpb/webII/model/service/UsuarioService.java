package edu.ifpb.webII.model.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.ifpb.webII.model.Perfil;
import edu.ifpb.webII.model.Usuario;
import edu.ifpb.webII.repository.UsuarioRepository;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = buscarPorUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário inexistente!"));

        // ==========================================================
        // SE A SENHA DO BANCO FOR "admin" OU "123456", ATUALIZA PARA BCRYPT
        // ==========================================================
        if (usuario.getSenha().equals("admin") || usuario.getSenha().equals("123456")) {

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            String senhaCriptografada = encoder.encode(usuario.getSenha());
            usuario.setSenha(senhaCriptografada);

            // Salva no banco para atualizar
            usuarioRepository.save(usuario);

            System.out.println(">>> Senha atualizada automaticamente para BCrypt.");
        }

        // ==========================================================
        // RETORNA O USER DO SPRING SECURITY
        // ==========================================================
        User userSpring = new User(
                usuario.getUsername(),
                usuario.getSenha(),
                AuthorityUtils.createAuthorityList(getAuthorities(usuario.getPerfis()))
        );
        return userSpring;
    }


    private String[] getAuthorities(List<Perfil> perfis) {
        String[] authorities = new String[perfis.size()];
        for (int i = 0; i < perfis.size(); i++) {
            authorities[i] = perfis.get(i).getNome();
        }
        return authorities;
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Transactional(readOnly = false)
    public void salvarUsuario(Usuario usuario) {
        String crypt = new BCryptPasswordEncoder().encode(usuario.getSenha());
        usuario.setSenha(crypt);
        usuarioRepository.save(usuario);
    }
    
    @Transactional
    public void atualizarSenha(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

}

