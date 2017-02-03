package com.example.romulo.comprafacil.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.romulo.comprafacil.MODEL.Produto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Romulo on 01/02/2017.
 */

public class ProdutoDAO extends SQLiteOpenHelper  {
    public ProdutoDAO(Context contexto){super(contexto,"produto",null,1);}
        private static final String CODIGO     ="codigo";
        private static final String NOME        ="nome";
        private static final String TABELA      ="tabela_produto";
        private static final String LOCALIZACAO = "localizacao";

    // CRIAÇÃO DA TABELA NO BANCO DE DADOS
    @Override
    public void onCreate (SQLiteDatabase db){
        String sql = "CREATE TABLE PRODUTOS (codigo TEXT NOT NULL, nome TEXT NOT NULL, localizacao TEXT NOT NULL);";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String sql = "DROP TABLE IF EXISTS "+TABELA;
        db.execSQL(sql);
        onCreate(db);
    }
    // FUNÇÃO INSERÇÃO NO BANCO
    public void inserir(Produto produto){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValuesProduto(produto);
        db.insert(TABELA,null,dados);

    }
    @NonNull
    private ContentValues getContentValuesProduto(Produto produto) {
        ContentValues dados = new ContentValues();
        dados.put(CODIGO, produto.getCod_pro());
        dados.put(NOME, produto.getNome_pro());
        dados.put(LOCALIZACAO, produto.getLocalizacao_pro());
        return dados;
    }
    public List<Produto> buscar(){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM "+TABELA+";";
        Cursor c = db.rawQuery(sql,null);

        List<Produto> produtos = new ArrayList<Produto>();
        while (c.moveToNext()){
            Produto produto = new Produto();
            produto.setCod_pro(c.getInt(   c.getColumnIndex(CODIGO)));
            produto.setNome_pro(c.getString(c.getColumnIndex(NOME)));
            produto.setLocalizacao_pro(c.getString(c.getColumnIndex(LOCALIZACAO)));
            produtos.add(produto);

        }
        c.close();
        return produtos;
    }

    // DANDO ERRO POR A VARIAVEL SER DO TIPO INTEIRO
    public void deletar(Produto produto){
        SQLiteDatabase db = getWritableDatabase();

        int [] params = {produto.getCod_pro()};
       // db.delete(TABELA, CODIGO+" = ?", params);
    }

    public void limpar(){
        SQLiteDatabase db = getWritableDatabase();

        String [] params = {"remover"};
       // db.delete(TABELA, "'remover' = ?", params);
    }
    public void alterar(Produto produto){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = getContentValuesProduto(produto);
        int [] params = {produto.getCod_pro()};
        //db.update(TABELA,dados,CODIGO +"= ? ",params);
    }


}
