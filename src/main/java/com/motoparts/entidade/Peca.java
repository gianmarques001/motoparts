package com.motoparts.entidade;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Peca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_peca")
    private Integer idPeca;

    @Min(value = 1, message = "O código deve ser preenchido")
    @Column(name = "cod_peca")
    @NotNull
    private Integer codPeca;

    @NotBlank(message = "O nome não pode está vazio")
    @Size(min = 5, max = 30, message = "O nome deve ter entre 5 e 30 caracteres")
    @Column(name = "nome_peca")
    private String nome;

    @NotBlank(message = "A marca não pode está vazia")
    @Size(min = 4, max = 20, message = "A marca deve ter entre 4 e 20 caracteres")
    @Column(name = "marca_peca")
    private String marca;

    @NotBlank(message = "A descrição não pode está vazia")
    @Size(min = 15, max = 100, message = "A descrição deve ter entre 15 e 100 caracteres")
    @Column(name = "descricao_peca")
    private String descricao;

    @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
    @Column(name = "quantidade_peca")
    @NotNull
    private Integer quantidade;

    @Min(1)
    @Column(name = "preco_peca")
    @NotNull
    private Double preco;

//    @ManyToOne
//    @JoinColumn(name = "usuario_id")
//    private Usuario usuario ;
//
//    public Usuario getUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//    }

    public Integer getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(Integer idPeca) {
        this.idPeca = idPeca;
    }

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
}

