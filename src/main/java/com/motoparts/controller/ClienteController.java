package com.motoparts.controller;

import com.motoparts.dto.ClienteDTO;
import com.motoparts.dto.FornecedorDTO;
import com.motoparts.excecoes.entidade.MensagemExceptionHandler;
import com.motoparts.service.ClienteService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/motoparts/cliente")
public class ClienteController {


    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteDTO> listarClientes() {
        if (clienteService.listarClientes().isEmpty()) {
            return null;
        }
        return clienteService.listarClientes();
    }


    @PostMapping
    public ResponseEntity<?> adicionarCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
        clienteService.salvarCliente(clienteDTO);
        return ResponseEntity.ok().body(clienteDTO);
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<?> buscarClienteID(@PathVariable Integer idCliente) {
        Optional<ClienteDTO> clienteProcurado = clienteService.buscarClientePorID(idCliente);
        if (!clienteProcurado.isEmpty()) {
            return ResponseEntity.ok().body(clienteProcurado);
        }
        return ResponseEntity.ok().body(new MensagemExceptionHandler(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), "Cliente não encontrado", null));
    }

//    @PutMapping("/{idCliente}")
//    public ResponseEntity<?> editarCliente(@PathVariable Integer idCliente, @RequestBody @Valid ClienteDTO clienteDTO) {
//        Optional<ClienteDTO> clienteProcurado = clienteService.buscarClientePorID(idCliente);
//        if (!clienteProcurado.isEmpty()) {
//            Optional<ClienteDTO> dto = clienteService.atualizarClientePorEmail(clienteProcurado.get().getEmail(), clienteDTO);
//            if (!dto.isEmpty()) {
//                return ResponseEntity.ok().body(dto);
//            }
//        }
//        return ResponseEntity.ok().body(new MensagemExceptionHandler(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), "ID inválido", null));
//    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<?> deletarCliente(@PathVariable Integer idCliente) {
        Optional<ClienteDTO> clienteProcurado = clienteService.deletarCliente(idCliente);
        if (!clienteProcurado.isEmpty()) {
            return ResponseEntity.ok().body(clienteProcurado);
        }
        return ResponseEntity.ok().body(new MensagemExceptionHandler(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), "Cliente não encontrada", null));
    }


    @PutMapping("/{idCliente}")
    public ResponseEntity<?> editarCliente(@PathVariable Integer idCliente, @RequestBody @Valid ClienteDTO clienteDTO) {
        Optional<ClienteDTO> clienteProcurado = clienteService.buscarClientePorID(idCliente);
        if (!clienteProcurado.isEmpty()) {

            Optional<ClienteDTO> dto = clienteService.atualizarClientePorEmail(clienteProcurado.get().getEmail(), clienteDTO);
            if (!dto.isEmpty()) {
                return ResponseEntity.ok().body(dto);
            }
        }
        return ResponseEntity.ok().body(new MensagemExceptionHandler(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), "ID inválido", null));
    }
}
