package com.motoparts.service;

import com.motoparts.dto.FornecedorDTO;
import com.motoparts.entidade.Fornecedor;
import com.motoparts.entidade.Peca;
import com.motoparts.dto.PecaDTO;
import com.motoparts.entidade.Usuario;
import com.motoparts.repository.PecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PecaService {

    @Autowired
    private PecaRepository pecaRepository;

    private PecaDTO converterDTO(Peca peca) {

        PecaDTO pecaDTO = new PecaDTO();
        pecaDTO.setId(peca.getIdPeca());
        pecaDTO.setNome(peca.getNome());
        pecaDTO.setCodPeca(peca.getCodPeca());
        pecaDTO.setMarca(peca.getMarca());
        pecaDTO.setDescricao(peca.getDescricao());
        pecaDTO.setQuantidade(peca.getQuantidade());
        pecaDTO.setPreco(peca.getPreco());
        return pecaDTO;
    }

    private Peca converterEntidade(PecaDTO pecaDto) {
        Peca peca = new Peca();
        peca.setNome(pecaDto.getNome());
        peca.setCodPeca(pecaDto.getCodPeca());
        peca.setMarca(pecaDto.getMarca());
        peca.setDescricao(pecaDto.getDescricao());
        peca.setQuantidade(pecaDto.getQuantidade());
        peca.setPreco(pecaDto.getPreco());
        return peca;
    }


    // Pegar todas as Peças
    public List<PecaDTO> listarPecas() {
        ArrayList<PecaDTO> pecasDTO = new ArrayList<PecaDTO>();
        List<Peca> pecas = pecaRepository.findAll();
        for (Peca peca : pecas) {
            pecasDTO.add(converterDTO(peca));
        }
        return pecasDTO;
    }

    // Salvar Peça
    public void salvarPeca(PecaDTO pecaDto) {
        Peca peca = converterEntidade(pecaDto);
       // peca.setUsuario(new Usuario());
        pecaRepository.save(peca);
    }



    // Salvar Peça - Fornecedor
//    public void salvarPeca(PecaDTO pecaDto, Usuario usuario) {
//        Peca peca = converterEntidade(pecaDto);
//        peca.setUsuario(usuario);
//        pecaRepository.save(peca);
//    }

    // Buscar Peça por ID
    public Optional<PecaDTO> buscarPecaPorID(Integer id) {
        Optional<Peca> peca = pecaRepository.findById(id);
        if (!peca.isEmpty()) {
            PecaDTO pecaDTO = converterDTO(peca.get());
            return Optional.of(pecaDTO);
        }
        return Optional.empty();
    }

    // Deletar Peça
    public Optional<PecaDTO> deletarPeca(Integer id) {
        Optional<PecaDTO> peca = buscarPecaPorID(id);
        if (!peca.isEmpty()) {
            pecaRepository.deleteById(id);
            return peca;
        }
        return Optional.empty();
    }



    public Optional<PecaDTO> editarPeca(PecaDTO pecaDTO) {
        // Verifica se o fornecedor com o email existe
        Optional<Peca> pecaOpt = pecaRepository.findByCodPeca(pecaDTO.getCodPeca());

        if (pecaOpt.isPresent()) {
            Peca peca = pecaOpt.get();

            int codPeca =  pecaDTO.getCodPeca();
            int atualizar = pecaRepository.atualizarPecaPeloCod(
                    codPeca,
                    pecaDTO.getQuantidade()
            );

            if (atualizar > 0) {
                // Atualiza os campos do fornecedor localmente
                peca.setQuantidade(pecaDTO.getQuantidade());

                PecaDTO dto = converterDTO(peca);
                return Optional.of(dto);
            } else {
                return Optional.empty();

            }
        } else {
            return Optional.empty();
        }
    }







//    public List<PecaDTO> buscarPecaPorFornecedor(Usuario usuario){
//        List<PecaDTO> pecasDto = new ArrayList<>();
//       // List<Peca> pecas = pecaRepository.findByUsuario(usuario);
////        if (!pecas.isEmpty()) {
////            for (Peca peca : pecas) {
////                pecasDto.add(converterDTO(peca));
////            }
////        }
//        return pecasDto;
//
//    }

}
