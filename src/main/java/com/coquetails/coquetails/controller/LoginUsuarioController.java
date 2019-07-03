/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coquetails.coquetails.controller;

import com.coquetails.coquetails.dao.ManagerDao;
import com.coquetails.coquetails.model.Usuario;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author bibil
 */
@ManagedBean(name = "loginUsuarioController")
@SessionScoped
public class LoginUsuarioController {
    private Usuario usuarioLogado = null;

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
    
    public String realizarLoginUsuario(String login, String senha){
        String query = "select u from Usuario u where"
                + "u.login=\""+login+" and u.senha=\""+senha+"\"";
        
        this.usuarioLogado = this.findUsuario(query);
        
        if(this.usuarioLogado == null){
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"Houston, we have a problem!",
                    "O login ou a senha do usuario está incorreto ou usuario não existe"));
            
            return null;
        }
        
        return "indexUsuario.xhtml";
    }

    private Usuario findUsuario(String query) {
        try {
            return (Usuario)ManagerDao.getCurrentInstance().read(query, Usuario.class).get(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("com.coquetails.coquetails.controller.LoginUsuarioController.findUsuario()");
            System.out.println("Message: " + e.getMessage());
            return null;
        }
    }
    
    public void logout(){
        this.usuarioLogado = null;
    }
    
}
