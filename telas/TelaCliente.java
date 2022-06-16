package telas;

import app.*;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.util.ArrayList;

public class TelaCliente extends JFrame {

    private Cantina cantina;
    private ArrayList<String> formasPagamento;
    private JComboBox<String> escolhePagamento;
    private String pagamentoEscolhido;
    private JButton btnComprarProd;
    private JButton btnMostrarCardapio;
    private JButton btnSair;
    private Container painel;
    private GridLayout layout;
    private DefaultListCellRenderer listRenderer;
    private Image image = new ImageIcon("image/logo3.png").getImage();
    private Connection conexao;
    private ArrayList<ItemVendido> carrinho;

    public TelaCliente(Connection conexao) {
        this.conexao = conexao;
        this.cantina = new Cantina(this.conexao);
        this.carrinho = cantina.getCarrinho();

        //
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.out.println("Look and Feel not set");
        }

        // LAYOUT E PAINEL
        painel = getContentPane();
        layout = new GridLayout(4, 1, 0, 3);

        // CRIANDO COMPONENTES

        // COMBOBOX
        formasPagamento = new ArrayList<>();
        formasPagamento.add("Pix");
        formasPagamento.add("Cartão");
        formasPagamento.add("Dinheiro");
        pagamentoEscolhido = formasPagamento.get(0);
        listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        escolhePagamento = criaComboBox(formasPagamento);
        escolhePagamento.setRenderer(listRenderer);

        // texto = new JLabel("Escolha um tipo", SwingConstants.CENTER);
        btnComprarProd = new JButton("Comprar Produto");
        btnMostrarCardapio = new JButton("Mostrar Cardápio");
        btnSair = new JButton("Sair");

        // PADDINGS
        ((JComponent) painel).setBorder(new EmptyBorder(25, 25, 25, 25));

        // SETANDO INFORMAÇÕES DA TELA
        setFont(new Font("Monospace", Font.BOLD, 20));
        setSize(400, 350);
        setTitle("Cliente");
        setVisible(true);
        setResizable(false);
        painel.setLayout(layout);
        setDefaultCloseOperation(TelaEscolha.EXIT_ON_CLOSE);
        setIconImage(image);
        setLocationRelativeTo(null);

        // ADICIONANDO ELEMENTOS
        painel.add(btnComprarProd);
        painel.add(btnMostrarCardapio);
        painel.add(escolhePagamento);
        painel.add(btnSair);

        // EVENTOS
        btnSair.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        acaoBtnSair();
                    }
                });

        btnComprarProd.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        acaoBtnComprar(acaoCriaVenda(), carrinho);
                    }
                });

        escolhePagamento.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    pagamentoEscolhido = ie.getItem().toString();

                }
            }
        });

        btnMostrarCardapio.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        acaoMostrarCardapio();
                    }
                });

    }

    private JComboBox<String> criaComboBox(ArrayList<String> lista) {
        escolhePagamento = new JComboBox<String>();
        for (int i = 0; i < formasPagamento.size(); i++) {

            escolhePagamento.addItem(formasPagamento.get(i));
        }

        return escolhePagamento;
    }

    private void acaoBtnSair() {
        JFrame telaEscolha = new TelaEscolha();
        this.dispose();
        telaEscolha.setVisible(true);
    }

    private void acaoBtnComprar(int cod_venda, ArrayList<ItemVendido> carrinho) {
        TelaClienteCpm TelaComprar = new TelaClienteCpm(cod_venda, carrinho, this.conexao);
        this.dispose();
        TelaComprar.setVisible(true);
    }

    private int acaoCriaVenda() {
        int cod_venda = -1;
        try {
            cod_venda = cantina.criarVenda(pagamentoEscolhido);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cod_venda;
    }

    private void acaoMostrarCardapio() {
        TelaMostraProdutos telaMostraProdutos = new TelaMostraProdutos(this.conexao);
        telaMostraProdutos.setVisible(true);
    }
}
