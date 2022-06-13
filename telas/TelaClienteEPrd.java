package telas;
import app.*;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TelaClienteEPrd extends JFrame {

    private SpinnerNumberModel model;
    private JSpinner qtdEscolher;
    private JButton btnVoltar;
    private JButton btnAdicionar;
    private Cantina cantina;
    private JComboBox<Item> comboBprodutos;
    private ArrayList<Item> produtos;
    private JLabel texto;

    private Container painel;
    private GridLayout layout;
    private DefaultListCellRenderer listRenderer;
    private Image image = new ImageIcon("image/logo3.png").getImage();

    private ArrayList<ItemVendido> carrinho;
    private int cod_venda;

    // TELA CLIENTE ESCOLHE PRODUTO
    public TelaClienteEPrd(int cod_venda, ArrayList<ItemVendido> carrinho) {

        this.cod_venda = cod_venda;
        this.carrinho = carrinho;

        // OBJETOS APP
        cantina = new Cantina();
        produtos = cantina.getEstoque().produtosDisponiveis();

        //
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.out.println("Look and Feel not set");
        }

        // LAYOUT E PAINEL
        painel = getContentPane();
        layout = new GridLayout(5, 1, 0, 3);

        // CRIANDO COMPONENTES

        listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        comboBprodutos = criaComboBox(produtos);
        comboBprodutos.setRenderer(listRenderer);

        int qtdPrimeiroItem = getItemDiffZero(produtos);
        if (qtdPrimeiroItem == 0) {
            model = new SpinnerNumberModel(0, 0, 0, 1);
        } else {
            model = new SpinnerNumberModel(1, 1, qtdPrimeiroItem, 1);
        }

        qtdEscolher = new JSpinner(model);
        btnVoltar = new JButton("Voltar");
        btnAdicionar = new JButton("Adicionar");
        texto = new JLabel("Escolha Um Produto", SwingConstants.CENTER);
        texto.setFont(new Font("Monospace", Font.BOLD, 20));
        // PADDINGS
        ((JComponent) painel).setBorder(new EmptyBorder(55, 55, 55, 55));

        // SETANDO INFORMAÇÕES DA TELA
        setSize(400, 350);
        setTitle("COMPRAR");
        setVisible(true);
        setResizable(false);
        painel.setLayout(layout);
        setDefaultCloseOperation(TelaEscolha.EXIT_ON_CLOSE);
        setIconImage(image);
        setLocationRelativeTo(null);
        setFont(new Font("Monospace", Font.BOLD, 20));

        // ADICIONANDO ELEMENTOS
        painel.add(texto);
        painel.add(comboBprodutos);
        painel.add(qtdEscolher);
        painel.add(btnAdicionar);
        painel.add(btnVoltar);
        // EVENTOS
        btnVoltar.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        acaoBtnVoltar(cod_venda, carrinho);
                    }
                });

        comboBprodutos.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {

                if (ie.getStateChange() == ItemEvent.SELECTED) {

                    int valorMax = mudaQuantidadeItem(ie.getItem().toString());
                    model.setMaximum(valorMax);
                    model.setValue(1);
                    if (valorMax == 0) {
                        model.setValue(0);
                    }
                }
            }
        });

        btnAdicionar.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        JOptionPane.showMessageDialog(painel, "Produto Adicionado!");
                        cantina.getEstoque().atualizarEstoque();
                        adicionaItemCarrinho(
                                Integer.parseInt(comboBprodutos.getSelectedItem().toString().substring(0, 1)),
                                cod_venda, (int) model.getValue());
                    }
                });

    }

    // ADICIONA ITEM NO CARRINHO E SE JÁ TIVER SÓ ALTERA A QUANTIDADE
    private boolean adicionaItemCarrinho(int cod_item, int cod_venda, int quantidade) {
        ItemVendido itemVendido = new ItemVendido(cod_item, cod_venda, quantidade);
        if (quantidade > 0) {

            for (int indice = 0; indice < carrinho.size(); indice++) {
                if (carrinho.get(indice).getCod_prod() == itemVendido.getCod_prod()) {
                    carrinho.get(indice)
                            .setQuantidade(carrinho.get(indice).getQuantidade() + itemVendido.getQuantidade());
                    return true;
                }
            }

            this.carrinho.add(itemVendido);
        }
        return false;
    }

    private void acaoBtnVoltar(int cod_venda, ArrayList<ItemVendido> carrinho) {
        JFrame telaCliente = new TelaClienteCpm(cod_venda, carrinho);
        this.dispose();
        telaCliente.setVisible(true);
    }

    // CRIA O COMBO BOX A PARTIR DO ARRAYLIST
    private JComboBox<Item> criaComboBox(ArrayList<Item> produtos) {
        comboBprodutos = new JComboBox<Item>();
        for (int i = 0; i < produtos.size(); i++) {

            comboBprodutos.addItem(produtos.get(i));
        }

        return comboBprodutos;
    }

    // PEGA O CÓDIGO DO ITEM DE UMA STRING E TRANSFORMA EM INTEIRO
    private int mudaQuantidadeItem(String codigo) {
        int quantidade = -1;
        int codigoInt = Integer.parseInt(codigo.substring(0, 1));
        for (int indice = 0; indice < produtos.size(); indice++) {
            if (produtos.get(indice).getCodigo() == codigoInt) {
                quantidade = produtos.get(indice).getQuantidade();
            }
        }

        return quantidade;
    }

    // FUNÇÃO QUE VERIFICA QUAL O PRIMEIRO ITEM COM QUANTIDADE DIFERENTE DE 0

    private int getItemDiffZero(ArrayList<Item> produtos) {
        for (int indice = 0; indice < produtos.size(); indice++) {
            if (produtos.get(0).getQuantidade() != 0) {
                return produtos.get(0).getQuantidade();
            }
        }
        return 0;
    }

}
