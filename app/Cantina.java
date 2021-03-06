package app;
import java.sql.Connection;
import java.util.ArrayList;
import myexceptions.*;

public class Cantina {

    private Estoque estoque;
    private FuncionarioDAO funcDAO;
    private ArrayList<Funcionario> func_cadastrados;
    private VendaDAO vendaDAO;
    private ArrayList<ItemVendido> carrinho;
    private Connection conexao;

    public Cantina(Connection conexao){
        this.conexao = conexao;
        this.estoque = new Estoque(this.conexao);
        this.funcDAO = new FuncionarioDAO(this.conexao);
        this.vendaDAO = new VendaDAO(this.conexao);
        this.func_cadastrados = funcDAO.getLista();
        this.carrinho = new ArrayList<ItemVendido>();
    }

    public VendaDAO getVendaDAO() {
        return vendaDAO;
    }

    public void atualizaCadastrados(){
        this.func_cadastrados = funcDAO.getLista();
    }
    
    public FuncionarioDAO getFuncDAO() {
        return funcDAO;
    }

    public ArrayList<ItemVendido> getCarrinho() {
        return carrinho;
    }

    public ArrayList<Funcionario> getFunc_cadastrados() {
        return func_cadastrados;
    }

    
    public Estoque getEstoque() {
        return estoque;
    }

    public String mostraCadastrados(){
        String saida = "\nCADASTRADOS";

        for (int indice = 0; indice < func_cadastrados.size(); indice++) {
            saida += "\n["+(indice+1)+"] - " + func_cadastrados.get(indice);
        }
        
        return saida;
    }

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
            estoque.atualizarEstoque();
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
                return "\nItem comprado com sucesso!";
            }
        }
        return "\nItem não encontrado!";
    }

    public String venderItem(int codigo, int cod_venda ,int quantidade) throws Exception{
        
        for (Item item : estoque.getLista()) {
            if (item.getCodigo() == codigo) {
                if (quantidade < 0){
                    throw new ItemException("\nQuantidade inválida!");
                }else if(item.getQuantidade() < quantidade){
                    throw new ItemException("\nQuantidade insuficiente!");
                }
                
                ItemVendido vendido = new ItemVendido(codigo, cod_venda, quantidade);
                estoque.getItemVendidoDAO().adicionaItemVendido(vendido);
                estoque.atualizarEstoque();
                return "\nProduto vendido com sucesso!";
            }
        }
        return "\nItem não encontrado!";
    }

    public void venderListaCarrinho(ArrayList<ItemVendido> carrinho) throws Exception{
        for (int indice = 0; indice < carrinho.size(); indice ++) {
            venderItem(carrinho.get(indice).getCod_prod(), carrinho.get(indice).getCod_venda(), carrinho.get(indice).getQuantidade());
        }
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

    public int criarVenda(String forma_pagamento) throws Exception{
        Venda vendaAdicionar = new Venda(this.conexao, forma_pagamento);
        estoque.getVendaDAO().adiciona(vendaAdicionar);
        estoque.atualizarEstoque();
        return vendaAdicionar.getCod_venda();
    }

    public void atualizarTotalVenda(int cod_venda ){
        estoque.getVendaDAO().atualizarTotalVenda(cod_venda, estoque.totalPorVenda(cod_venda));
    }

    public void atualizarQuantidadeItemVendido(int cod_venda, int quantidade, int cod_prod){
        estoque.getItemVendidoDAO().atualizarQuantidadeItemVendido(cod_venda, cod_prod, quantidade);
    }
}
