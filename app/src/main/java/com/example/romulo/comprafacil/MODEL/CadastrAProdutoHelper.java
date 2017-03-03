package com.example.romulo.comprafacil.MODEL;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.romulo.comprafacil.CadastraProdutoActivity;
import com.example.romulo.comprafacil.CadastraSecaoActivity;
import com.example.romulo.comprafacil.CadastroActivity;
import com.example.romulo.comprafacil.DAO.ProdutoDAO;
import com.example.romulo.comprafacil.DAO.SecaoDAO;
import com.example.romulo.comprafacil.R;

import java.util.List;

/**
 * Created by Marques de Souza on 17/02/2017.
 */

public class CadastraProdutoHelper {
    private EditText cod;
    private EditText nome;
    private EditText localizacao;
    private EditText foto;
    private Produto produto;
    private SecaoDAO DAO;
    private Secao secao;
    private Spinner spn;
    private String temp;

    public CadastraProdutoHelper(CadastraProdutoActivity activity){
        DAO=new SecaoDAO(activity);
        List<Secao> secaos = DAO.busca();DAO.close();
        spn=(Spinner) activity.findViewById(R.id.spinner);
        ArrayAdapter<Secao> adapter = new ArrayAdapter<Secao>(activity, android.R.layout.simple_list_item_1, secaos);
        spn.setAdapter(adapter);
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                secao=(Secao) spn.getItemAtPosition(position);
                temp=secao.getCod_secao()+"-"+secao.getNome_secao();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        cod = (EditText) activity.findViewById(R.id.editTextCodProd1);
        nome = (EditText) activity.findViewById(R.id.editTextNomProd1);
        produto=new Produto();
    }
    public  Produto pegaProduto(){

        produto.setCod_pro(cod.getText().toString());
        produto.setNome_pro(nome.getText().toString());
        produto.setLocalizacao_pro(temp);
        return  produto;
    }
    public void preencher(Produto produto){
        cod.setText(produto.getCod_pro());
        nome.setText(produto.getNome_pro());

    }
}
