package com.motoparts.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ClienteDTO {


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

    @NotNull(message = "Numero deve ser preenchido")
    private Integer numero;


    @NotEmpty(message = "Cep deve ser preenchido")
    private String cep;


    @NotEmpty(message = "Identificador deve ser preenchido")
    private String identificador;

    @NotNull(message = "Tipo deve ser preenchido")
    private Integer tipo;


    public @NotEmpty String getNome() {
        return nome;
    }

    public void setNome(@NotEmpty String nome) {
        this.nome = nome;
    }

    public @NotEmpty @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty @Email String email) {
        this.email = email;
    }

    public @NotEmpty @Size(min = 8, max = 16) String getSenha() {
        return senha;
    }

    public void setSenha(@NotEmpty @Size(min = 8, max = 16) String senha) {
        this.senha = senha;
    }

    public @NotEmpty(message = "Telefone deve ser preenchido") String getTelefone() {
        return telefone;
    }

    public void setTelefone(@NotEmpty(message = "Telefone deve ser preenchido") String telefone) {
        this.telefone = telefone;
    }

    public @NotEmpty(message = "Endereco deve ser preenchido") String getEndereco() {
        return endereco;
    }

    public void setEndereco(@NotEmpty(message = "Endereco deve ser preenchido") String endereco) {
        this.endereco = endereco;
    }

    public @NotNull(message = "Numero deve ser preenchido") Integer getNumero() {
        return numero;
    }

    public void setNumero(@NotNull(message = "Numero deve ser preenchido") Integer numero) {
        this.numero = numero;
    }

    public @NotEmpty(message = "Cep deve ser preenchido") String getCep() {
        return cep;
    }

    public void setCep(@NotEmpty(message = "Cep deve ser preenchido") String cep) {
        this.cep = cep;
    }

    public @NotEmpty(message = "Identificador deve ser preenchido") String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(@NotEmpty(message = "Identificador deve ser preenchido") String identificador) {
        this.identificador = identificador;
    }

    public @NotNull(message = "Tipo deve ser preenchido") Integer getTipo() {
        return tipo;
    }

    public void setTipo(@NotNull(message = "Tipo deve ser preenchido") Integer tipo) {
        this.tipo = tipo;
    }
}
