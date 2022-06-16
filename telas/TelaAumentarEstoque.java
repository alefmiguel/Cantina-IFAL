package telas;

import app.*;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.util.ArrayList;

public class TelaAumentarEstoque extends JFrame {

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

    private Connection conexao;

    // TELA CLIENTE ESCOLHE PRODUTO
    public TelaAumentarEstoque(Connection conexao) {
        this.conexao = conexao;

        // OBJETOS APP
        cantina = new Cantina(this.conexao);
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
        model = new SpinnerNumberModel(1, 1, 100000, 1);

        qtdEscolher = new JSpinner(model);
        btnVoltar = new JButton("Voltar");
        btnAdicionar = new JButton("Adicionar");
        texto = new JLabel("ADICIONAR ESTOQUE", SwingConstants.CENTER);
        texto.setFont(new Font("Monospace", Font.BOLD, 20));
        // PADDINGS
        ((JComponent) painel).setBorder(new EmptyBorder(55, 55, 55, 55));

        // SETANDO INFORMAÇÕES DA TELA
        setSize(400, 350);
        setTitle("ADICIONAR ESTOQUE");
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
                        acaoBtnVoltar();
                    }
                });

        btnAdicionar.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        if (comboBprodutos.getSelectedItem() != null) {
                            cantina.getEstoque().atualizarEstoque();
                            adicionaQuantidadeItem(comboBprodutos.getSelectedItem().toString(), (int) model.getValue());
                            JOptionPane.showMessageDialog(painel, "Produto Adicionado!");
                            cantina.getEstoque().atualizarEstoque();
                            acaoBtnVoltar();
                        }else{
                            JOptionPane.showMessageDialog(painel, "Nenhum Produto Disponível!");
                        }
                    }
                });

    }

    private void acaoBtnVoltar() {
        JFrame telaAdm = new TelaAdm(this.conexao);
        this.dispose();
        telaAdm.setVisible(true);
    }

    // CRIA O COMBO BOX A PARTIR DO ARRAYLIST
    private JComboBox<Item> criaComboBox(ArrayList<Item> produtos) {
        comboBprodutos = new JComboBox<Item>();
        for (int i = 0; i < produtos.size(); i++) {

            comboBprodutos.addItem(produtos.get(i));
        }

        return comboBprodutos;
    }

    // ADICIONA QUANTIDADE NO ITEM

    private void adicionaQuantidadeItem(String codigo, int quantidade) {
        int codigoInt = Integer.parseInt(codigo.substring(0, 1));
        try {
            for (int indice = 0; indice < produtos.size(); indice++) {
                if (produtos.get(indice).getCodigo() == codigoInt) {
                    cantina.comprarItem(produtos.get(indice).getCodigo(), quantidade);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
