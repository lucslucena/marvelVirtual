package br.com.lucas.marvelvirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static br.com.lucas.marvelvirtual.R.*;

public class EditarProdutosActivity extends AppCompatActivity {

    private EditText idProduto;
    private EditText nomeProduto;
    private EditText prcProduto;
    private EditText edicao;
    private EditText qtProduto;

    private Button btSalvarAlteracoes;

    private Produto p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_editar_produtos);

        this.idProduto = (EditText) findViewById(R.id.idProduto);
        this.nomeProduto = (EditText) findViewById(id.nomeProduto);
        this.prcProduto = (EditText) findViewById(id.prcProduto);
        this.edicao = (EditText) findViewById(id.edicao);
        this.qtProduto = (EditText) findViewById(id.qtProduto);

        this.btSalvarAlteracoes = (Button) findViewById(id.btSalvarDados);

        Bundle bDadosProduto = getIntent().getExtras();

        Produto p = new Produto();

        p.setId(bDadosProduto.getLong("id_produto"));
        p.setNome(bDadosProduto.getString("nome_produto"));
        p.setPreco(bDadosProduto.getDouble("preco_produto"));
        p.setEdicao(bDadosProduto.getInt("edicao_produto"));
        p.setQtdEstoque(bDadosProduto.getInt("qtEstoque_produto"));

        this.setDadosProduto(p);

        this.clickSalvarListener();

    }

    private void setDadosProduto(Produto p){
        this.idProduto.setText(String.valueOf(p.getId()));
        this.nomeProduto.setText(p.getNome());
        this.prcProduto.setText(String.valueOf(p.getPreco()));
        this.edicao.setText(String.valueOf(p.getEdicao()));
        this.qtProduto.setText(String.valueOf(p.getQtdEstoque()));
    }

    private Produto getDados(){
        this.p = new Produto();
        if(this.nomeProduto.getText().toString().isEmpty() == false){
            this.p.setNome(this.nomeProduto.getText().toString());
        }
        else{
            return null;
        }

        if(this.qtProduto.getText().toString().isEmpty() == false){
            int qtdeProduto = Integer.parseInt(this.qtProduto.getText().toString());
            this.p.setQtdEstoque(qtdeProduto);
        }
        else{
            return null;
        }

        if(this.edicao.getText().toString().isEmpty()== false){
            int edEdicao = Integer.parseInt(this.edicao.getText().toString());
            this.p.setEdicao(edEdicao);
        }
        else{
            return null;
        }
        if(prcProduto.getText().toString().isEmpty() == false){
            double precoProduto = Double.parseDouble(this.prcProduto.getText().toString());
            this.p.setPreco(precoProduto);
        }
        else{
            return null;
        }
        if(idProduto.getText().toString().isEmpty() == false){
            Long idNovo = Long.parseLong(this.idProduto.getText().toString());
            this.p.setId(idNovo);
        }
        else{
            return null;
        }
        return p;
    }

    private void clickSalvarListener(){

        this.btSalvarAlteracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Produto pAtualizar = getDados();
                Log.d("PRODUTO RECUPERADO", pAtualizar.toString());

                if(pAtualizar != null){
                    ProdutoController pcontroller = new ProdutoController(ConexionSQL.getInstance(EditarProdutosActivity.this));
                    boolean atualizou = pcontroller.atualizarProdutoController(pAtualizar);

                    if(atualizou == true){
                        Toast.makeText(EditarProdutosActivity.this, "Produto salvo!", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(EditarProdutosActivity.this, "Produto não foi salvo!", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(EditarProdutosActivity.this, "Todos os campos são obrigatórios!", Toast.LENGTH_LONG).show();                }
            }
        });

    }
}
