package com.example.romulo.comprafacil.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.romulo.comprafacil.MODEL.Produto;

import junit.runner.Version;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Romulo on 01/02/2017.
 */


//OpenHelper responsável criação do banco e pelo versionamento
public class ProdutoDAO extends SQLiteOpenHelper  {
    public ProdutoDAO(Context contexto){super(contexto,"produto",null,1);}
        private static final String CODIGO     ="codigo";
        private static final String NOME        ="nome";
        private static final String TABELA      ="PRODUTOS";
        private static final String LOCALIZACAO = "localizacao";
        private static final String FOTO="foto";
    // CRIAÇÃO DAS TABELAS NO BANCO DE DADO
    @Override
    public void onCreate (SQLiteDatabase db){
        String sql = "CREATE TABLE PRODUTOS (codigo TEXT NOT NULL, nome TEXT NOT NULL, localizacao TEXT NOT NULL,foto TEXT );";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String sql = "";
        switch (oldVersion) {
            case 1:
                sql = "ALTER TABLE PRODUTOS ADD COLUMN foto TEXT";
                db.execSQL(sql); // indo para versao 2
        }
    }

    // FUNÇÃO DE INSERÇÃO NO BANCO
    public void inserir(Produto produto){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValuesProduto(produto);
        db.insert(TABELA,null,dados);

    }

    public void inserirProduto(String codigo, String nome, String localizacao,String foto){
ContentValues contentValues = new ContentValues();
        try {
            contentValues.put(CODIGO, codigo);
            contentValues.put(NOME, nome);
            contentValues.put(LOCALIZACAO, localizacao);
            contentValues.put(FOTO,foto);
            SQLiteDatabase db = getWritableDatabase();

            db.insert(TABELA, null, contentValues);
        } catch (SQLiteAbortException e){

        }

    }

    @NonNull
    private ContentValues getContentValuesProduto(Produto produto) {
        ContentValues dados = new ContentValues();
        dados.put(CODIGO, produto.getCod_pro());
        dados.put(NOME, produto.getNome_pro());
        dados.put(LOCALIZACAO, produto.getLocalizacao_pro());
        dados.put(FOTO, produto.getFoto());
        return dados;
    }
    public List<String> buscarProduto(){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM "+TABELA+";";
        Cursor c = db.rawQuery(sql,null);

        List<String> produtos = new ArrayList<>();
        while (c.moveToNext()){

            produtos.add("Código: " + c.getString(c.getColumnIndex("codigo"))+  "\nDescrição: " +c.getString(c.getColumnIndex("nome")) +
            "\nSeção: "+c.getString(c.getColumnIndex("localizacao")));

        }
        c.close();
        return produtos;
    }

    // buscar produto tela inicial

    public List<Produto> buscaparodutotela(String busca){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM "+TABELA+" where nome like '%"+busca+"%'";
        Cursor c = db.rawQuery(sql, null);
        List<Produto> produtos = new ArrayList<Produto>();
        while (c.moveToNext()) {
            Produto produto = new Produto();
            produto.setCod_pro(c.getString(c.getColumnIndex("codigo")));
            produto.setNome_pro(c.getString(c.getColumnIndex("nome")));
            produto.setLocalizacao_pro(c.getString(c.getColumnIndex("localizacao")));
            produto.setFoto(c.getString(c.getColumnIndex("foto")));
            produtos.add(produto);
        }
        c.close();
        return produtos;
    }
    public List<Produto> buscaparodutotelaCodigo(String busca){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM "+TABELA+" where codigo like '%"+busca+"%'";
        Cursor c = db.rawQuery(sql, null);
        List<Produto> produtos = new ArrayList<Produto>();
        while (c.moveToNext()) {
            Produto produto = new Produto();
            produto.setCod_pro(c.getString(c.getColumnIndex("codigo")));
            produto.setNome_pro(c.getString(c.getColumnIndex("nome")));
            produto.setLocalizacao_pro(c.getString(c.getColumnIndex("localizacao")));
            produto.setFoto(c.getString(c.getColumnIndex("foto")));
            produtos.add(produto);
        }
        c.close();
        return produtos;
    }
    public List<Produto> buscaparodutotelaSecao(String busca){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM "+TABELA+" where localizacao like '%"+busca+"%'";
        Cursor c = db.rawQuery(sql, null);
        List<Produto> produtos = new ArrayList<Produto>();
        while (c.moveToNext()) {
            Produto produto = new Produto();
            produto.setCod_pro(c.getString(c.getColumnIndex("codigo")));
            produto.setNome_pro(c.getString(c.getColumnIndex("nome")));
            produto.setLocalizacao_pro(c.getString(c.getColumnIndex("localizacao")));
            produto.setFoto(c.getString(c.getColumnIndex("foto")));
            produtos.add(produto);
        }
        c.close();
        return produtos;
    }
    public List<Produto> busca(){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM "+TABELA+";";
        Cursor c = db.rawQuery(sql, null);

        List<Produto> produtos = new ArrayList<Produto>();
        while (c.moveToNext()) {
            Produto produto = new Produto();
            produto.setCod_pro(c.getString(c.getColumnIndex("codigo")));
            produto.setNome_pro(c.getString(c.getColumnIndex("nome")));
            produto.setLocalizacao_pro(c.getString(c.getColumnIndex("localizacao")));
            produto.setFoto(c.getString(c.getColumnIndex("foto")));
            produtos.add(produto);
        }
        c.close();
        return produtos;
    }

    public Produto buscarproduto(String cod){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM "+TABELA+"where codigo="+cod+";";
        Cursor c = db.rawQuery(sql, null);

           Produto produto = new Produto();
            produto.setCod_pro(c.getString(c.getColumnIndex("codigo")));
            produto.setNome_pro(c.getString(c.getColumnIndex("nome")));
            produto.setLocalizacao_pro(c.getString(c.getColumnIndex("localizacao")));
            produto.setFoto(c.getString(c.getColumnIndex("foto")));
        c.close();
        return produto;
    }

    // DANDO ERRO POR A VARIAVEL SER DO TIPO INTEIRO
    public void deletar(Produto produto){
        SQLiteDatabase db = getWritableDatabase();

        String [] params = {produto.getCod_pro().toString()};
        db.delete("PRODUTOS", "codigo = ?", params);
    }

    public void limpar(){
        SQLiteDatabase db = getWritableDatabase();

        String [] params = {"remover"};
       // db.delete(TABELA, "'remover' = ?", params);
    }
    public void alterar(Produto produto){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = getContentValuesProduto(produto);
        String [] params = {produto.getCod_pro().toString()};
        db.update("PRODUTOS",dados, "codigo = ?",params);
    }

}
