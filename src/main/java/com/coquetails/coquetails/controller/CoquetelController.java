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
import com.coquetails.coquetails.model.UnidadeGarrafa;
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
    private Insumo insumo;
    private Insumo selInsumo;
    private String comentario;
    private UnidadeGarrafa unidadeGarrafa;
    
    @PostConstruct
    public void init() {
        this.coquetel = new Coquetel();
        this.itemCoquetel = new ItemCoquetel();
        this.insumo = new Insumo();
        this.comentario = null;
        this.unidadeGarrafa = null;
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
        ManagerDao.getCurrentInstance().insert(cadCoquetel);
        this.coquetel = new Coquetel();
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage("Coquetel inserido com sucesso"));
        return "apresentarCoqueteis.xhtml";
    }
    
    public String inserir(Coquetel cadCoquetel, int unidadeGarrafa, boolean criacaoInterna){
        cadCoquetel.setCriacaoInterna(criacaoInterna);
        cadCoquetel.setUnidadeGarrafa(addUnidadeGarrafa(unidadeGarrafa));
        ManagerDao.getCurrentInstance().insert(cadCoquetel);
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage("Coquetel inserido com sucesso"));
        return "apresentarCoqueteis.xhtml";
    }
    
    public void inserirInsumoItem(ItemCoquetel cadItemCoquetel, Insumo insumo){
        cadItemCoquetel.setInsumo(insumo);
    }
    
    public void inserirItemCoquetel(Coquetel cadCoquetel, ItemCoquetel cadItemCoquetel){
        cadCoquetel.addItemCoquetel(cadItemCoquetel);
    }
    
    public void removerItemCoquetel(Coquetel cadCoquetel, ItemCoquetel cadItemCoquetel){
        cadCoquetel.removeItemCoquetel(itemCoquetel);
    }
    
    public void inserirComentario(Coquetel cadCoquetel, String comentario){
        cadCoquetel.addComentario(comentario);
    }
    
    public void removerComentario(Coquetel cadCoquetel, String instrucao){
        cadCoquetel.removeComentario(instrucao);
    }
    
    public List<String> lerInstrucoesCoquetel(Coquetel cadCoquetel){
        return cadCoquetel.getComentario();
    }
    
    public void alterarComentario(Coquetel cadCoquetel){
        
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public UnidadeGarrafa getUnidadeGarrafa() {
        return unidadeGarrafa;
    }

    public void setUnidadeGarrafa(UnidadeGarrafa unidadeGarrafa) {
        this.unidadeGarrafa = unidadeGarrafa;
    }

    private UnidadeGarrafa addUnidadeGarrafa(int unidadeGarrafa) {
        switch(unidadeGarrafa){
            case 1:
                return UnidadeGarrafa.v300mL;
            case 2:
                return UnidadeGarrafa.v350mL;
            case 3:
                return UnidadeGarrafa.v600mL;
            case 4:
                return UnidadeGarrafa.v1L;
            case 5:
                return UnidadeGarrafa.v15L;//1,5 Litro
            case 6:
                return UnidadeGarrafa.v2L;
            default:
                return UnidadeGarrafa.v300mL; //menor valor de unidade possível
        }
    }
    
}
