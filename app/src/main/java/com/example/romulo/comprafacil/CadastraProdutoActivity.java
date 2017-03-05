package com.example.romulo.comprafacil;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.romulo.comprafacil.DAO.ProdutoDAO;
import com.example.romulo.comprafacil.HELPER.CadastraProdutoHelper;
import com.example.romulo.comprafacil.MODEL.Produto;

import java.io.File;

public class  CadastraProdutoActivity extends AppCompatActivity {
    private Produto produto;
    private Integer CODIGO_CAMERA=530;
    private String caminho;
    private CadastraProdutoHelper helper;
    private int temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_produto);

        final ProdutoDAO PD = new ProdutoDAO(this);
        Intent intecaopegar=getIntent();
        produto=(Produto)intecaopegar.getSerializableExtra("produto");
        helper= new CadastraProdutoHelper(this);

        final Button botaofoto=(Button) findViewById(R.id.formulario_botao_foto);
        Button botao = (Button) findViewById(R.id.cadrasProduto);
        if(produto!=null){
            temp=1;
            helper.preencher(produto);
        }
        botaofoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                caminho = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File arquivoFoto = new File(caminho);
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto));
                startActivityForResult(intentCamera, CODIGO_CAMERA);
            }
        });
        botao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                   //Obrigar a não cadastrar vazio
                        produto=helper.pegaProduto();
                        if(caminho==null){
                            produto.setFoto("vazio");
                        }else{
                        produto.setFoto(caminho);}
                        if(temp==1){
                            PD.alterar(produto);

                        }else{
                            PD.inserir(produto);
                        }finish();
                    }


        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CODIGO_CAMERA) {

            }
        }

    }

}
