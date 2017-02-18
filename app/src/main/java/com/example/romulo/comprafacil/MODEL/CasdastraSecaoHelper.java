package com.example.romulo.comprafacil.MODEL;

import android.widget.EditText;

import com.example.romulo.comprafacil.CadastraSecaoActivity;
import com.example.romulo.comprafacil.R;

/**
 * Created by Marques de Souza on 17/02/2017.
 */

public class CasdastraSecaoHelper {
    private EditText cod;
    private EditText nome;
    private Secao secao;
    public CasdastraSecaoHelper(CadastraSecaoActivity activity){
        cod=(EditText) activity.findViewById(R.id.editTextCodigoSecao);
        nome=(EditText) activity.findViewById(R.id.editTextNomeSecao);
        secao=new Secao();
    }
    public Secao pegaSecao(){
        secao.setCod_secao(cod.getText().toString());
        secao.setNome_secao(nome.getText().toString());
        return secao;
    }
    public void preencher(Secao secao){
        cod.setText(secao.getCod_secao());
        nome.setText(secao.getNome_secao());
        }


}
