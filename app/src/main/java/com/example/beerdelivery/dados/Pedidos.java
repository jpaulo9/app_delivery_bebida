package com.example.beerdelivery.dados;

import android.graphics.Bitmap;

public class Pedidos {

    int idUser, idBebidas, unidades;
    int imagem;
    String nome, status;
    double valorApagar;



    public Pedidos(){

    }

    public Pedidos(int idUser, int idBebidas, int unidades, String nome, double valorApagar, int imagem, String status) {
        this.idUser = idUser;
        this.idBebidas = idBebidas;
        this.unidades = unidades;
        this.nome = nome;
        this.valorApagar = valorApagar;
        this.imagem = imagem;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdBebidas() {
        return idBebidas;
    }

    public void setIdBebidas(int idBebidas) {
        this.idBebidas = idBebidas;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }


    public double getValorApagar() {
        return valorApagar;
    }

    public void setValorApagar(double valorApagar) {
        this.valorApagar = valorApagar;
    }
}
