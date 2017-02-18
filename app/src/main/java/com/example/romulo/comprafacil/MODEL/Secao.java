package com.example.romulo.comprafacil.MODEL;

import java.io.Serializable;

/**
 * Created by Marques de Souza on 17/02/2017.
 */

public class Secao implements Serializable {
    private String cod_secao;
    private String nome_secao;

    public String getCod_secao() {
        return cod_secao;
    }

    public void setCod_secao(String cod_secao) {
        this.cod_secao = cod_secao;
    }

    public String getNome_secao() {
        return nome_secao;
    }

    public void setNome_secao(String nome_secao) {
        this.nome_secao = nome_secao;
    }
    @Override
    public  String toString(){
        return getCod_secao()+"-"+getNome_secao();
    }
}
