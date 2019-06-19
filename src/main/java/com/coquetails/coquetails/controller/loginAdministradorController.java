/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coquetails.coquetails.controller;

import com.coquetails.coquetails.dao.ManagerDao;
import com.coquetails.coquetails.model.Administrador;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author bibil
 */
@ManagedBean
@SessionScoped
public class loginAdministradorController implements Serializable{

    private Administrador admnistradorLogado = null;
    
    public String realizarLoginAdministrador(String login, String senha){
        
        String query = "select a from Administrador a where a.login=\""+login+"\" and "
                + "a.senha=\""+senha+"\"";
        
        this.admnistradorLogado = this.findAdministrador(query);
        
        if(this.admnistradorLogado==null){
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"Problema!",
                    "O login ou a senha do administrador está incorreto"));
            
            return null;
        }
        
        return "indexAdministrador.xhtml";
    }

    private Administrador findAdministrador(String query) {
        try {
            return (Administrador)ManagerDao.getCurrentInstance().read(query, Administrador.class).get(0);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("com.coquetails.coquetails.controller.loginAdministradorController.findAdministrador()");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Administrador getAdmnistradorLogado() {
        return admnistradorLogado;
    }

    public void setAdmnistradorLogado(Administrador admnistradorLogado) {
        this.admnistradorLogado = admnistradorLogado;
    }
    
    public void logout(){
        this.admnistradorLogado = null;
    }
    
    
}
