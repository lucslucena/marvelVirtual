package br.com.lucas.marvelvirtual;

public class Produto {
    private long id;
    private String nome;
    private int qtdEstoque;
    private int edicao;
    private double preco;

    public Produto(){
    }

    public long getId(){
        return this.id;
    }
    public void setId(long id){
        this.id = id;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public int getQtdEstoque(){
        return this.qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque){
        this.qtdEstoque = qtdEstoque;
    }

    public int getEdicao(){
        return this.edicao;
    }

    public void setEdicao(int edicao){
        this.edicao = edicao;
    }

    public double getPreco(){
        return this.preco;
    }

    public void setPreco(double preco){
        this.preco = preco;
    }

    public String toString(){
        return this.nome + " - " + this.id;
    }

}
