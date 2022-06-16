package telas;
import app.*;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.ArrayList;

public class TelaMostraFunc extends JFrame {

    private Cantina cantina;
    private JList<Funcionario> jlist;
    private JScrollPane scrollPane;
    private DefaultListModel<Funcionario> model;
    private JLabel texto;
    private Container painel; 
    private GridLayout layout;
    private Connection conexao;
    private Image image = new ImageIcon("image/logo3.png").getImage();
    
    private ArrayList<Funcionario> funcionarios;

    public TelaMostraFunc(Connection conexao) {
        this.conexao = conexao;
        this.cantina =  new Cantina(this.conexao);
        this.funcionarios = cantina.getFunc_cadastrados();

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
        jlist = criaJlist(funcionarios);
        scrollPane.setViewportView(jlist);
        texto = new JLabel("Cadastrados", SwingConstants.CENTER);
        texto.setFont(new Font("Monospace", Font.BOLD, 20));

        // PADDINGS 
        ((JComponent) painel).setBorder(new EmptyBorder(25,25,25,25));

        // SETANDO INFORMAÇÕES DA TELA
        setFont(new Font("Monospace", Font.BOLD, 20));
        setSize(400, 350);
        setTitle("Funcionários Cadastrados");
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


    private JList<Funcionario> criaJlist(ArrayList<Funcionario> funcionarios) {
        model = new DefaultListModel<Funcionario>();
        for (int i = 0; i < funcionarios.size(); i++) {
            model.addElement(funcionarios.get(i));
        }
        jlist = new JList<Funcionario>(model);
        return jlist;
    }

}
