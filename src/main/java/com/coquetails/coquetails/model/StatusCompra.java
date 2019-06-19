/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coquetails.coquetails.model;

/**
 *
 * @author bibil
 */
public enum StatusCompra {
    APROVADO(1), NEGADO(0);
    
    public int valorStatus;
    private StatusCompra() {
        
    }
    
    StatusCompra(int valor){
        valorStatus = valor;
    }

    public void setValorStatus(int valorStatus) {
        this.valorStatus = valorStatus;
    }

    public int getValorStatus() {
        return valorStatus;
    }

    public static StatusCompra getAPROVADO() {
        return APROVADO;
    }

    public static StatusCompra getNEGADO() {
        return NEGADO;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
    
    
}
