package com.motoparts.controller;

import com.motoparts.dto.FornecedorDTO;

import com.motoparts.excecoes.entidade.MensagemExceptionHandler;
import com.motoparts.service.FornecedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/motoparts/fornecedor")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping
    public List<FornecedorDTO> listarFornecedores() {
        if (fornecedorService.listarFornecedores().isEmpty()) {
            return null;
        }
        return fornecedorService.listarFornecedores();
    }


    @PostMapping
    public ResponseEntity<?> adicionarFornecedor(@RequestBody @Valid FornecedorDTO fornecedorDTO) {
        fornecedorService.salvarFornecedor(fornecedorDTO);
        return ResponseEntity.ok().body(fornecedorDTO);
    }

    @GetMapping("/{idFornecedor}")
    public ResponseEntity<?> buscarFornecedorID(@PathVariable Integer idFornecedor) {
        Optional<FornecedorDTO> fornecedorProcurado = fornecedorService.buscarFornecedorPorID(idFornecedor);
        if (!fornecedorProcurado.isEmpty()) {
            return ResponseEntity.ok().body(fornecedorProcurado);
        }
        return ResponseEntity.ok().body(new MensagemExceptionHandler(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), "Fornecedor não encontrado", null));
    }

    @PutMapping("/{idFornecedor}")
    public ResponseEntity<?> editarFornecedor(@PathVariable Integer idFornecedor, @RequestBody @Valid FornecedorDTO fornecedorDTO) {
        Optional<FornecedorDTO> fornecedorProcurado = fornecedorService.buscarFornecedorPorID(idFornecedor);
        if (!fornecedorProcurado.isEmpty()) {

            Optional<FornecedorDTO> dto = fornecedorService.atualizarFornecedorPorEmail(fornecedorProcurado.get().getEmail(), fornecedorDTO);
            if (!dto.isEmpty()) {
                return ResponseEntity.ok().body(dto);
            }
        }
        return ResponseEntity.ok().body(new MensagemExceptionHandler(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), "ID inválido", null));
    }

    @DeleteMapping("/{idFornecedor}")
    public ResponseEntity<?> deletarFornecedor(@PathVariable Integer idFornecedor) {
        Optional<FornecedorDTO> fornecedorDeletado = fornecedorService.deletarFornecedor(idFornecedor);
        if (!fornecedorDeletado.isEmpty()) {
            return ResponseEntity.ok().body(fornecedorDeletado);
        }
        return ResponseEntity.ok().body(new MensagemExceptionHandler(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), "Fornecedor não encontrada", null));
    }
}
