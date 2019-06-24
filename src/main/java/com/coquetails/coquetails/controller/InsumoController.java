/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coquetails.coquetails.controller;

import com.coquetails.coquetails.dao.ManagerDao;
import com.coquetails.coquetails.model.Insumo;
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
    
    public void inserir(){
        ManagerDao.getCurrentInstance().insert(this.insumo);
        this.insumo = new Insumo();
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage("Insumo adicionado com sucesso"));
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
