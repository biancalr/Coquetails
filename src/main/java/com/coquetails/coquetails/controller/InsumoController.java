/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coquetails.coquetails.controller;

import com.coquetails.coquetails.dao.ManagerDao;
import com.coquetails.coquetails.model.Insumo;
import com.coquetails.coquetails.model.TipoInsumo;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author bibil
 */
@ManagedBean(name = "insumosController")
@SessionScoped
public class InsumoController {
    private Insumo insumo;
    private Insumo selInsumo;
    
    @PostConstruct
    public void init(){
        this.insumo = new Insumo();
    }
    
    public String inserir(Insumo cadInsumo, int tipoInsumo){
        TipoInsumo tipo = addTipoInsumo(tipoInsumo);
        System.out.println("tipoInsumo " + tipo);
        cadInsumo.setTipoInsumo(tipo);
        ManagerDao.getCurrentInstance().insert(cadInsumo);
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage("Insumo adicionado com sucesso"));
        return "apresentarInsumos.xhtml";
    }
    
    public TipoInsumo addTipoInsumo(int tipoInsumo){
        TipoInsumo tipo;
        switch(tipoInsumo){
            case 1:
                tipo = TipoInsumo.getFRUTA();
                break;
            case 2:
                tipo = TipoInsumo.getSUCO();
                break;
            case 3:
                tipo = TipoInsumo.getBEBIDA();
                break;
            case 4:
                tipo =  TipoInsumo.getEXTRA();
                break;
            default:
                FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage("Tipo não reconhecido"));
                return null;
        }
        return tipo;
    }
    
    public String inserir(Insumo cadInsumo){
        ManagerDao.getCurrentInstance().insert(cadInsumo);
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage("Insumo adicionado com sucesso"));
        return "apresentarInsumos.xhtml";
    }
    
    public void alterar(int tipoInsumo){
        TipoInsumo tipo = addTipoInsumo(tipoInsumo);
        this.selInsumo.setTipoInsumo(tipo);
        ManagerDao.getCurrentInstance().update(this.selInsumo);
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage("Insumo alterado com sucesso"));
    }
    
    public void alterar(){
        ManagerDao.getCurrentInstance().update(this.selInsumo);
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage("Insumo alterado com sucesso"));
    }
    
    public void remover(){
        ManagerDao.getCurrentInstance().delete(this.selInsumo);
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage("Insumo removido com sucesso"));
    }
    
    public Insumo findInsumo(String id){
        try {
            return (Insumo)ManagerDao.getCurrentInstance().read(
                    "Select i From Insumo where i.id='" + id + "'",
                    Insumo.class).get(0);
        } catch (Exception e) {
            System.out.println("com.coquetails.coquetails.controller.InsumoController.findInsumo()");
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public List<Insumo> lerTudo(){
        return ManagerDao.getCurrentInstance().
                read("Select i from Insumo i", Insumo.class);
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public Insumo getSelInsumo() {
        return selInsumo;
    }

    public void setSelInsumo(Insumo selInsumo) {
        this.selInsumo = selInsumo;
    }
    
}
