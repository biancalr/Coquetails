/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coquetails.coquetails.composites;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author bibil
 */
@ManagedBean
public class createObjectBean {

    public void createObjectBean(String varClass, String varName, String varScope) {
        Class classe = null;
        try {
            classe = Class.forName(varClass);
        } catch (ClassNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("A classe passada nao existe;"));
            System.out.println(ex.getMessage());
            return;
        }

        Object objeto = null;

        for (Constructor c : classe.getConstructors()) {
            if (c.getParameterCount() == 0) {
                try {
                    objeto = c.newInstance();
                } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | InvocationTargetException e) {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage("Não foi possível criar o objeto"));
                    System.out.println(e.getMessage());
                    return;
                }
            }
        }

        if (objeto == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Não existe um construtor vazio"));

            return;
        }

        if (varScope.equalsIgnoreCase("session")) {
            FacesContext.getCurrentInstance().getExternalContext().
                    getSessionMap().put(varName, objeto);
        }

    }

    
    
}
