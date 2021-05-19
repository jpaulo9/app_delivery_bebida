package com.example.beerdelivery.dados;

import android.graphics.Bitmap;

public class Bebidas {

    String nome, categoria;
    double preco;
    String Tipomedida;
    int idBe, quantidade, medida;
    int imagem;


    public Bebidas(){

    }

    public Bebidas(String nome, String categoria, double preco, int quantidade,
                   int idBe, String Tmedida, int medida, int bebida) {
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.quantidade = quantidade;
        this.Tipomedida = Tmedida;
        this.idBe = idBe;
        this.imagem = bebida;
        this.medida = medida;

    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public String getTipomedida() {
        return Tipomedida;
    }

    public void setTipomedida(String tipomedida) {
        Tipomedida = tipomedida;
    }

    public int getMedida() {
        return medida;
    }

    public void setMedida(int medida) {
        this.medida = medida;
    }



    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }


    public int getIdBe() {
        return idBe;
    }

    public void setIdBe(int idBe) {
        this.idBe = idBe;
    }


}
