package entidade;

public class Produto {
    private String nome;
    private double preco;
    private int qde;

    public Produto() {
    }

    public Produto(String nome, double preco, int qde) {
        this.nome = nome;
        this.preco = preco;
        this.qde = qde;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQde() {
        return qde;
    }

    public void setQde(int qde) {
        this.qde = qde;
    }
    
    public double total(){
        return preco * qde;
    }
    
}
