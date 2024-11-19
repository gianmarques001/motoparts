package com.motoparts.repository;

import com.motoparts.entidade.Cliente;

import com.motoparts.entidade.Fornecedor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Optional<Cliente> findByEmail(String email);


    @Modifying
    @Transactional
    @Query("UPDATE Cliente c SET c.endereco = :endereco, c.cep = :cep, c.numero = :numero, c.telefone = :telefone WHERE c.email = :email")
    int atualizarClientePorEmail(@Param("email") String email,
                                    @Param("endereco") String endereco,
                                    @Param("cep") String cep,
                                    @Param("numero") Integer numero,
                                    @Param("telefone") String telefone);

}
