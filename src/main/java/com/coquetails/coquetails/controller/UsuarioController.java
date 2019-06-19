/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coquetails.coquetails.controller;

import com.coquetails.coquetails.dao.ManagerDao;
import com.coquetails.coquetails.model.Cartao;
import com.coquetails.coquetails.model.Endereco;
import com.coquetails.coquetails.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import net.bootsfaces.component.tabView.TabView;

/**
 *
 * @author bibil
 */
@ManagedBean(name = "usuariosController")
@SessionScoped
public class UsuarioController implements Serializable {

    private Usuario usuario;
    private Usuario selUsuario;
    private Endereco endereco;
    private Cartao cartao;

    private double mainTabControl = 0;
    private int createtabControl = 0;

    @PostConstruct
    public void init() {
        this.cartao = new Cartao();
        this.endereco = new Endereco();
        this.usuario = new Usuario();
    }

    public String inserir(Usuario cadUsuario, String confirma) {
        if (!cadUsuario.getSenha().equals(confirma)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Senha e Confirmação devem ser iguais"));
            return null;
        }
        ManagerDao.getCurrentInstance().insert(this.usuario);
        this.usuario = new Usuario();
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage("Usuário inserido com sucesso"));
        return "loginUsuario.xhtml";
    }

    public void alterar() {
        ManagerDao.getCurrentInstance().update(this.selUsuario);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário alterado com sucesso!"));
    }

    public List<Usuario> lerTudo() {
        return ManagerDao.getCurrentInstance().
                read("Select u from Usuario u", Usuario.class);
    }
    
    public void removerCartao(){
        this.selUsuario.removerCartao(cartao);
        this.cartao = new Cartao();
    }

    public void inserirCartao() {
        this.selUsuario.addCartao(cartao);
        this.cartao = new Cartao();
    }
    
    public void removerEndereco(){
        this.selUsuario.setEndereco(null);
        this.endereco = new Endereco();
    }

    public void inserirEndereco() {
        this.selUsuario.setEndereco(endereco);
        this.endereco = new Endereco();
    }

    public Cartao existeCartao(String numero) {
        this.cartao = new Cartao();
        try {
            for (Cartao c : usuario.getCartoes()) {
                if (c.getNumero().equals(numero)) {
                    cartao = c;
                    break;
                }
            }
            return this.cartao;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public void deletar() {
        ManagerDao.getCurrentInstance().delete(this.selUsuario);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário removido com sucesso!"));
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getSelUsuario() {
        return selUsuario;
    }

    public void setSelUsuario(Usuario selUsuario) {
        this.selUsuario = selUsuario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public double getMainTabControl() {
        return mainTabControl;
    }

    public void setMainTabControl(double mainTabControl) {
        this.mainTabControl = mainTabControl;
    }

    public int getCreatetabControl() {
        return createtabControl;
    }

    public void setCreatetabControl(int createtabControl) {
        this.createtabControl = createtabControl;
    }

    public void nextTabCreate() {
        TabView tab = (TabView) FacesContext.getCurrentInstance().
                getViewRoot().findComponent("formCreateUsuario:mainTab");
        mainTabControl += 0.5;
        tab.setActiveIndex((int) mainTabControl);
    }

    public void previousCreateTab() {
        TabView tab = (TabView) FacesContext.getCurrentInstance().getViewRoot().findComponent("formCreateSelecao:mainTab");

        mainTabControl -= 0.5;

        tab.setActiveIndex((int) mainTabControl);
    }

}
