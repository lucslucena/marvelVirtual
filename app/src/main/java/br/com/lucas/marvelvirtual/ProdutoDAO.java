package br.com.lucas.marvelvirtual;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private final ConexionSQL conexionSQL;
    private Object ContentValues;

    public ProdutoDAO(ConexionSQL conexionSQL) {
        this.conexionSQL = conexionSQL;
    }

    public long salvaProdutoDAO(Produto p){
        SQLiteDatabase db = conexionSQL.getWritableDatabase();

        try{
            ContentValues v = new ContentValues();
            v.put("id", p.getId());
            v.put("nome", p.getNome());
            v.put("quantidade_estoque", p.getQtdEstoque());
            v.put("edicao", p.getEdicao());
            v.put("preco", p.getPreco());

            long idProdutoNovo = db.insert("produtos", null, v);

            return idProdutoNovo;
        }
        catch (Exception ex){
            ex.printStackTrace();
        } finally {
            if(db != null){
                db.close();
            }
        }
        return 0;

    }

    public List<Produto> getListaProdutosDAO() {
        List<Produto> listaProdutos = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor;

        String query = "SELECT * FROM produtos;";

        try {
            db = this.conexionSQL.getReadableDatabase();
            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                Produto pTemp = null;


                do {
                    pTemp = new Produto(); // instancia um novo produto
                    pTemp.setId(cursor.getLong(0));
                    pTemp.setNome(cursor.getString(1));
                    pTemp.setQtdEstoque(cursor.getInt(2));
                    pTemp.setEdicao(cursor.getInt(3));
                    pTemp.setPreco(cursor.getDouble(4));
                    //adiciona os dados
                    listaProdutos.add(pTemp); //adiciona na lista
                }
                while (cursor.moveToNext()); //se houver um próximo produto, move para o próximo e
            }
        }
        catch (Exception ex) {
            Log.d("ERRO_LISTA_PRODUTOS", "Erro ao retornar os produtos");
            return null;
        }
        finally {
            if (db != null) {
                db.close();
            }
        }

        return listaProdutos;
    }

    public boolean excluirProdutoDAO(long pidProduto){
        SQLiteDatabase db = null;

        try{
            db = this.conexionSQL.getWritableDatabase();

            db.delete(
                    "produtos",
                    "id = ?",
                    new String[]{String.valueOf(pidProduto)}
            );
        }
        catch(Exception ex){
            Log.d("ProdutoDAO", "NAO FOI POSSIVEL DELETAR O PRODUTO");
            return false;
        }
        finally {
            if(db != null){
                db.close();
            }
        }
        return true;
    }

    public boolean atualizarProdutoDAO(Produto p){
        SQLiteDatabase db = null;
        try{

            db = this.conexionSQL.getWritableDatabase();

            ContentValues prodAtributos = new ContentValues();
            prodAtributos.put("nome", p.getNome());
            prodAtributos.put("quantidade_estoque", p.getQtdEstoque());
            prodAtributos.put("edicao", p.getEdicao());
            prodAtributos.put("preco", p.getPreco());


            int atualizou = db.update("produtos",
                    prodAtributos,
                    "id = ?",
                    new String[]{String.valueOf(p.getId())}
                    );

            if(atualizou > 0){
                return true;
            }

        }
        catch(Exception ex){
            Log.d("PRODUTOD", "não foi possível atualizar o produto");
        }
        finally {
            if(db != null){
                db.close();
            }
        }
        return false;
    }
}
