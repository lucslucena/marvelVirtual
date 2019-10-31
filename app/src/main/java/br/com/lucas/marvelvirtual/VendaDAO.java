package br.com.lucas.marvelvirtual;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class VendaDAO {

    private final ConexionSQL conexionSQL;

    public VendaDAO(ConexionSQL conexionSQL) {
        this.conexionSQL = conexionSQL;
    }


    public long salvarVendaDAO(Venda vVenda) {

        SQLiteDatabase db = conexionSQL.getWritableDatabase();
        try {

            ContentValues values = new ContentValues();
            values.put("data", vVenda.getDataVenda().getTime());

            long idVendaInserido = db.insert("vendas", null, values);
            return idVendaInserido;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if (db != null) {
                db.close();
            }
        }

        return 0;
    }


    public boolean salvarItensVenda(Venda v) {

        SQLiteDatabase db = conexionSQL.getWritableDatabase();

        try {
            ContentValues values = null;

            for (ItemCarrinho iVenda : v.getItensVenda()) {
                values = new ContentValues();
                values.put("quantidade_vendida", iVenda.getQtdeSelecionada());
                values.put("id_produto", iVenda.getIdProduto());
                values.put("id_venda", iVenda.getId());

                db.insert("item_venda", null, values);

            }
            return true;

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if (db != null) {
                db.close();
            }
        }

        return false;
    }

}
