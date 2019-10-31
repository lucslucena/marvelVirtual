package br.com.lucas.marvelvirtual;

import java.util.Date;
import java.util.List;

public class Venda {

    private long id;
    private Date dataVenda;
    private List<ItemCarrinho> itensVenda;

    public Venda(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public List<ItemCarrinho> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<ItemCarrinho> itensVenda) {
        this.itensVenda = itensVenda;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", dataVenda=" + dataVenda +
                '}';
    }
}
