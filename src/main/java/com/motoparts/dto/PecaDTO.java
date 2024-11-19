package com.motoparts.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PecaDTO {

    private Integer id;

    @NotBlank(message = "O nome não pode está vazio")
    @Size(min = 5, max = 30, message = "O nome deve ter entre 5 e 30 caracteres")
    private String nome;

    @Min(value = 1)
    @NotNull
    private Integer codPeca;

    @NotBlank(message = "A marca não pode está vazia")
    @Size(min = 4, max = 20, message = "A marca deve ter entre 4 e 20 caracteres")
    private String marca;

    @NotBlank(message = "A descrição não pode está vazia")
    @Size(min = 15, max = 100, message = "A descrição deve ter entre 15 e 100 caracteres")
    private String descricao;

    @Min(1)
    @NotNull
    private Integer quantidade;

    @Min(1)
    @NotNull
    private Double preco;


    public @NotBlank String getNome() {
        return nome;
    }

    public void setNome(@NotBlank String nome) {
        this.nome = nome;
    }

    public @NotBlank String getMarca() {
        return marca;
    }

    public void setMarca(@NotBlank String marca) {
        this.marca = marca;
    }

    public @NotBlank String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank String descricao) {
        this.descricao = descricao;
    }

    public @Min(1) Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(@Min(1) Integer quantidade) {
        this.quantidade = quantidade;
    }

    public @Min(1) Double getPreco() {
        return preco;
    }

    public void setPreco(@Min(1) Double preco) {
        this.preco = preco;
    }

    public @Min(1) Integer getCodPeca() {
        return codPeca;
    }

    public void setCodPeca(@Min(1) Integer codPeca) {
        this.codPeca = codPeca;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
