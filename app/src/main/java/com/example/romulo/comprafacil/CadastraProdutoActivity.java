package com.example.romulo.comprafacil;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.romulo.comprafacil.DAO.ProdutoDAO;

public class CadastraProdutoActivity extends AppCompatActivity {

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

       // final String oi = String.valueOf(codigo.getText());

        final String nomeString = nome.getText().toString();
     //   final String localizacaoString = localizacao.getSelectedItem().toString();


        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  PD.inserirProduto(codigoString,nomeString,"localizacaoString");
                String codigoString = codigo.getText().toString();
                String nomeString = nome.getText().toString();
                String localizacaoString = localizacao.getText().toString();
                //String oi = String.valueOf(codigo.getText());
                //AlertDialog.Builder al = new AlertDialog.Builder(CadastraProdutoActivity.this);
               // al.setMessage(">"+codigoString+"r"+nomeString+oi);
                //al.show();
                PD.inserirProduto(codigoString,nomeString,localizacaoString);
                finish();
            }
        });


    }
}
