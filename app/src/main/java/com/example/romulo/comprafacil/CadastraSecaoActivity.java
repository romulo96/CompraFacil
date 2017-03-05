package com.example.romulo.comprafacil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.romulo.comprafacil.DAO.SecaoDAO;
import com.example.romulo.comprafacil.HELPER.CasdastraSecaoHelper;
import com.example.romulo.comprafacil.MODEL.Secao;

public class CadastraSecaoActivity extends AppCompatActivity {
        private Secao secao;
        private CasdastraSecaoHelper helper;
        private int temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_secao);
        final SecaoDAO PD=new SecaoDAO(this);
        helper= new CasdastraSecaoHelper(this);
        Intent intecaopegar=getIntent();
        secao=(Secao) intecaopegar.getSerializableExtra("secao");
        Button botao = (Button) findViewById(R.id.buttonCadastrarSecao);
        if(secao!=null){
            temp=1;
            helper.preencher(secao);
        }
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secao=helper.pegaSecao();
                if(temp==1){
                    PD.alterar(secao);

                }else{
                    PD.inserir(secao);
                }finish();
            }
        });

        }
}
