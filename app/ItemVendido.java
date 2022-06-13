package app;

public class ItemVendido {
    private int cod_venda;
    private int cod_prod;
    private int quantidade;
    private int id = 1;

    public ItemVendido (){
        
    }
    
    public ItemVendido (int cod_prod, int cod_venda, int quantidade){
        if(cod_venda < 0 || cod_prod < 0 || quantidade < 0){
            throw new IllegalArgumentException("\nTodos os argumentos devem ser maior que 0!");
        }
        ItemVendidoDAO itemVendidoDAO = new ItemVendidoDAO();
        this.cod_venda = cod_venda;
        this.cod_prod = cod_prod;
        this.quantidade = quantidade;
        this.id = itemVendidoDAO.codigoUnico(this.id);
    }


    // GETTERS

    public int getCod_prod() {
        return cod_prod;
    }

    public int getCod_venda() {
        return cod_venda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getId() {
        return id;
    }

    //SETTER

    public void setCod_prod(int cod_prod) {
        this.cod_prod = cod_prod;
    }

    public void setCod_venda(int cod_venda) {
        this.cod_venda = cod_venda;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setID(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        
        return "Codigo-produto: "+this.cod_prod+" | codigo-venda: "+this.cod_venda + " | quantidade: "+this.quantidade;
    }
}
