package telas;
import app.*;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TelaMostraProdutos extends JFrame {

    private Cantina cantina = new Cantina();
    private JList jlist;
    private JScrollPane scrollPane;
    private DefaultListModel model;
    private JLabel texto;
    private Container painel; 
    private GridLayout layout;
    private DefaultListCellRenderer listRenderer;
    private Image image = new ImageIcon("image/logo3.png").getImage();
    
    private ArrayList<Item> produtos;

    public TelaMostraProdutos() {

        this.produtos = cantina.getEstoque().produtosDisponiveis();

        // 
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (Exception e) {
            System.out.println("Look and Feel not set");
        }


        // LAYOUT E PAINEL
        painel = getContentPane();
        layout = new GridLayout(2,1,0,3);

        // CRIANDO COMPONENTES
        scrollPane = new JScrollPane();
        jlist = criaJlist(produtos);
        scrollPane.setViewportView(jlist);
        texto = new JLabel("Produtos", SwingConstants.CENTER);
        texto.setFont(new Font("Monospace", Font.BOLD, 20));

        // PADDINGS 
        ((JComponent) painel).setBorder(new EmptyBorder(25,25,25,25));

        // SETANDO INFORMAÇÕES DA TELA
        setFont(new Font("Monospace", Font.BOLD, 20));
        setSize(400, 350);
        setTitle("Produtos");
        setVisible(true);
        setResizable(false);
        painel.setLayout(layout);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setIconImage(image);
        setLocationRelativeTo(null);

        // ADICIONANDO ELEMENTOS
        painel.add(texto);
        painel.add(scrollPane);
      

        
    }


    private JList<Item> criaJlist(ArrayList<Item> produtos) {
        model = new DefaultListModel<Item>();
        for (int i = 0; i < produtos.size(); i++) {
            model.addElement(produtos.get(i));
        }
        jlist = new JList<Item>(model);
        return jlist;
    }

}
