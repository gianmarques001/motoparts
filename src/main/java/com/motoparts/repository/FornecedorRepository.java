package com.motoparts.repository;

import com.motoparts.dto.FornecedorDTO;
import com.motoparts.entidade.Fornecedor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {

    Optional<Fornecedor> findByEmail(String email);



    @Modifying
    @Transactional
    @Query("UPDATE Fornecedor f SET f.endereco = :endereco, f.cep = :cep, f.numero = :numero, f.telefone = :telefone WHERE f.email = :email")
    int atualizarFornecedorPorEmail(@Param("email") String email,
                                    @Param("endereco") String endereco,
                                    @Param("cep") String cep,
                                    @Param("numero") Integer numero,
                                    @Param("telefone") String telefone);
}
