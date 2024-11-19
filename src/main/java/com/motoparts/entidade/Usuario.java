package com.motoparts.entidade;

import com.motoparts.permissao.Permissao;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(name = "nome_usuario")
    private String nome;

    @NotEmpty
    @Email
    @Column(unique = true, name = "email_usuario")
    private String email;

    @NotEmpty
    @Size(min = 8, max = 16)
    @Column(name = "senha_usuario")
    private String senha;


    @OneToOne
    @JoinColumn(name = "codPermissao", referencedColumnName = "id")
    private Permissao codPermissao;


//    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Peca> pecas;


//    public List<Peca> getPecas() {
//        return pecas;
//    }
//
//    public void setPecas(List<Peca> pecas) {
//        this.pecas = pecas;
//    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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



    public Permissao getCodPermissao() {
        return codPermissao;
    }

    public void setCodPermissao(Permissao codPermissao) {
        this.codPermissao = codPermissao;
    }
}
