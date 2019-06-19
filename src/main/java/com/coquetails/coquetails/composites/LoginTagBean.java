/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coquetails.coquetails.composites;

import com.coquetails.coquetails.annotations.GenericLoginController;
import com.coquetails.coquetails.annotations.GenericLoginMethod;
import com.coquetails.coquetails.annotations.GenericLoginParameter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author bibil
 */
@ManagedBean
public class LoginTagBean {
    
    private Class controller;
    private Object controllerObject;
    private String returnTo;
    private Method loginMethod;
    
    public void initLoginProgress(String controllerName, String returnTo){
        try {
            controller = Class.forName(controllerName);
        } catch (ClassNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Classe controller inexistente")); 
            System.out.println("com.coquetails.coquetails.composites.LoginTagBean.initLoginProgress()");
            System.out.println(ex.getMessage());
            return;
        }
        
        if(!this.controller.isAnnotationPresent(GenericLoginController.class)){
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Seu controlador não está anotado")); 
            return;
        }
        
        for(Method m : this.controller.getMethods()){
            if(m.getClass().isAnnotationPresent(GenericLoginMethod.class)){
                this.loginMethod = m;
                break;
            }
        }
        
        this.returnTo = returnTo;
        
        for(Constructor c:this.controller.getConstructors()){
            if(c.getParameterCount()==0){
                try {
                    this.controllerObject = c.newInstance();
                } catch (Exception ex) {
                    FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage("Problema ao criar o controlador")); 
                    return;
                } 
            }
        }
        
    }
    
    public String realizarLogin(String login, String senha){
        boolean isLoginFirst = true;
        
        Parameter loginP = null;
        
        if(this.loginMethod.getParameters()[0].getClass().
                isAnnotationPresent(GenericLoginParameter.class)){
            
           loginP = this.loginMethod.getParameters()[0];
            
           isLoginFirst = true; 
        }else{
            isLoginFirst = false;
            loginP = this.loginMethod.getParameters()[1];
        }
        
        if(isLoginFirst){
            
            try {
                this.loginMethod.invoke(controllerObject, login,senha);
            } catch (Exception ex) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage("Deu pau ao chamar o método de login")); 
                System.out.println(ex.getMessage());
                    return null;
            } 
            
        }else{
            try {
                this.loginMethod.invoke(controllerObject, senha,login);
            } catch (Exception ex) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage("Deu pau ao chamar o método de login"));
                System.out.println(ex.getMessage());
                    return null;
            } 
        }
        
        return this.returnTo;
    }
    
    
}
