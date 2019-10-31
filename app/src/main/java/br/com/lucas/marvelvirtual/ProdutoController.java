package br.com.lucas.marvelvirtual;

import java.util.List;

public class ProdutoController {
    private final ProdutoDAO pDAO;

    public ProdutoController(ConexionSQL pConexion) {
        pDAO = new ProdutoDAO(pConexion);
    }

    public long salvaProdutoController(Produto p){
        return this.pDAO.salvaProdutoDAO(p);
    }

    public List<Produto> getListaProdutosController(){
        return this.pDAO.getListaProdutosDAO();
    }

    public boolean excluirProdutoController(long pidProduto){
        return this.pDAO.excluirProdutoDAO(pidProduto);
    }

    public boolean atualizarProdutoController(Produto p){
        return this.pDAO.atualizarProdutoDAO(p);
    }

}
