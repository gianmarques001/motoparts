package com.motoparts.entidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
public class Fornecedor extends Usuario {

//    @Email
//    @NotEmpty
//    @Column(unique = true, name = "email_fornecedor")
//    private String email;
//
//    @NotEmpty
//    @Size(min = 8, max = 16)
//    @Column(name = "senha_fornecedor")
//    private String senha;


//    @NotEmpty
//    @Column(name = "razao_fornecedor")
//    private String razaoSocial;

    @NotEmpty
    @Column(name = "telefone_fornecedor")
    private String telefone;

    @NotEmpty
    @Column(name = "endereco_fornecedor")
    private String endereco;

    @NotNull
    @Column(name = "numero_fornecedor")
    private Integer numero;

    @NotEmpty
    @Column(name = "cep_fornecedor")
    private String cep;

    @NotEmpty
    @Column(name = "ide_fornecedor")
    private String identificador;

    @NotNull
    @Column(name = "tipo_fornecedor")
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

//
//    public @Email @NotEmpty String getEmail() {
//        return email;
//    }
//
//    public void setEmail(@Email @NotEmpty String email) {
//        this.email = email;
//    }
//
//    public @NotEmpty @Size(min = 8, max = 16) String getSenha() {
//        return senha;
//    }
//
//    public void setSenha(@NotEmpty @Size(min = 8, max = 16) String senha) {
//        this.senha = senha;
//    }
//
//    public @NotEmpty String getRazaoSocial() {
//        return razaoSocial;
//    }
//
//    public void setRazaoSocial(@NotEmpty String razaoSocial) {
//        this.razaoSocial = razaoSocial;
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
