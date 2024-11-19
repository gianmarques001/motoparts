package com.motoparts.service;

import com.motoparts.dto.ClienteDTO;
import com.motoparts.dto.FornecedorDTO;
import com.motoparts.entidade.Cliente;
import com.motoparts.entidade.Fornecedor;
import com.motoparts.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private  ClienteRepository clienteRepository;
    @Autowired
    private UsuarioService usuarioService;


    private Cliente converterEntidade(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setEndereco(clienteDTO.getEndereco());
        cliente.setNumero(clienteDTO.getNumero());
        cliente.setCep(clienteDTO.getCep());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setSenha(clienteDTO.getSenha());
        cliente.setIdentificador(clienteDTO.getIdentificador());
        cliente.setTipo(clienteDTO.getTipo());
        return cliente;
    }

    private ClienteDTO converterDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setTelefone(cliente.getTelefone());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setEndereco(cliente.getEndereco());
        clienteDTO.setNumero(cliente.getNumero());
        clienteDTO.setCep(cliente.getCep());
        clienteDTO.setSenha(cliente.getSenha());
        clienteDTO.setIdentificador(cliente.getIdentificador());
        clienteDTO.setTipo(cliente.getTipo());
        return clienteDTO;
    }


    public List<ClienteDTO> listarClientes() {
        ArrayList<ClienteDTO> clientesDTO = new ArrayList<ClienteDTO>();
        List<Cliente> clientes = clienteRepository.findAll();
        for (Cliente cliente : clientes) {
            clientesDTO.add(converterDTO(cliente));
        }
        return clientesDTO;

    }

    public void salvarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = converterEntidade(clienteDTO);
        clienteRepository.save(cliente);
    }

    public Optional<ClienteDTO> buscarClientePorID(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (!cliente.isEmpty()) {
            ClienteDTO clienteDTO = converterDTO(cliente.get());
            return Optional.of(clienteDTO);
        }
        return Optional.empty();
    }

    public Optional<ClienteDTO> deletarCliente(Integer id) {
        Optional<ClienteDTO> clienteDTO = buscarClientePorID(id);
        if (!clienteDTO.isEmpty()) {
            usuarioService.deletarUsuario(id);
            return clienteDTO;
        }
        return Optional.empty();
    }



    public Optional<ClienteDTO> atualizarClientePorEmail(String email, ClienteDTO dto) {

        Optional<Cliente> clienteOpt = clienteRepository.findByEmail(email);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();

            int registrosAtualizados = clienteRepository.atualizarClientePorEmail(
                    email,
                    dto.getEndereco(),
                    dto.getCep(),
                    dto.getNumero(),
                    dto.getTelefone()
            );

            if (registrosAtualizados > 0) {
                // Atualiza os campos do fornecedor localmente
                cliente.setEndereco(dto.getEndereco());
                cliente.setCep(dto.getCep());
                cliente.setNumero(dto.getNumero());
                cliente.setTelefone(dto.getTelefone());
                ClienteDTO clienteDTO = converterDTO(cliente);
                return Optional.of(clienteDTO);
            } else {
                return Optional.empty();

            }
        } else {
            return Optional.empty();
        }
    }



}
