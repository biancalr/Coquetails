/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coquetails.coquetails.controller;

import com.coquetails.coquetails.dao.ManagerDao;
import com.coquetails.coquetails.model.ItemPedido;
import com.coquetails.coquetails.model.Pedido;
import com.coquetails.coquetails.model.Usuario;
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
@ManagedBean(name = "pedidosController")
@SessionScoped
public class PedidoController  implements Serializable{
    
    private Pedido pedido;
    private Pedido selPedido;
    private ItemPedido item;
    
    @PostConstruct
    public void init(){
        this.pedido = new Pedido();
        this.item = new ItemPedido();
    }
    
    public void inserir(){
        ManagerDao.getCurrentInstance().insert(this.pedido);
        this.pedido = new Pedido();
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage("Pedido adicionado com sucesso"));
    }
    
    public void alterar(){
        ManagerDao.getCurrentInstance().update(this.selPedido);
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage("Pedido alterado com sucesso"));
    }
    
    public void remover(){
        ManagerDao.getCurrentInstance().delete(this.selPedido);
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage("Pedido removido com sucesso!"));
    }
    
    public List<Pedido> lerTudo(){
        return ManagerDao.getCurrentInstance().
                read("Select p from Pedido p", Usuario.class);
    }
    
    public List<Pedido> lerPedidosUsuario(String cpf){
        return ManagerDao.getCurrentInstance().
                read("Select u.pedidos from Usuario u where u.cpf='" + cpf + "'",
                        Pedido.class);
    }
    
    public void inserirItem(){
        this.selPedido.getItensSelecionados().add(item);
        this.item = new ItemPedido();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Pedido getSelPedido() {
        return selPedido;
    }

    public void setSelPedido(Pedido selPedido) {
        this.selPedido = selPedido;
    }

    public ItemPedido getItem() {
        return item;
    }

    public void setItem(ItemPedido item) {
        this.item = item;
    }
    
    
}
