/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coquetails.coquetails.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author bibil
 */
@Entity
@Table
@Access(AccessType.FIELD)
public class ItemCoquetel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String nome;

    @NotNull
    @DecimalMin(value = "0.0")
    private double preco;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            optional = false)
    @JoinColumn(name = "id_coquetel", referencedColumnName = "ID",
            nullable = false)
    private Coquetel coquetel;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            orphanRemoval = false, optional = false)
    private Insumo insumo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public Coquetel getCoquetel() {
        return coquetel;
    }

    public void setCoquetel(Coquetel coquetel) {
        this.coquetel = coquetel;
    }
    
    public static double truncar(double numero) {
        int n = (int) (numero * Math.pow(10.0, 2));
        numero = n / Math.pow(10.0, 2);
        return numero;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final ItemCoquetel other = (ItemCoquetel) obj;
        return this.id == other.id;
    }
    
    

}
