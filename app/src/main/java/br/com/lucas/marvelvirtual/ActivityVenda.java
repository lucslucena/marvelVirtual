package br.com.lucas.marvelvirtual;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityVenda extends AppCompatActivity {
    private Spinner spnProdutos;
    private List<Produto> listaProdutos;
    private ProdutoController pdController;
    private EditText qtdeItem;
    private TextView totalVenda;

    //carrinho
    private ListView lstCarrinho;
    private List<ItemCarrinho> listaICarrinho;
    private AdapterItensCarrinho adpItemCarrinho;

    //controller
    private VendaController vController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venda);

        this.vController = new VendaController(ConexionSQL.getInstance(this));



        //spinner
        this.pdController = new ProdutoController(ConexionSQL.getInstance(this));
        this.listaProdutos = this.pdController.getListaProdutosController();

        this.spnProdutos = (Spinner) findViewById(R.id.spProduto);
        ArrayAdapter<Produto> spProdutoAdapter = new ArrayAdapter<Produto>(this, android.R.layout.simple_spinner_item, this.listaProdutos);

        this.spnProdutos.setAdapter(spProdutoAdapter);
        // fim spinner

        this.qtdeItem = (EditText) this.findViewById(R.id.qtdeProduto);
        this.totalVenda = (TextView) this.findViewById(R.id.tvTotalVenda);


        //variaveis do carrinho e objetos
        this.lstCarrinho = (ListView) this.findViewById(R.id.ltProduto);
        this.listaICarrinho = new ArrayList<>();
        this.adpItemCarrinho = new AdapterItensCarrinho(ActivityVenda.this, this.listaICarrinho);

        this.lstCarrinho.setAdapter(this.adpItemCarrinho);


        this.lstCarrinho.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                final ItemCarrinho iCarrinho = (ItemCarrinho) adpItemCarrinho.getItem(position);

                AlertDialog.Builder janelaEscolha = new AlertDialog.Builder(ActivityVenda.this);
                janelaEscolha.setTitle("Selecione: ");
                janelaEscolha.setMessage("Remover o item " + iCarrinho.getNome() + "?");

                janelaEscolha.setNegativeButton("Não", null);

                janelaEscolha.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        boolean excluir = false;

                        excluir = adpItemCarrinho.removeItemCarrinho(position);

                        double vendaTotal = calcularTotalVenda(listaICarrinho);
                        atualizarValorTotalVenda(vendaTotal);


                        if(!excluir){
                            Toast.makeText(ActivityVenda.this, "Não foi possível excluir item do carrinho", Toast.LENGTH_LONG).show();
                        }

                    }
                });

                janelaEscolha.create().show();


            }
        });



    }

    /*
    clicar no botão para add ao carrinho
     */
    public void eventAddProduto(View v){

        ItemCarrinho iCarrinho = new ItemCarrinho();
        Produto pSelecionado = (Produto) this.spnProdutos.getSelectedItem();

        int qtdeProduto = 0;
        if(this.qtdeItem.getText().toString().equals("")){
            qtdeProduto = 1;
        }else{
            qtdeProduto = Integer.parseInt(this.qtdeItem.getText().toString());
        }

        iCarrinho.setNome(pSelecionado.getNome());
        iCarrinho.setQtdeSelecionada(qtdeProduto);
        iCarrinho.setIdProduto(pSelecionado.getId());
        iCarrinho.setPreco(pSelecionado.getPreco());
        iCarrinho.setPrecoProdutoVenda(iCarrinho.getPreco() * iCarrinho.getQtdeSelecionada());

        this.adpItemCarrinho.addItemCarrinho(iCarrinho);

        double vendaTotal = this.calcularTotalVenda(this.listaICarrinho);
        this.atualizarValorTotalVenda(vendaTotal);

    }

    /*
    clicar no botão de finalizar venda
     */
    public void eventFecharVenda(View v){
        Venda vendaFechar = this.criarVenda();


        if(this.salvarVenda(vendaFechar) == true){
            Toast.makeText(this, "Venda Fechada!", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Não foi possível fechar a venda", Toast.LENGTH_LONG).show();
        }
    }

    private double calcularTotalVenda(List<ItemCarrinho> listaItensCarrinho){

        double totalVenda = 0.0d;

        for (ItemCarrinho iCarrinho: listaItensCarrinho) {
            totalVenda += iCarrinho.getPrecoProdutoVenda();
        }

        return totalVenda;
    }

    private void atualizarValorTotalVenda(double aValorTotal){
        this.totalVenda.setText(String.valueOf(aValorTotal));
    }

    private Venda criarVenda(){
        Venda venda = new Venda();
        venda.setDataVenda(new Date());
        venda.setItensVenda(this.listaICarrinho);

        return venda;
    }

    private boolean salvarVenda(Venda v){

        long idVenda = vController.salvarVendaController(v);

        if(idVenda > 0){

            v.setId(idVenda);
            if(vController.salvarItensVendaController(v)){
                Toast.makeText(this, "Venda realizada!", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, "Não foi possível realizar a venda, tente novamente!", Toast.LENGTH_LONG).show();
            }
            return true;
        }
        return false;
    }


}