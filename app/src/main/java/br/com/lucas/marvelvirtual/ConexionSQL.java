package br.com.lucas.marvelvirtual;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSQL extends SQLiteOpenHelper {

    private static ConexionSQL INST_CONEXION;
    private static final int version_db = 1;
    private static final String name_db = "nome-produtos-app";

    public ConexionSQL(@Nullable Context context) {
        super(context, name_db, null, version_db);
    }


    //padrão singleton
    public static ConexionSQL getInstance(Context context){
        if(INST_CONEXION == null){
            INST_CONEXION = new ConexionSQL(context);
        }
        return INST_CONEXION;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTabelaProd =
                "CREATE TABLE IF NOT EXISTS produtos" +
                        "(" +
                        "id INTEGER PRIMARY KEY," +
                        "nome TEXT," +
                        "quantidade_estoque INTEGER," +
                        "edicao INTEGER," +
                        "preco REAL" +
                        ")";

        db.execSQL(sqlTabelaProd);

        String sqlTabelaVenda =
                "CREATE TABLE IF NOT EXISTS vendas" +
                        "(" +
                        "id INTEGER PRIMARY KEY," +
                        "data INTEGER" +
                        ")";

        db.execSQL(sqlTabelaVenda);

        String sqlTabelaItemVenda =
                "CREATE TABLE IF NOT EXISTS item_venda" +
                        "(" +
                        "id INTEGER PRIMARY KEY," +
                        "quantidade_vendida INTEGER," +
                        "id_produto INTEGER," +
                        "id_venda INTEGER" +
                        ")";

        db.execSQL(sqlTabelaItemVenda);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
