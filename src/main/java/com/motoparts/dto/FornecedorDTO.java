package com.motoparts.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FornecedorDTO {


    @Email
    @NotEmpty(message = "Email deve ser preenchido")
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "Senha deve ser preenchido")
    @Size(min = 8, max = 16)
    private String senha;

    @NotEmpty(message = "Nome deve ser preenchido")
    private String nome;


    @NotEmpty(message = "Telefone deve ser preenchido")
    private String telefone;


    @NotEmpty(message = "Endereco deve ser preenchido")
    private String endereco;

    @NotNull (message = "Numero deve ser preenchido")
    private Integer numero;


    @NotEmpty(message = "Cep deve ser preenchido")
    private String cep;


    @NotEmpty(message = "Identificador deve ser preenchido")
    private String identificador;

    @NotNull(message = "Tipo deve ser preenchido")
    private Integer tipo;


    public @Email @NotEmpty String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotEmpty String email) {
        this.email = email;
    }

    public @NotEmpty @Size(min = 8, max = 16) String getSenha() {
        return senha;
    }

    public void setSenha(@NotEmpty @Size(min = 8, max = 16) String senha) {
        this.senha = senha;
    }

    public @NotEmpty String getNome() {
        return nome;
    }

    public void setNome(@NotEmpty    String nome) {
        this.nome = nome;
    }

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

    public @NotNull  Integer getNumero() {
        return numero;
    }

    public void setNumero(@NotNull  Integer numero) {
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

    public void setTipo(@NotNull  Integer tipo) {
        this.tipo = tipo;
    }
}
