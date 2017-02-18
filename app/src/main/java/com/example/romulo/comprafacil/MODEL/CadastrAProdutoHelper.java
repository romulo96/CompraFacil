package com.example.romulo.comprafacil.MODEL;

import android.widget.Button;
import android.widget.EditText;

import com.example.romulo.comprafacil.CadastraProdutoActivity;
import com.example.romulo.comprafacil.R;

/**
 * Created by Marques de Souza on 17/02/2017.
 */

public class CadastraProdutoHelper {
    private EditText cod;
    private EditText nome;
    private EditText localizacao;
    private EditText foto;
    private Produto produto;
    public CadastraProdutoHelper(CadastraProdutoActivity activity){
        cod = (EditText) activity.findViewById(R.id.editTextCodProd1);
        nome = (EditText) activity.findViewById(R.id.editTextNomProd1);
        localizacao = (EditText) activity.findViewById(R.id.editTextSecao);
        produto=new Produto();

    }
    public  Produto pegaProduto(){
        if (cod.getText().toString().equals("")) {
            cod.setError("Insira o código");
            cod.requestFocus();
        } else if (nome.getText().toString().equals("")) {
            nome.setError("Insira nome do produto");
            nome.requestFocus();
        } else if (localizacao.getText().toString().equals("")) {
            localizacao.setError("Insira nome da seção");
            localizacao.requestFocus();
        } else {
        produto.setCod_pro(cod.getText().toString());
        produto.setNome_pro(nome.getText().toString());
        produto.setLocalizacao_pro(localizacao.getText().toString());

        }
        return  produto;
    }
    public void preencher(Produto produto){
        cod.setText(produto.getCod_pro());
        nome.setText(produto.getNome_pro());
        localizacao.setText(produto.getLocalizacao_pro());
    }
}
