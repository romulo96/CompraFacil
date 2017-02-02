package com.example.romulo.comprafacil.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Romulo on 01/02/2017.
 */

public class ProdutoDAO extends SQLiteOpenHelper  {
    public ProdutoDAO(Context contexto){super(contexto,"produto",null,1);}
        private static final String CODIGO ="codigo";
        private static final  String NOME="nome";
        private static final String TABELA="tabela_produto";
        private static final String LOCALIZACAO = "localizacao";



}
