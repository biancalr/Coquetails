/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coquetails.coquetails.controller;

import com.coquetails.coquetails.dao.ManagerDao;
import com.coquetails.coquetails.model.Coquetel;
import com.coquetails.coquetails.model.Insumo;
import com.coquetails.coquetails.model.ItemCoquetel;
import java.io.Serializable;
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
@ManagedBean(name = "coqueteisController")
@SessionScoped
public class CoquetelController implements Serializable{
    
    private Coquetel coquetel;
    private Coquetel selCoquetel;
    private ItemCoquetel itemCoquetel;
    private ItemCoquetel selItemCoquetel;
    
    @PostConstruct
    public void init() {
        this.coquetel = new Coquetel();
        this.itemCoquetel = new ItemCoquetel();
    }

    public Coquetel getCoquetel() {
        return coquetel;
    }

    public void setCoquetel(Coquetel coquetel) {
        this.coquetel = coquetel;
    }

    public Coquetel getSelCoquetel() {
        return selCoquetel;
    }

    public void setSelCoquetel(Coquetel selCoquetel) {
        this.selCoquetel = selCoquetel;
    }
    
    public String inserir(Coquetel cadCoquetel, boolean criacaoInterna){
        cadCoquetel.setCriacaoInterna(criacaoInterna);
        ManagerDao.getCurrentInstance().insert(this.coquetel);
        this.coquetel = new Coquetel();
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage("Coquetel inserido com sucesso"));
        return "apresentarCoqueteis.xhtml";
    }
    
    public void inserirInsumoItem(Object insumo){
        this.itemCoquetel.setInsumo((Insumo) insumo);
    }
    
    public void inserirItemCoquetel(Coquetel cadCoquetel){
        cadCoquetel.addItemCoquetel(itemCoquetel);
        this.itemCoquetel = new ItemCoquetel();
    }
    
    public void alterar(){
        ManagerDao.getCurrentInstance().update(this.selCoquetel);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Coquetel atualizado com sucesso!"));
    }
    
    public void remover(){
        ManagerDao.getCurrentInstance().delete(this.selCoquetel);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Coquetel removido com sucesso!"));
    }
    
    public List<Coquetel> lerTudo(){
        return ManagerDao.getCurrentInstance().read("Select c from Coquetel c", Coquetel.class);
    }
    
    public List<Coquetel> lerCriacoesInternas(){
        return ManagerDao.getCurrentInstance().
                read("Select c from Coquetel c where c.criacaoInterna='true'", 
                        Coquetel.class);
    }
    
    public List<Coquetel> lerCriacoesDoUsuario(){
        return ManagerDao.getCurrentInstance().
                read("Select c from Coquetel c where c.criacaoInterna='false'", 
                        Coquetel.class);
    }

    public ItemCoquetel getItemCoquetel() {
        return itemCoquetel;
    }

    public void setItemCoquetel(ItemCoquetel itemCoquetel) {
        this.itemCoquetel = itemCoquetel;
    }

    public ItemCoquetel getSelItemCoquetel() {
        return selItemCoquetel;
    }

    public void setSelItemCoquetel(ItemCoquetel selItemCoquetel) {
        this.selItemCoquetel = selItemCoquetel;
    }
    
}
