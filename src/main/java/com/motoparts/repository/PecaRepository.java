package com.motoparts.repository;

import com.motoparts.entidade.Fornecedor;
import com.motoparts.entidade.Peca;
import com.motoparts.entidade.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PecaRepository extends JpaRepository<Peca, Integer> {

    //    List<Peca> findByUsuario(Usuario usuario);
    Optional<Peca> findByCodPeca(Integer codPeca);


    @Modifying
    @Transactional

    @Query("UPDATE Peca  p SET p.quantidade = :quantidade WHERE p.codPeca = :codPeca")
    int atualizarPecaPeloCod(@Param("codPeca") Integer codPeca, @Param("quantidade") Integer quantidade
    );


}
