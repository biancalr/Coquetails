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
public enum UnidadeGarrafa {
    v300mL("300mL"),
    v350mL("350mL"),
    v600mL("600mL"),
    v1L("1Litro"),
    v15L("1,5Litro"),
    v2L("2Litros");
    
    private String volume;
    private UnidadeGarrafa() {
    }

    private UnidadeGarrafa(String volume) {
        this.volume = volume;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public static UnidadeGarrafa getV300mL() {
        return v300mL;
    }

    public static UnidadeGarrafa getV350mL() {
        return v350mL;
    }

    public static UnidadeGarrafa getV600mL() {
        return v600mL;
    }

    public static UnidadeGarrafa getV1L() {
        return v1L;
    }

    public static UnidadeGarrafa getV15L() {
        return v15L;
    }

    public static UnidadeGarrafa getV2L() {
        return v2L;
    }

    @Override
    public String toString() {
        String sb = "Volume da garrafa: " + this.volume;
        return sb;
    }
    
}
