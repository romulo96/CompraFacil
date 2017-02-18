package com.example.romulo.comprafacil.MODEL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.romulo.comprafacil.R;

import java.util.List;

/**
 * Created by Marques de Souza on 16/02/2017.
 */

public class AdapterProduto extends BaseAdapter {
    private final List<Produto> produtoArrayAdapter;
    private final Activity contexto;
    public AdapterProduto(List<Produto> produtoArrayAdapter, Activity contexto) {
        this.produtoArrayAdapter = produtoArrayAdapter;
        this.contexto=contexto;
    }


    @Override
    public int getCount() {
        return produtoArrayAdapter.size();
    }

    @Override
    public Object getItem(int position) {
        return produtoArrayAdapter.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= contexto.getLayoutInflater().inflate(R.layout.listviewcustom,parent,false);

        Produto produto=produtoArrayAdapter.get(position);

        TextView cod=(TextView) view.findViewById(R.id.codigo_produto);
        TextView nome=(TextView) view.findViewById(R.id.nome_produto);
        TextView local=(TextView) view.findViewById(R.id.localizacao_produto);
        ImageView foto= (ImageView) view.findViewById(R.id.Foto_pequena);
        cod.setText("Codigo: "+produto.getCod_pro());
        nome.setText(produto.getNome_pro());
        local.setText("Localização: "+produto.getLocalizacao_pro());
        if(produto.getFoto().equals("vazio")){

        }else{
        Bitmap bitmap = BitmapFactory.decodeFile(produto.getFoto());
        Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
        foto.setImageBitmap(bitmapReduzido);
        foto.setScaleType(ImageView.ScaleType.FIT_XY);
        foto.setTag(produto.getFoto());
        }
        return view;
    }
}
