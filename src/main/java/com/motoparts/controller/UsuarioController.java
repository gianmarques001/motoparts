package com.motoparts.controller;


import com.motoparts.entidade.Usuario;
import com.motoparts.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/motoparts/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @PostMapping
    public ResponseEntity<?> adicionarUsuario(@RequestBody @Valid Usuario usuario) {
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuario));
    }

//    @GetMapping("/{idPeca}")
//    public ResponseEntity<?> buscarPecaID(@PathVariable Integer idUsuario) {
//        Optional<Usuario> usuarioProcurado = usuarioService.buscarUsuarioPorID(idUsuario);
//        if (!usuarioProcurado.isEmpty()) {
//            return ResponseEntity.ok().body(usuarioProcurado);
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrada");
//    }


    // Editar Usuario
//    public ResponseEntity<?> editarUsuario(@PathVariable Integer idUsuario, @RequestBody @Valid Usuario usuario) {
//    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Integer idUsuario) {

        Optional<Usuario> usuarioProcurado = usuarioService.deletarUsuario(idUsuario);

        if (!usuarioProcurado.isEmpty()) {
            return ResponseEntity.ok().body(usuarioProcurado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");

    }
}
