package br.com.lucas.marvelvirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import br.com.lucas.marvelvirtual.ConexionSQL;
import br.com.lucas.marvelvirtual.ProdutoController;

public class MainActivity extends AppCompatActivity {

    private Button btCadastroProduto;

    private Button btListaProduto;

    private Button btVendeProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSQL.getInstance(this);

        this.btCadastroProduto = (Button) findViewById(R.id.btCadastroProduto);

        this.btListaProduto = (Button) findViewById(R.id.btListaProduto);

        this.btVendeProduto = (Button) findViewById(R.id.btVender);

        this.btCadastroProduto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ActivityProduto.class);
                startActivity(i);

            }
        });

        this.btListaProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(MainActivity.this, ListaProdutosActivity.class);
                startActivity(itn);
            }
        });

        this.btVendeProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityVenda.class);
                startActivity(intent);
            }
        });
    }
}

