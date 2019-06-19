/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coquetails.coquetails.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author bibil
 */
@Entity
@Table
@Access(AccessType.FIELD)
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataHora;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private StatusCompra statusCompra;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            optional = true, targetEntity = Usuario.class)
    @JoinColumn(name = "id_pedido", referencedColumnName = "ID",
            nullable = false)
    private Usuario usuario;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            orphanRemoval = true, mappedBy = "pedido")
    private List<ItemPedido> itensSelecionados;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public StatusCompra getStatusCompra() {
        return statusCompra;
    }

    public void setStatusCompra(StatusCompra statusCompra) {
        this.statusCompra = statusCompra;
    }

    public List<ItemPedido> getItensSelecionados() {
        return itensSelecionados;
    }

    public void setItensSelecionados(List<ItemPedido> itensSelecionados) {
        this.itensSelecionados = itensSelecionados;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Coleta o preco de cada item do pedido, soma e retorna o valor resultante
     * @return o valor total do pedido
     */
    public double getTotalPreco() {
        Double total = null;
        for (ItemPedido itemSelecionado : itensSelecionados) {
            total += itemSelecionado.getSubtotalPreco();
        }
        return total;
    }
    
    

}
