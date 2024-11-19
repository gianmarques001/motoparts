package com.motoparts.service;

import com.motoparts.dto.FornecedorDTO;
import com.motoparts.entidade.Fornecedor;
import com.motoparts.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private UsuarioService usuarioService;

    private Fornecedor converterEntidade(FornecedorDTO fornecedorDto) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(fornecedorDto.getNome());
        fornecedor.setTelefone(fornecedorDto.getTelefone());
        fornecedor.setEmail(fornecedorDto.getEmail());
        fornecedor.setEndereco(fornecedorDto.getEndereco());
        fornecedor.setNumero(fornecedorDto.getNumero());
        fornecedor.setCep(fornecedorDto.getCep());
        fornecedor.setIdentificador(fornecedorDto.getIdentificador());
        fornecedor.setTipo(fornecedorDto.getTipo());
        return fornecedor;
    }


    private FornecedorDTO converterDTO(Fornecedor fornecedor) {
        FornecedorDTO fornecedorDTO = new FornecedorDTO();
        fornecedorDTO.setNome(fornecedor.getNome());
        fornecedorDTO.setTelefone(fornecedor.getTelefone());
        fornecedorDTO.setEmail(fornecedor.getEmail());
        fornecedorDTO.setEndereco(fornecedor.getEndereco());
        fornecedorDTO.setNumero(fornecedor.getNumero());
        fornecedorDTO.setCep(fornecedor.getCep());
        fornecedorDTO.setSenha(fornecedor.getSenha());
        fornecedorDTO.setIdentificador(fornecedor.getIdentificador());
        fornecedorDTO.setTipo(fornecedor.getTipo());
        return fornecedorDTO;
    }

    public List<FornecedorDTO> listarFornecedores() {
        ArrayList<FornecedorDTO> fornecedoresDTO = new ArrayList<FornecedorDTO>();
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();
        for (Fornecedor fornecedor : fornecedores) {
            fornecedoresDTO.add(converterDTO(fornecedor));
        }
        return fornecedoresDTO;

    }

    public void salvarFornecedor(FornecedorDTO fornecedorDto) {
        Fornecedor fornecedor = converterEntidade(fornecedorDto);
        fornecedorRepository.save(fornecedor);
    }

    public Optional<FornecedorDTO> buscarFornecedorPorID(Integer id) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
        if (!fornecedor.isEmpty()) {
            FornecedorDTO fornecedorDTO = converterDTO(fornecedor.get());
            return Optional.of(fornecedorDTO);
        }
        return Optional.empty();
    }


    public Optional<FornecedorDTO> deletarFornecedor(Integer id) {
        // Verificar se o fornecedor existe
        Optional<FornecedorDTO> fornecedorDto = buscarFornecedorPorID(id);
        if (!fornecedorDto.isEmpty()) {
            usuarioService.deletarUsuario(id);
            return fornecedorDto;
        }
        return Optional.empty();
    }


    public Optional<FornecedorDTO> atualizarFornecedorPorEmail(String email, FornecedorDTO dto) {
        // Verifica se o fornecedor com o email existe
        Optional<Fornecedor> fornecedorOpt = fornecedorRepository.findByEmail(email);
        if (fornecedorOpt.isPresent()) {
            Fornecedor fornecedor = fornecedorOpt.get();
            // Chama o método de atualização no repositório
            int registrosAtualizados = fornecedorRepository.atualizarFornecedorPorEmail(
                    email,
                    dto.getEndereco(),
                    dto.getCep(),
                    dto.getNumero(),
                    dto.getTelefone()
            );

            if (registrosAtualizados > 0) {
                // Atualiza os campos do fornecedor localmente
                fornecedor.setEndereco(dto.getEndereco());
                fornecedor.setCep(dto.getCep());
                fornecedor.setNumero(dto.getNumero());
                fornecedor.setTelefone(dto.getTelefone());
                FornecedorDTO fornecedorDTO = converterDTO(fornecedor);
                return Optional.of(fornecedorDTO);
            } else {
                return Optional.empty();

            }
        } else {
            return Optional.empty();
        }
    }


}
