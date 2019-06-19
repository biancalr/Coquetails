/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coquetails.coquetails.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.coquetails.coquetails.model.Bebida;
import com.coquetails.coquetails.dao.ManagerDao;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


/**
 *
 * @author bibil
 */
@ManagedBean(name = "bebidasController")
@SessionScoped
public class BebidaController implements Serializable{
    
    private Bebida bebida;
    private Bebida selBebida;
    
    public void inserir(){
        ManagerDao.getCurrentInstance().insert(bebida);
        this.bebida = new Bebida();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Bebida inserida com sucesso!"));
    }
    
    public void alterar(){
        ManagerDao.getCurrentInstance().update(this.selBebida);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Bebida atualizada com sucesso!"));
    }
    
    public void deletar(){
        ManagerDao.getCurrentInstance().delete(this.selBebida);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Bebida removida com sucesso!"));
    }
    
    public List<Bebida> ler(){
        return ManagerDao.getCurrentInstance().read("Select b From Bebida b", Bebida.class);
    }

    public Bebida getBebida() {
        return bebida;
    }

    public void setBebida(Bebida bebida) {
        this.bebida = bebida;
    }

    public Bebida getSelBebida() {
        return selBebida;
    }

    public void setSelBebida(Bebida selBebida) {
        this.selBebida = selBebida;
    }
    
    
}
