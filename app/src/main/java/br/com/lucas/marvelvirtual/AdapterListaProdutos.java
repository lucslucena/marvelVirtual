package br.com.lucas.marvelvirtual;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterListaProdutos extends BaseAdapter {

    private Context cnt;
    private List<Produto> prodList;

    public AdapterListaProdutos(Context cnt, List<Produto> produtoList) {
        this.cnt = cnt;
        this.prodList = produtoList;
    }

    @Override
    public int getCount() {
        return this.prodList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.prodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void removeProduto(int position){
        this.prodList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(this.cnt, R.layout.layout_produto, null );

        TextView vNomeProduto = (TextView) v.findViewById(R.id.vNomeProduto);
        TextView vPrcProduto  = (TextView) v.findViewById(R.id.vPrcProduto);
        TextView vEstoque = (TextView) v.findViewById(R.id.vEstoque);
        TextView vEdicao = (TextView) v.findViewById(R.id.vEdicao);


        vNomeProduto.setText(this.prodList.get(position).getNome());
        vPrcProduto.setText(String.valueOf(this.prodList.get(position).getPreco()));
        vEstoque.setText(String.valueOf(this.prodList.get(position).getQtdEstoque()));
        vEdicao.setText(String.valueOf(this.prodList.get(position).getEdicao()));

        return v;
    }
/*
Atualiza a lista de produtos do adapter
 */

    public void atualizar(List<Produto> pProduto){
        this.prodList.clear();
        this.prodList = pProduto;
        this.notifyDataSetChanged();
    }
}
