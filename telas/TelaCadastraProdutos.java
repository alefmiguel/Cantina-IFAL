package telas;
import app.*;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;



public class TelaCadastraProdutos extends JFrame {

	private Connection conexao;

    private Cantina cantina;
	private JLabel txtNome;
    private JTextField inputNome;
    private JLabel txtDescricao;
	private JTextField inputDesc;
    private JLabel txtPrecoCompra;
    private JTextField inputPrecoCompra;
    private JLabel txtPrecoVenda;
    private JTextField inputPrecoVenda;


    private JButton botaoCadastrar;
	private JButton botaoVoltar;

	private Container painel;
	private GridLayout layout;
	
	private Image image = new ImageIcon("image/logo3.png").getImage();
	// private Funcionario adm = new Funcionario("admin", "admin");

	public TelaCadastraProdutos(Connection conexao)  {
		this.conexao = conexao;
        this.cantina = new Cantina(this.conexao);

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			System.out.println("Look and Feel not set");
		}

		// LAYOUT E PAINEL
		painel = getContentPane();

        // SIM COLOQUEI AS ROWS ERRADO DE PROPOSITO PRA ELE QUEBRAR PQ GOSTEI DELE QUEBRADO
		layout = new GridLayout(9, 2, 0, 5); // (rows, cols, gapcols, gapRows)

		// COMPONENTES EM SI
		txtNome = new JLabel("Nome do Produto: ");
        inputNome = new JTextField();
        txtDescricao = new JLabel("Descrição do Produto: ");
        inputDesc = new JTextField();
        txtPrecoCompra = new JLabel("Preço de Compra: ");
        inputPrecoCompra = new JTextField();
        txtPrecoVenda = new JLabel("Preço de Venda: ");
        inputPrecoVenda = new JTextField();
        botaoCadastrar = new JButton("Cadastrar");
        botaoVoltar = new JButton("Voltar");


		// FONTE
		Font fonte = new Font("Monospace", Font.BOLD, 12);
		txtNome.setFont(fonte);
        txtDescricao.setFont(fonte);
        txtPrecoCompra.setFont(fonte);
        txtPrecoVenda.setFont(fonte);

		// PADDINGS
		((JComponent) painel).setBorder(new EmptyBorder(25, 25, 25, 25));

		// Configurações padrões da tela
		setSize(400, 350);
		setTitle("Cadastrar Produtos");
		setVisible(true);
		setResizable(false);
		painel.setLayout(layout);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(image);
		setLocationRelativeTo(null);

		// Adicionando componentes
		
        painel.add(txtNome);
        painel.add(inputNome);
        painel.add(txtDescricao);  
        painel.add(inputDesc);
        painel.add(txtPrecoCompra);
        painel.add(inputPrecoCompra);
        painel.add(txtPrecoVenda);
        painel.add(inputPrecoVenda);
        painel.add(botaoVoltar);
        painel.add(botaoCadastrar);

        // EVENTOS
		botaoCadastrar.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						verificaDados();
                        acaoBotaoVoltar();
					}
				});

		botaoVoltar.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						acaoBotaoVoltar();
					}
				});

		
		

	}

	private void acaoBotaoVoltar() {
		JFrame telaAdm = new TelaAdm(this.conexao);
		this.dispose();
		telaAdm.setVisible(true);
	}

	private void verificaDados(){
        try {
            String nome = inputNome.getText();
            String desc = inputDesc.getText();
            float preco_compra = Float.parseFloat(inputPrecoCompra.getText());
            float preco_venda = Float.parseFloat(inputPrecoVenda.getText());
            if(nome.length() !=  0 && desc.length() != 0){
                cantina.adicionarItem(nome, desc, preco_venda, preco_compra);
                JOptionPane.showMessageDialog(painel, "Item adicionado com sucesso!");
            }else{
                JOptionPane.showMessageDialog(painel, "Não é permitido campo vazio!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(painel, "Dados inválidos!");
        }
    }
}
