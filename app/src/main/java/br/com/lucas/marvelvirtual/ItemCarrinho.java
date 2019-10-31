package br.com.lucas.marvelvirtual;

public class ItemCarrinho {

    private Long id;
    private String nome;
    private int qtdeSelecionada;
    private double preco;
    private double precoProdutoVenda;
    private Long idProduto;

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtdeSelecionada() {
        return qtdeSelecionada;
    }

    public void setQtdeSelecionada(int qtdeSelecionada) {
        this.qtdeSelecionada = qtdeSelecionada;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getPrecoProdutoVenda() {
        return precoProdutoVenda;
    }

    public void setPrecoProdutoVenda(double precoProdutoVenda) {
        this.precoProdutoVenda = precoProdutoVenda;
    }

    @Override
    public String toString() {
        return "ItemCarrinho{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", qtdeSelecionada=" + qtdeSelecionada +
                ", preco=" + preco +
                ", precoProdutoVenda=" + precoProdutoVenda +
                ", idProduto=" + idProduto +
                '}';
    }
}
