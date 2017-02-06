package com.example.romulo.comprafacil.MODEL;

import java.io.Serializable;

/**
 * Created by Romulo on 01/02/2017.
 */

public class Produto implements Serializable {
    private String cod_pro;
    private String nome_pro;
    private String localizacao_pro;

    public void setLocalizacao_pro(String localizacao_pro) {
        this.localizacao_pro = localizacao_pro;
    }

    public String getCod_pro() {
        return cod_pro;
    }

    public void setCod_pro(String cod_pro) {
        this.cod_pro = cod_pro;
    }

    public String getNome_pro() {
        return nome_pro;
    }

    public void setNome_pro(String nome_pro) {
        this.nome_pro = nome_pro;
    }

    public String getLocalizacao_pro() {
        return localizacao_pro;
    }

    @Override
    public String toString(){return "Codigo:"+getCod_pro()+"\nNome:"+getNome_pro()+"\nSeção:"+getLocalizacao_pro();}
}
