import java.util.ArrayList;

public class Estoque {
    private ItemDAO itemDAO = new ItemDAO();
    private ArrayList<Item> lista = itemDAO.getLista();
    private int quant_vendida;
    private int quant_comprada;
    private float investimento;
    private float lucro_bruto;
    private float lucro_liquido = lucro_bruto - investimento;

    public Estoque() {
        
    }

    public void atualizarEstoque(){
        this.lista = this.itemDAO.getLista();
    }

    public ItemDAO getItemDAO() {
        return itemDAO;
    }

    public void adicionarItem(Item item) {
        lista.add(item);
    }

    public void addQuant_comprada(int quantidade) {
        quant_comprada += quantidade;
    }

    public void addQuant_vendida(int quantidade) {
        quant_vendida += quantidade;
    }

    public void addIvestimento(float investimento) {
        this.investimento += investimento;
    }

    public void addLucro_bruto(float lucro_bruto) {
        this.lucro_bruto += lucro_bruto;
    }

    public int getQuant_comprada() {
        return quant_comprada;
    }

    public int getQuant_vendida() {
        return quant_vendida;
    }

    public int getQuantItens() {
        int quant = 0;

        for (Item item : lista) {
            quant += item.getQuantidade();
        }

        return quant;
    }

    public ArrayList<Item> ordenaEstoque(int i) throws Exception {
        if (i == 1){
            lista.sort((Item item1, Item item2) -> item1.getDescricao().compareTo(item2.getDescricao()));
            return lista;
        } else if (i == 2){
            lista.sort((Item item1, Item item2) -> item1.getQuantidade() < item2.getQuantidade() ? -1 : 1);
            return lista;
        } else {
            throw new Exception("\nParâmetro inválido");
        }
    }

    public String toStringCompra() {
        String saida = "";
        for (Item item : lista) {
            saida += item.toString() + "\n\n";
        }
        return saida;
    }
    
    public String toStringVenda() {
        String saida = "";
        for (Item item : lista) {
            saida += item.toString() + "\n";
        }
        return saida;
    }

    public ArrayList<Item> getLista() {
        return lista;
    }

    public float getInvestimento() {
        return investimento;
    }

    public float getLucro_liquido() {
        return lucro_liquido;
    }
}
