/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coquetails.coquetails.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author bibil
 */
@Entity
@Table(uniqueConstraints = 
        @UniqueConstraint(columnNames = "CPF", name = "cpf_uk"))
@Access(AccessType.FIELD)
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @NotBlank
    @CPF
    protected String cpf;

    @NotBlank
    @Size(min = 4)
    protected String nome;

    @NotBlank
    @Email
    protected String email;

    @NotBlank
    @Size(min = 4)
    protected String login;

    @NotBlank
    @Size(min = 6)
    protected String senha;

    @NotBlank
    @Size(min = 8)
    protected String telefone1;

    @Size(min = 8)
    protected String telefone2;

    @Embedded
    protected Endereco endereco;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID",
            nullable = true)
    protected List<Cartao> cartoes;

    @OneToMany(mappedBy = "criador", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = false)
    protected List<Coquetel> coqueteisCriados;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    protected List<Pedido> pedidos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Cartao> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<Cartao> cartoes) {
        this.cartoes = cartoes;
    }

    public List<Coquetel> getCoqueteisCriados() {
        return coqueteisCriados;
    }

    public void setCoqueteisCriados(List<Coquetel> coqueteisCriados) {
        this.coqueteisCriados = coqueteisCriados;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public boolean temPedido() {
        return !this.pedidos.isEmpty();
    }

    public void addPedido(Pedido p) {
        if (this.pedidos == null) {
            this.pedidos = new ArrayList<>();
        }
        p.setUsuario(this);
        this.pedidos.add(p);

    }

    public boolean removerPedido(Pedido p) {
        return this.pedidos.remove(p);
    }

    public boolean temCartao() {
        return !this.cartoes.isEmpty();
    }

    public void addCartao(Cartao c) {
        if (this.cartoes == null) {
            this.cartoes = new ArrayList<>();
        }
        this.cartoes.add(c);
    }
    
    public boolean removerCartao(Cartao c){
        return this.cartoes.remove(c);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        return this.id == other.id;
    }

}
