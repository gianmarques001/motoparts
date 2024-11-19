package com.motoparts.entidade;

import com.motoparts.permissao.Permissao;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Cliente extends Usuario {


    @NotEmpty
    @Column(name = "telefone_cliente")
    private String telefone;

    @NotEmpty
    @Column(name = "endereco_cliente")
    private String endereco;

    @NotNull
    @Column(name = "numero_cliente")
    private Integer numero;

    @NotEmpty
    @Column(name = "cep_cliente")
    private String cep;

    @NotEmpty
    @Column(name = "ide_cliente")
    private String identificador;

    @NotNull
    @Column(name = "tipo_cliente")
    private Integer tipo; // Trocar para enum ou int

//    @OneToMany(mappedBy = "usuario")
//    private List<Peca> pecas;
//
//
//    public List<Peca> getPecas() {
//        return pecas;
//    }
//
//    public void setPecas(List<Peca> pecas) {
//        this.pecas = pecas;
//    }

    public @NotEmpty String getTelefone() {
        return telefone;
    }

    public void setTelefone(@NotEmpty String telefone) {
        this.telefone = telefone;
    }

    public @NotEmpty String getEndereco() {
        return endereco;
    }

    public void setEndereco(@NotEmpty String endereco) {
        this.endereco = endereco;
    }

    public @NotNull Integer getNumero() {
        return numero;
    }

    public void setNumero(@NotNull Integer numero) {
        this.numero = numero;
    }

    public @NotEmpty String getCep() {
        return cep;
    }

    public void setCep(@NotEmpty String cep) {
        this.cep = cep;
    }

    public @NotEmpty String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(@NotEmpty String identificador) {
        this.identificador = identificador;
    }

    public @NotNull Integer getTipo() {
        return tipo;
    }

    public void setTipo(@NotNull Integer tipo) {
        this.tipo = tipo;
    }
}


