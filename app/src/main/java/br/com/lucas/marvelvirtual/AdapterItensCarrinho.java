package br.com.lucas.marvelvirtual;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterItensCarrinho extends BaseAdapter {

    private Context cnt;
    private List<ItemCarrinho> itensCarrinho;

    public AdapterItensCarrinho(Context cnt, List<ItemCarrinho> itensCarrinho) {
        this.cnt = cnt;
        this.itensCarrinho = itensCarrinho;
    }

    @Override
    public int getCount() {
        return this.itensCarrinho.size();
    }

    @Override
    public Object getItem(int position) {
        return this.itensCarrinho.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public boolean removeItemCarrinho(int position){
        this.itensCarrinho.remove(position);
        notifyDataSetChanged();

        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(this.cnt, R.layout.layout_carrinho, null );

        TextView vNomeProduto = (TextView) v.findViewById(R.id.tvNomeProduto);
        TextView vPrcProduto  = (TextView) v.findViewById(R.id.tvPrecoProduto);
        TextView vQtdeSelecionada = (TextView) v.findViewById(R.id.tvQtdeProduto);
        TextView vValorItem = (TextView) v.findViewById((R.id.tValorTotalItem));


        vNomeProduto.setText(this.itensCarrinho.get(position).getNome());
        vPrcProduto.setText(String.valueOf(this.itensCarrinho.get(position).getPreco()));
        vQtdeSelecionada.setText(String.valueOf(this.itensCarrinho.get(position).getQtdeSelecionada()));
        vValorItem.setText(String.valueOf(this.itensCarrinho.get(position).getPrecoProdutoVenda()));

        return v;
    }


    //adiciona um item ao carrinho
    public void addItemCarrinho(ItemCarrinho iCarrinho){
        this.itensCarrinho.add(iCarrinho);
        this.notifyDataSetChanged();
    }
/*
Atualiza a lista do carrinho do adapter
 */

    public void atualizar(List<ItemCarrinho> iCarrinho){
        this.itensCarrinho.clear();
        this.itensCarrinho = iCarrinho;
        this.notifyDataSetChanged();
    }
}
