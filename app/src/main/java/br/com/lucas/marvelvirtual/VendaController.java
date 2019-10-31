package br.com.lucas.marvelvirtual;

public class VendaController {

    private final VendaDAO vDAO;


    public VendaController(ConexionSQL vConexionSQLite) {
        vDAO = new VendaDAO(vConexionSQLite);
    }

    public long salvarVendaController(Venda vVenda){
        return vDAO.salvarVendaDAO(vVenda);
    }

    public boolean salvarItensVendaController(Venda vVenda){
        return vDAO.salvarItensVenda(vVenda);
    }

}
