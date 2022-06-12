import java.util.ArrayList;

public class Estoque {
    private ItemDAO itemDAO;
    private VendaDAO vendaDAO;
    private ItemVendidoDAO itemVendidoDAO;

    private ArrayList<Item> listaProdutos;
    // private ArrayList<ItemVendido> listaVendidos;
    private ArrayList<Venda> listaVendas;

    private int quant_vendida;
    private int quant_comprada;
    private float investimento;
    private float lucro_bruto;
    private float lucro_liquido;

    public Estoque() {
        this.itemDAO = new ItemDAO();
        this.vendaDAO = new VendaDAO();
        this.itemVendidoDAO = new ItemVendidoDAO();
        this.listaProdutos = produtosDisponiveis();
        this.listaVendas = vendaDAO.getListaVenda();
        this.investimento = calculaInvestimento();
        this.lucro_bruto = calculaLucroBruto();
        this.lucro_liquido = lucro_bruto - investimento;
    }

    //
    public void atualizarEstoque() {
        this.listaProdutos = produtosDisponiveis();
        // this.listaVendidos = this.itemVendidoDAO.getListaVendidos();
    }


    public Double totalPorVenda(int cod_venda) {
        atualizarEstoque();
        Double total = 0.;

        ArrayList<Item> produtosVenda = vendaDAO.getProdutosVenda(cod_venda);
        ArrayList<ItemVendido> itemVendido = itemVendidoDAO.getVendidoList(cod_venda);
        // PEGA OS PRODUTOS VENDIDOS
        for(int indice=0; indice<itemVendido.size(); indice++){
            for(int indice2 = 0; indice2<produtosVenda.size(); indice2++){
                
                if(itemVendido.get(indice).getCod_prod() == produtosVenda.get(indice2).getCodigo()){
                    total+= itemVendido.get(indice).getQuantidade() * produtosVenda.get(indice2).getPreco_venda();
                }
            }
        }
        
        return total;
    }

    public ArrayList<Item> produtosDisponiveis() {
        ArrayList<Item> listaProdutos = itemDAO.getLista();
        ArrayList<ItemVendido> listaVendidos = itemVendidoDAO.getListaVendidos();

        for(int indice = 0; indice < listaVendidos.size(); indice++) {
            for(int indice2 = 0; indice2 < listaProdutos.size(); indice2++){

                if(listaVendidos.get(indice).getCod_prod() == listaProdutos.get(indice2).getCodigo()){
                    listaProdutos.get(indice2).removeQuantidade(listaVendidos.get(indice).getQuantidade());}
            }
        }
        return listaProdutos;
    }


    // INVESTIMENTO

    public float calculaInvestimento(){
        ArrayList<Item> listaProdutos = itemDAO.getLista();
        float total = 0;
        for(int indice = 0; indice < listaProdutos.size();indice++){
            total += listaProdutos.get(indice).getQuantidade() * listaProdutos.get(indice).getPreco_compra();
        }
       
        return total;
    }


    public float calculaLucroBruto(){
        ArrayList<Venda> listaVendas = vendaDAO.getListaVenda();
        float total = 0;
        for(int indice = 0; indice < listaVendas.size();indice++){
            total += listaVendas.get(indice).getTotal_venda();
        }
        
        return total;
    }

    // GETTERS

    public ItemDAO getItemDAO() {
        return itemDAO;
    }

    public VendaDAO getVendaDAO() {
        return vendaDAO;
    }

    public ItemVendidoDAO getItemVendidoDAO() {
        return itemVendidoDAO;
    }

    public ArrayList<Item> getLista() {
        return listaProdutos;
    }

    public float getInvestimento() {
        return investimento;
    }

    public float getLucro_liquido() {
        return lucro_liquido;
    }

    public int getQuant_comprada() {
        return quant_comprada;
    }

    public int getQuant_vendida() {
        return quant_vendida;
    }

    public int getQuantItens() {
        int quant = 0;

        for (Item item : listaProdutos) {
            quant += item.getQuantidade();
        }

        return quant;
    }

    // SETTERS

    public void adicionarItem(Item item) {
        listaProdutos.add(item);
    }

    // public void addQuant_comprada(int quantidade) {
    //     quant_comprada += quantidade;
    // }

    // public void addQuant_vendida(int quantidade) {
    //     quant_vendida += quantidade;
    // }

    // public void addIvestimento(float investimento) {
    //     this.investimento += investimento;
    // }

    public void addLucro_bruto(float lucro_bruto) {
        this.lucro_bruto += lucro_bruto;
    }

    // OTHERS
    public ArrayList<Item> ordenaEstoque(int i) throws Exception {
        if (i == 1) {
            listaProdutos.sort((Item item1, Item item2) -> item1.getDescricao().compareTo(item2.getDescricao()));
            return listaProdutos;
        } else if (i == 2) {
            listaProdutos.sort((Item item1, Item item2) -> item1.getQuantidade() < item2.getQuantidade() ? -1 : 1);
            return listaProdutos;
        } else {
            throw new Exception("\nParâmetro inválido");
        }
    }

    public String toStringCompra() {
        String saida = "";

        for (int indice = 0; indice < listaProdutos.size(); indice++) {
            saida += "\n[" + (indice+1) + "] - " + listaProdutos.get(indice);
        }
        return saida;
    }

    public String toStringVenda() {
        String saida = "";
        for (Item item : listaProdutos) {
            saida += item.toString() + "\n";
        }
        return saida;
    }

    // ADICIONAR PRODUTOS VENDIDOS
    public void adicionarItemVendido() {

    }

}
