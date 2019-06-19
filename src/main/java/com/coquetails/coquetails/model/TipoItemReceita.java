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
public enum TipoItemReceita {
    FRUTA("fruta"),
    SUCO("suco"),
    BEBIDA("bebida"),
    EXTRA("extra");
    
    private String tipoItemReceita;
    private TipoItemReceita() {
        
    }
    
    private TipoItemReceita(String tipoItemReceita) {
        this.tipoItemReceita = tipoItemReceita;
    }

    public String getTipoItemReceita() {
        return tipoItemReceita;
    }

    public void setTipoItemReceita(String tipoItemReceita) {
        this.tipoItemReceita = tipoItemReceita;
    }

    public static TipoItemReceita getEXTRA() {
        return EXTRA;
    }

    public static TipoItemReceita getFRUTA() {
        return FRUTA;
    }

    public static TipoItemReceita getSUCO() {
        return SUCO;
    }

    public static TipoItemReceita getBEBIDA() {
        return BEBIDA;
    }

    @Override
    public String toString() {
        String sb = "Tipo do Item: " + tipoItemReceita;
        return sb;
    }
    
}
