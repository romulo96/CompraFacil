package com.example.romulo.comprafacil.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.romulo.comprafacil.MODEL.Produto;
import com.example.romulo.comprafacil.MODEL.Secao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marques de Souza on 17/02/2017.
 */

public class SecaoDAO extends SQLiteOpenHelper {
    public SecaoDAO(Context contexto){super(contexto,"secao",null,1);}
    private static final String CODIGO     ="codigo";
    private static final String NOME        ="nome";
    private static final String TABELA      ="SECAO";

    // CRIAÇÃO DAS TABELAS NO BANCO DE DADO
    @Override
    public void onCreate (SQLiteDatabase db){
        String sql = "CREATE TABLE SECAO (codigo TEXT NOT NULL, nome TEXT NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String sql = "";
        switch (oldVersion) {
            case 1:
                sql = "ALTER TABLE SECAO ADD COLUMN foto TEXT";
                db.execSQL(sql); // indo para versao 2
        }
    }

    // FUNÇÃO DE INSERÇÃO NO BANCO
    public void inserir(Secao secao){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValuesProduto(secao);
        db.insert(TABELA,null,dados);

    }

    public void inserirSecao(String codigo, String nome){
        ContentValues contentValues = new ContentValues();
        try {
            contentValues.put(CODIGO, codigo);
            contentValues.put(NOME, nome);
            SQLiteDatabase db = getWritableDatabase();

            db.insert(TABELA, null, contentValues);
        } catch (SQLiteAbortException e){

        }

    }

    @NonNull
    private ContentValues getContentValuesProduto(Secao secao) {
        ContentValues dados = new ContentValues();
        dados.put(CODIGO, secao.getCod_secao());
        dados.put(NOME, secao.getNome_secao());
        return dados;
    }
    public List<String> buscarSecao(){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM "+TABELA+";";
        Cursor c = db.rawQuery(sql,null);

        List<String> secao = new ArrayList<>();
        while (c.moveToNext()){

            secao.add("Código: " + c.getString(c.getColumnIndex("codigo"))+  "\nDescrição: " +c.getString(c.getColumnIndex("nome")) +
                    "\nSeção: "+c.getString(c.getColumnIndex("localizacao")));

        }
        c.close();
        return secao;
    }

    // buscar produto tela inicial

    public List<Secao> buscarsecaotela(String busca){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM "+TABELA+" where nome like '%"+busca+"%'";
        Cursor c = db.rawQuery(sql, null);
        List<Secao> secaos = new ArrayList<Secao>();
        while (c.moveToNext()) {
            Secao secao = new Secao();
            secao.setCod_secao(c.getString(c.getColumnIndex("codigo")));
            secao.setNome_secao(c.getString(c.getColumnIndex("nome")));
            secaos.add(secao);
        }
        c.close();
        return secaos;
    }
    public List<Secao> busca(){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM "+TABELA+";";
        Cursor c = db.rawQuery(sql, null);

        List<Secao> secaos = new ArrayList<Secao>();
        while (c.moveToNext()) {
            Secao secao = new Secao();
            secao.setCod_secao(c.getString(c.getColumnIndex("codigo")));
            secao.setNome_secao(c.getString(c.getColumnIndex("nome")));
            secaos.add(secao);
        }
        c.close();
        return secaos;
    }

    public Secao buscasecao(String cod){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM "+TABELA+"where codigo="+cod+";";
        Cursor c = db.rawQuery(sql, null);

        Secao secao = new Secao();
        secao.setCod_secao(c.getString(c.getColumnIndex("codigo")));
        secao.setNome_secao(c.getString(c.getColumnIndex("nome")));
        c.close();
        return secao;
    }

    // DANDO ERRO POR A VARIAVEL SER DO TIPO INTEIRO
    public void deletar(Secao secao){
        SQLiteDatabase db = getWritableDatabase();

        String [] params = {secao.getCod_secao().toString()};
        db.delete("SECAO", "codigo = ?", params);
    }

    public void limpar(){
        SQLiteDatabase db = getWritableDatabase();

        String [] params = {"remover"};
        // db.delete(TABELA, "'remover' = ?", params);
    }
    public void alterar(Secao secao){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = getContentValuesProduto(secao);
        String [] params = {secao.getCod_secao().toString()};
        db.update("SECAO",dados, "codigo = ?",params);
    }

}
