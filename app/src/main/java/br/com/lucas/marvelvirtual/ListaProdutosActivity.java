package br.com.lucas.marvelvirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static br.com.lucas.marvelvirtual.R.*;

public class ListaProdutosActivity extends AppCompatActivity {

    private ListView lstProdutos;
    private List<Produto> prodList;
    private AdapterListaProdutos adpListaProdutos;
    private ProdutoController pdController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_lista_produtos);

        this.pdController = new ProdutoController(ConexionSQL.getInstance(ListaProdutosActivity.this));

        prodList = pdController.getListaProdutosController();

        this.lstProdutos = (ListView) findViewById(id.lstProduto); //esse lstProduto é o id do activity_lista_produtos

        this.adpListaProdutos = new AdapterListaProdutos(ListaProdutosActivity.this, this.prodList);

        this.lstProdutos.setAdapter(this.adpListaProdutos);

        this.lstProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                final Produto pSelecionado = (Produto) adpListaProdutos.getItem(position);

                AlertDialog.Builder janelaEscolha = new AlertDialog.Builder(ListaProdutosActivity.this);

                janelaEscolha.setTitle("Selecione:");
                janelaEscolha.setMessage("O que deseja fazer com o quadrinho selecionado?");

                janelaEscolha.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                janelaEscolha.setNegativeButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                        boolean excluir = pdController.excluirProdutoController(pSelecionado.getId());

                        if(excluir == true){

                            adpListaProdutos.removeProduto(position); //remove na mesma hora

                            Toast.makeText(ListaProdutosActivity.this, "Produto excluído!", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(ListaProdutosActivity.this, "Produto não foi excluído!", Toast.LENGTH_LONG).show();
                        }

                    }
                });

                janelaEscolha.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        Bundle bDadosProduto = new Bundle();

                        bDadosProduto.putLong("id_produto", pSelecionado.getId());
                        bDadosProduto.putString("nome_produto", pSelecionado.getNome());
                        bDadosProduto.putDouble("preco_produto", pSelecionado.getPreco());
                        bDadosProduto.putInt("edicao_produto", pSelecionado.getEdicao());
                        bDadosProduto.putInt("qtEstoque_produto", pSelecionado.getQtdEstoque());

                        Intent itEditarProdutos = new Intent(ListaProdutosActivity.this, EditarProdutosActivity.class);
                        itEditarProdutos.putExtras(bDadosProduto);
                        startActivity(itEditarProdutos);
                    }

                });

                janelaEscolha.create().show();

            }
        });

    }


    //executa o evento de click no botão atualizar
    public void eventAtualizarProdutos(View v){
        //chamar o update no adapterProduto
        this.adpListaProdutos.atualizar(this.pdController.getListaProdutosController());
    }
}
