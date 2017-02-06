package com.example.romulo.comprafacil;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.romulo.comprafacil.DAO.ProdutoDAO;

public class  CadastraProdutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_produto);

        final ProdutoDAO PD = new ProdutoDAO(this);

    //   Botao para setar dados do banco da tela produto
        Button botao = (Button) findViewById(R.id.cadrasProduto);

        final EditText codigo = (EditText) findViewById(R.id.editTextCodProd1);
        final EditText nome = (EditText) findViewById(R.id.editTextNomProd1);
        final EditText localizacao = (EditText) findViewById(R.id.editTextSecao);

        final String nomeString = nome.getText().toString();

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                   //Obrigar a não cadastrar vazio
                    if (codigo.getText().toString().equals("")) {
                        codigo.setError("Insira o código");
                        codigo.requestFocus();
                    } else if (nome.getText().toString().equals("")) {
                        nome.setError("Insira nome do produto");
                        nome.requestFocus();
                    } else if (localizacao.getText().toString().equals("")) {
                        localizacao.setError("Insira nome da seção");
                        localizacao.requestFocus();
                    } else {

                        String codigoString = codigo.getText().toString();
                        String nomeString = nome.getText().toString();
                        String localizacaoString = localizacao.getText().toString();
                        PD.inserirProduto(codigoString,nomeString,localizacaoString);
                        finish();
                    }
            }

        });

    }
}
