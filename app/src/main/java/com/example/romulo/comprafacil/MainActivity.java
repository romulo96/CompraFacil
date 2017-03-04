package com.example.romulo.comprafacil;

import android.content.Intent;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SearchView;

import com.example.romulo.comprafacil.DAO.ProdutoDAO;
import com.example.romulo.comprafacil.MODEL.AdapterProduto;
import com.example.romulo.comprafacil.MODEL.Produto;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String conteudopesq;
    private static ListView lista;
    private RadioButton nome;
    private RadioButton codigo;
    private RadioButton secao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Lista vinculando XML a classe
        lista=(ListView)findViewById(R.id.ListPesquisa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Botão para acionar a pesquisa
        final SearchView pesquisa =(SearchView) findViewById(R.id.pesquisa);
        final AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);

        pesquisa.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
              @Override
              public boolean onQueryTextSubmit(String query) {
                  return false;
              }

              @Override
              //
              public boolean onQueryTextChange(String newText) {
                  final ProdutoDAO PD = new ProdutoDAO(MainActivity.this);
                  nome=(RadioButton) findViewById(R.id.rbtnNome);
                  codigo=(RadioButton) findViewById(R.id.rbtnCodigo);
                  secao=(RadioButton) findViewById(R.id.rbtnSecao);
                  conteudopesq=pesquisa.getQuery().toString();
                  if(nome.isChecked()){
                      List<Produto> produtos=PD.buscaparodutotela(conteudopesq);
                      AdapterProduto adapter = new AdapterProduto(produtos,MainActivity.this);
                      lista.setAdapter(adapter);
                  }
                  if(codigo.isChecked()){
                      List<Produto> produtos=PD.buscaparodutotelaCodigo(conteudopesq);
                      AdapterProduto adapter = new AdapterProduto(produtos,MainActivity.this);
                      lista.setAdapter(adapter);
                  }else{
                      List<Produto> produtos=PD.buscaparodutotelaSecao(conteudopesq);
                          AdapterProduto adapter = new AdapterProduto(produtos,MainActivity.this);
                          lista.setAdapter(adapter);
                  }



                  return false;
              }
          });

            }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    protected void onResume() {
        //Para fechar aplicação na tela de lista de produtos
        if(getIntent().getBooleanExtra("SAIR", false)){
            finish();
        }
        super.onResume();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_settings:
                Intent activity_login = new Intent(this, LoginActivity.class);
                startActivity(activity_login);
                return true;

            //Menu inflater para sair da aplicação
            case R.id.action_sair:
                this.finish();
                return true;

            default: return false;
        }

    }
}
