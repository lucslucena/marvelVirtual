package br.com.lucas.marvelvirtual;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import static br.com.lucas.marvelvirtual.R.*;

public class ActivityProduto extends AppCompatActivity {

    private EditText nomeProduto;
    private EditText qtProduto;
    private EditText edicao;
    private EditText prcProduto;
    private EditText idProduto; //codigo

    private Button btSalvaProduto;

    private Produto p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        nomeProduto = (EditText) findViewById(id.nomeProduto);
        qtProduto = (EditText) findViewById(id.qtProduto);
        edicao = (EditText) findViewById(id.edicao);
        prcProduto = (EditText) findViewById(id.prcProduto);
        idProduto = (EditText) findViewById(id.idProduto);

        btSalvaProduto = (Button) findViewById(id.btSalvaProduto);

        this.clickSalvarListener();

    }

    private void clickSalvarListener(){

            this.btSalvaProduto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Produto pCadastrar = getDados();
                    if(pCadastrar != null){
                        ProdutoController pcontroller = new ProdutoController(ConexionSQL.getInstance(ActivityProduto.this));
                        long idProdutoNovo = pcontroller.salvaProdutoController(pCadastrar);

                        if(idProdutoNovo > 0){
                            Toast.makeText(ActivityProduto.this, "Produto salvo!", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(ActivityProduto.this, "Produto não foi salvo!", Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(ActivityProduto.this, "Todos os campos são obrigatórios!", Toast.LENGTH_LONG).show();
                    }
                }
            });

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

}
