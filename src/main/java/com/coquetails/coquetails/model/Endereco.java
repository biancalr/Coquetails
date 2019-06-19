/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coquetails.coquetails.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;

/**
 *
 * @author bibil
 */
@Embeddable
public class Endereco implements Serializable {

    @NotBlank
    protected String cep;

    @NotBlank
    protected String estado;

    @NotBlank
    protected String cidade;

    @NotBlank
    protected String bairro;

    @NotBlank
    protected String logradouro;
    
    protected String complemento;

    @NotNull
    @Min(value = 1)
    @Max(value = 9999)
    protected Integer numero;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    
    
}
