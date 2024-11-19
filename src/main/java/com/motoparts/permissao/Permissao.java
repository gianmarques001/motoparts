package com.motoparts.permissao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Permissao {

    public Permissao() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private Integer codPermissao;

    @NotEmpty
    private String permissao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotEmpty Integer getCodPermissao() {
        return codPermissao;
    }

    public void setCodPermissao(@NotEmpty Integer codPermissao) {
        this.codPermissao = codPermissao;
    }

    public @NotEmpty String getPermissao() {
        return permissao;
    }

    public void setPermissao(@NotEmpty String permissao) {
        this.permissao = permissao;
    }
}
