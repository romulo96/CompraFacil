package com.example.romulo.comprafacil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.romulo.comprafacil.DAO.ProdutoDAO;
import com.example.romulo.comprafacil.MODEL.Produto;

public class ExibeFotoActivity extends AppCompatActivity {
        private Produto produto;
        private ImageView campoFoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibe_foto);
        final ProdutoDAO PD = new ProdutoDAO(this);
        Intent intecaopegar=getIntent();
        produto=(Produto) intecaopegar.getSerializableExtra("produto");
        campoFoto=(ImageView) findViewById(R.id.imageViewProduto);
        carregaImagem(produto.getFoto());


    }
    public void carregaImagem(String caminhoFoto) {
        if (caminhoFoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            campoFoto.setTag(caminhoFoto);
        }
    }
}
