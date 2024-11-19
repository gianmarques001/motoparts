package com.motoparts.service;

import com.motoparts.entidade.Usuario;
import com.motoparts.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarUsuarioPorID(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> deletarUsuario(Integer id) {
        Optional<Usuario> usuario = buscarUsuarioPorID(id);

        if (!usuario.isEmpty()) {
            usuarioRepository.deleteById(id);
            return usuario;
        }
        return Optional.empty();
    }
}
