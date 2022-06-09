public class Item{
    private String nome;
    private String descricao;
    private float preco_venda;
    private float preco_compra;
    private int quantidade;

    // CONSTRUTOR PARA FACILITAR O PROCESSO DE ADIÇÃO
    public Item(String nome, String descricao, float preco_venda, float preco_compra){
        this.nome = nome;
        this.descricao = descricao;
        this.preco_venda = preco_venda;
        this.preco_compra = preco_compra;
    }

    // GETTERS e SETTERS
    public String getNome() {
        return nome;
    }

    public float getPreco_venda(){
        return preco_venda;
    }

    public float getPreco_compra(){
        return preco_compra;
    }

    public int getQuantidade(){
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void addQuantidade(int quantidade){
        this.quantidade += quantidade;
    }

    public void removeQuantidade(int quantidade){
        this.quantidade -= quantidade;
    }

    // MÉTODO TOSTRING P VENDA / COMPRA
    public String toStringVenda(){
        return "Nome: " + nome + "\nDescricao: " + descricao + "\nPreco: " + preco_venda + "\n";
    }

    public String toStringCompra(){
        return "Nome: " + nome + "\nDescricao: " + descricao + "\nPreco de compra: " + preco_compra + "\n";
    }
}
