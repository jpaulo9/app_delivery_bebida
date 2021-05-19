package com.example.beerdelivery.dados;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Usuario implements Serializable {

    String nome, email, senha, categ, nomeProd;
    int idUsr, idBebida, unidadeBebida;
    Bitmap fotoPerfil;
    double precoBebida;

    public Usuario(){

    }

    public Usuario(String nome, String email, String senha, int idUsr, Bitmap  bitmap, int idBebida, String cat, int unidadeBebida
    , double precoBebida, String nomeProd) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idUsr = idUsr;
        this.fotoPerfil = bitmap;
        this.idBebida = idBebida;
        this.categ = cat;
        this.unidadeBebida = unidadeBebida;
        this.precoBebida = precoBebida;
        this.nomeProd = nomeProd;


    }

    public String getNomeProd() {
        return nomeProd;
    }

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public int getUnidadeBebida() {
        return unidadeBebida;
    }

    public void setUnidadeBebida(int unidadeBebida) {
        this.unidadeBebida = unidadeBebida;
    }

    public double getPrecoBebida() {
        return precoBebida;
    }

    public void setPrecoBebida(double precoBebida) {
        this.precoBebida = precoBebida;
    }

    public String getCateg() {
        return categ;
    }

    public void setCateg(String categ) {
        this.categ = categ;
    }

    public int getIdBebida() {
        return idBebida;
    }

    public void setIdBebida(int idBebida) {
        this.idBebida = idBebida;
    }

    public Bitmap getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(Bitmap fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdUsr() {
        return idUsr;
    }

    public void setIdUsr(int idUsr) {
        this.idUsr = idUsr;
    }
}
