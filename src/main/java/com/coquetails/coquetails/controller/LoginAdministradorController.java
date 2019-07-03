/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coquetails.coquetails.controller;

import com.coquetails.coquetails.dao.ManagerDao;
import com.coquetails.coquetails.model.Administrador;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author bibil
 */
@ManagedBean(name = "loginAdministradorController")
@SessionScoped
public class LoginAdministradorController {
    
    private Administrador administradorLogado = null;

    public Administrador getAdministradorLogado() {
        return administradorLogado;
    }

    public void setAdministradorLogado(Administrador administradorLogado) {
        this.administradorLogado = administradorLogado;
    }
    
    public String realizarLoginAdministrador(String login, String senha){
        String query = "select a from Administrador a where"
                + "a.login=\"" + login + " and a.senha=\"" + senha + "\"";
        this.administradorLogado = this.findAdministrador(query);
        if(this.administradorLogado == null){
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"Houston, we have a problem!",
                    "O login ou a senha do coordenador está incorreto ou administrador não existe"));
            return null;
        }
        return "indexAdministrador.xhtml";
    }

    private Administrador findAdministrador(String query) {
        try {
            return (Administrador)ManagerDao.getCurrentInstance().read(query, Administrador.class).get(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("com.coquetails.coquetails.controller.LoginAdministradorController.findAdministrador()");
            System.out.println("Message: " + e.getMessage());
            return null;
        }
    }
    
    public String addMensagemAlerta(){
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage("Espertinhos não passarão"));
        return "loginCoordenador.xhtml";
    }
    
    public void logout(){
        this.administradorLogado = null;
    }
    
}
