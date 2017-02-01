package com.example.romulo.comprafacil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }




    // CHAMAR TELA CADASTRO
    public void startactivity_cadastro2(View view){
        Intent activity_cadastro2 = new Intent(this, CadastroActivity.class);
        startActivity(activity_cadastro2);
    }
}
