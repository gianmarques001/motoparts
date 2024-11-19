package com.motoparts.controller;


import com.motoparts.dto.PecaDTO;
import com.motoparts.entidade.Usuario;
import com.motoparts.excecoes.entidade.MensagemExceptionHandler;

import com.motoparts.service.FornecedorService;
import com.motoparts.service.PecaService;

import com.motoparts.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/api/motoparts/peca")
public class PecaController {

    @Autowired
    private PecaService pecaService;


    @Autowired
    private UsuarioService usuarioService;


    @GetMapping
    public List<PecaDTO> listarPecas() {
        return pecaService.listarPecas();
    }

    @PostMapping("")
    public ResponseEntity<?> adicionarPeca(@RequestBody @Valid PecaDTO pecaDto) {
//        System.out.println("ID " + idUsuario);

        // Segundo na tabela de Fornecedores
//        Optional<Usuario> usuario = usuarioService.buscarUsuarioPorID(idUsuario);
//        if (!usuario.isEmpty()) {
//            //pecaService.salvarPeca(pecaDto, usuario.get());
//
//        }
        pecaService.salvarPeca(pecaDto);
        return ResponseEntity.ok().body("Peça criada");
        // return ResponseEntity.ok().body(new MensagemExceptionHandler(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), "ID não encontrado", null));


    }

    @GetMapping("/{idPeca}")
    public ResponseEntity<?> buscarPecaID(@PathVariable Integer idPeca) {
        Optional<PecaDTO> pecaProcurada = pecaService.buscarPecaPorID(idPeca);
        if (!pecaProcurada.isEmpty()) {
            return ResponseEntity.ok().body(pecaProcurada);
        }
        return ResponseEntity.ok().body(new MensagemExceptionHandler(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), "Peça não encontrada", null));
    }


//    @PutMapping("/{idPeca}/{idUsuario}")
//    public ResponseEntity<?> editarPeca(@PathVariable Integer idPeca, @PathVariable Integer idUsuario, @RequestBody @Valid PecaDTO pecaDto) {
//        // Verificar se o ID é valido
//        Optional<Usuario> usuario = usuarioService.buscarUsuarioPorID(idUsuario);
//        if (!usuario.isEmpty()) {
//
//            Optional<PecaDTO> pecaProcurada = pecaService.buscarPecaPorID(idPeca);
//            if (!pecaProcurada.isEmpty()) {
//                // Atualizar a quantidade Peca
//                pecaProcurada.get().setQuantidade(pecaDto.getQuantidade());
//
//               // pecaService.salvarPeca(pecaProcurada.get(), usuario.get());
//                pecaService.salvarPeca(pecaProcurada.get());
//                return ResponseEntity.ok().body("Peça criada"); //
//            }
//
//            return ResponseEntity.ok().body(new MensagemExceptionHandler(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), "Peça inválido", null));
//        }
//        return ResponseEntity.ok().body(new MensagemExceptionHandler(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), "Usuário inválido", null));
//
//    }

    @PutMapping("/{idPeca}")
    public ResponseEntity<?> editarPeca(@PathVariable Integer idPeca, @RequestBody @Valid PecaDTO pecaDto) {

        Optional<PecaDTO> pecaProcurada = pecaService.buscarPecaPorID(idPeca);
        if (!pecaProcurada.isEmpty()) {
            // Atualizar a quantidade Peca
            pecaProcurada.get().setQuantidade(pecaDto.getQuantidade());

            // pecaService.salvarPeca(pecaProcurada.get(), usuario.get());
            pecaService.editarPeca(pecaProcurada.get());
            return ResponseEntity.ok().body("Peça editada"); //
        }

        return ResponseEntity.ok().body(new MensagemExceptionHandler(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), "Peça inválido", null));

    }


    @DeleteMapping("/{idPeca}")
    public ResponseEntity<?> deletarPeca(@PathVariable Integer idPeca) {
        Optional<PecaDTO> pecaDeletada = pecaService.deletarPeca(idPeca);
        if (!pecaDeletada.isEmpty()) {
            return ResponseEntity.ok().body(pecaDeletada);
        }
        return ResponseEntity.ok().body(new MensagemExceptionHandler(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), "Peça não encontrada", null));

    }


//    @GetMapping("/fornecedor/{idUsuario}")
//    public ResponseEntity<?> pegarPecaPorFornecedor(@PathVariable Integer idUsuario) {
//
//        Optional<Usuario> usuario = usuarioService.buscarUsuarioPorID(idUsuario);
//        if (!usuario.isEmpty()) {
//            List<PecaDTO> pecas = pecaService.buscarPecaPorFornecedor(usuario.get());
//            if (!pecas.isEmpty()) {
//                return ResponseEntity.ok().body(pecas);
//            }
//            return ResponseEntity.ok().body(new MensagemExceptionHandler(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), "Esse usuario não possui peças cadastradas", null));
//
//        }
//        return ResponseEntity.ok().body(new MensagemExceptionHandler(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), "Usuário não encontrado", null));
//
//    }


}