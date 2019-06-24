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
public enum TipoInsumo {
    FRUTA("fruta"),
    SUCO("suco"),
    BEBIDA("bebida"),
    EXTRA("extra");
    
    private String tipoItemReceita;
    private TipoInsumo() {
        
    }
    
    private TipoInsumo(String tipoItemReceita) {
        this.tipoItemReceita = tipoItemReceita;
    }

    public String getTipoItemReceita() {
        return tipoItemReceita;
    }

    public void setTipoItemReceita(String tipoItemReceita) {
        this.tipoItemReceita = tipoItemReceita;
    }

    public static TipoInsumo getEXTRA() {
        return EXTRA;
    }

    public static TipoInsumo getFRUTA() {
        return FRUTA;
    }

    public static TipoInsumo getSUCO() {
        return SUCO;
    }

    public static TipoInsumo getBEBIDA() {
        return BEBIDA;
    }

    @Override
    public String toString() {
        String sb = "Tipo do Item: " + tipoItemReceita;
        return sb;
    }
    
}
