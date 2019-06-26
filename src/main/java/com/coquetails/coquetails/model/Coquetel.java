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
import javax.persistence.CollectionTable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author bibil
 */
@Entity
@DiscriminatorValue(value = "coquetel")
@Access(AccessType.FIELD)
public class Coquetel extends Bebida implements Serializable {
    
    /**
     * Informações extras sobre o modo de preparo e/ou os ingredientes
     */
    @ElementCollection
    @CollectionTable(name = "Comentario", 
            joinColumns = @JoinColumn(name = "id_usuario",
                    nullable = true))
    protected List<String> comentario;

    /**
     * Depende de quem criou o coquetel. Se o criador é um administrador
     * a variável recebe true, caso contrário, false.
     */
    @NotNull
    protected Boolean criacaoInterna;
    
    @OneToMany(mappedBy = "coquetel",cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = false)
    protected List<ItemCoquetel> itensCoquetel;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
             optional = true, targetEntity = Usuario.class)
    @JoinColumn(name = "id_cliente", referencedColumnName = "ID",
            nullable = true)
    protected Usuario criador;

    public Coquetel() {
        this.criacaoInterna = true;
    }

    public double getPrecoItensReceita() {
        Double valor = null;
        for (ItemCoquetel itemCoquetel : itensCoquetel) {
            valor += itemCoquetel.getCoquetel().getPreco();
        }
        return valor;
    }

    public List getComentario() {
        return comentario;
    }

    public void setComentario(List<String> comentario) {
        this.comentario = comentario;
    }
    
    public void addComentario(String comentario){
        if(this.comentario == null){
            this.comentario = new ArrayList<>();
        }
        this.comentario.add(comentario);
    }
    
    public boolean removeComentario(String comentario){
        return this.comentario.remove(comentario);
    }
    
    public void addItemCoquetel(ItemCoquetel itemCoquetel){
        if(this.itensCoquetel == null){
            this.comentario = new ArrayList<>();
        }
        itemCoquetel.setCoquetel(this);
        this.itensCoquetel.add(itemCoquetel);
    }
    
    public boolean removeComentario(ItemCoquetel itemCoquetel){
        return this.itensCoquetel.remove(itemCoquetel);
    }

    public Boolean getCriacaoInterna() {
        return criacaoInterna;
    }

    public void setCriacaoInterna(Boolean criacaoInterna) {
        this.criacaoInterna = criacaoInterna;
    }

    public Usuario getCriador() {
        return criador;
    }

    public void setCriador(Usuario criador) {
        this.criador = criador;
    }

}
