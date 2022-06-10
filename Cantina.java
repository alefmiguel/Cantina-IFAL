import myexceptions.*;

public class Cantina {
    private Estoque estoque = new Estoque();
    

    public void verCardapioVenda(){
        System.out.println(estoque.toStringVenda());
    }

    public void verCardapioCompras(){
        System.out.println(estoque.toStringCompra());
    }

    public void adicionarItem(String nome, String descricao, float preco_venda, float preco_compra) throws Exception{
        if (preco_venda <= 0 || preco_compra <= 0) {
            throw new Exception("\nPreço inválido!");
        } else if (preco_venda < preco_compra) {
            throw new Exception("\nPreço de venda não pode ser menor que o preço de compra!");
        } else {
            Item itemAdicionar = new Item(nome, descricao, preco_venda, preco_compra);
            estoque.getItemDAO().adiciona(itemAdicionar);
        }
        System.out.println("\nItem adicionado com sucesso!");
    }

    public String comprarItem(int codigo, int quantidade) throws Exception{
        for (Item item : estoque.getLista()) {
            if (item.getCodigo() == codigo) {
                if (quantidade < 0){
                    throw new ItemException("\nQuantidade inválida!");
                }
                estoque.getItemDAO().atualizaQuantidade(codigo, quantidade);
                estoque.atualizarEstoque();
                estoque.addIvestimento(item.getPreco_compra() * quantidade);
                return "\nItem comprado com sucesso!";
            }
        }
        return "\nItem não encontrado!";
    }

    public String venderItem(int codigo, int quantidade) throws Exception{
        for (Item item : estoque.getLista()) {
            if (item.getCodigo() == codigo) {
                if (quantidade < 0){
                    throw new ItemException("\nQuantidade inválida!");
                }
                quantidade *= -1;
                estoque.getItemDAO().atualizaQuantidade(codigo, quantidade);
                estoque.atualizarEstoque();
                estoque.addQuant_vendida(quantidade);
                estoque.addLucro_bruto(item.getPreco_venda() * quantidade);
                return "\nProduto vendido com sucesso!";
            }
        }
        return "\nItem não encontrado!";
    }

    public String resumoLucro(){
        if (estoque.getLucro_liquido() > 0) {
            return "\nLucro Liquido: R$ " + estoque.getLucro_liquido() + "\n";
        } else {
            return "\nPrejúizo: R$ " + estoque.getLucro_liquido() + "\n";
        }
    }

    public String resumoItens(int ordenacao){
        try {
            estoque.ordenaEstoque(ordenacao);
        } catch (Exception e) {
            return e.getMessage();
        }

        String resumo = "";
        for (Item item : estoque.getLista()) {
            resumo += item.getNome() + " - " + item.getQuantidade() + "\n";
        }

        return resumo;
    }

    public String resumoItensBaixo() {
        String resumo = "";
        for (Item item : estoque.getLista()) {
            if (item.getQuantidade() < 50) {
                resumo += item.getNome() + " - " + item.getQuantidade() + "\n";
            }
        }

        return resumo;
    }
}
