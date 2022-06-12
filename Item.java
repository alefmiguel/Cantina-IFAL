public class Item{
    private int codigo;
    private String nome;
    private String descricao;
    private float preco_venda;
    private float preco_compra;
    private int quantidade;
    private int estoque_min;

    // CONSTRUTOR PARA FACILITAR O PROCESSO DE ADIÇÃO

    public Item(Item item){
        this.codigo = item.getCodigo();
        this.nome = item.getNome();
        this.descricao = item.getDescricao();
        this.preco_compra = item.getPreco_compra();
        this.preco_venda = item.getPreco_venda();
        this.estoque_min = item.getEstoque_min();
    }

    public Item(String nome, String descricao, float preco_venda, float preco_compra){
        this.nome = nome;
        this.descricao = descricao;
        this.preco_venda = preco_venda;
        this.preco_compra = preco_compra;
    }

    public Item(int codigo, String nome, String descricao, float preco_venda, float preco_compra, int estoque_min){
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.preco_venda = preco_venda;
        this.preco_compra = preco_compra;
        this.estoque_min = estoque_min;
    }

    public Item(){

    }
    // GETTERS

    public int getEstoque_min() {
        return estoque_min;
    }

    public int getCodigo() {
        return codigo;
    }

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

    //  SETTERS
    public void addQuantidade(int quantidade){
        this.quantidade += quantidade;
    }

    public void removeQuantidade(int quantidade){
        this.quantidade -= quantidade;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setEstoque_min(int estoque_min) {
        this.estoque_min = estoque_min;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco_compra(float preco_compra) {
        this.preco_compra = preco_compra;
    }

    public void setPreco_venda(float preco_venda) {
        this.preco_venda = preco_venda;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // MÉTODO TOSTRING P VENDA / COMPRA
    // public String toStringVenda(){
    //     return "Nome: " + nome + "\nDescricao: " + descricao + "\nPreco: " + preco_venda + "\n";
    // }

    // public String toStringCompra(){
    //     return "Nome: " + nome + "\nDescricao: " + descricao + "\nPreco de compra: " + preco_compra + "\n";
    // }

    @Override
    public String toString() {
        
        return "\n-------------\nNome: "+ this.nome + "\nCódigo: "+ this.codigo + "\nQuantidade: "+ this.quantidade + "\nPreço: "+ this.preco_venda + "\n";
    }
}
