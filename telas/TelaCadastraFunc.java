package telas;
import app.*;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;


public class TelaCadastraFunc extends JFrame {

    private Cantina cantina;
	private JLabel txtNome;
    private JTextField inputNome;
    private JLabel txtCodigo;
	private JTextField inputCodigo;
    private JLabel txtSenha;
    private JTextField inputSenha;


    private JButton botaoCadastrar;
	private JButton botaoVoltar;

	private Container painel;
	private GridLayout layout;
	
	private Connection conexao;
	private Image image = new ImageIcon("image/logo3.png").getImage();
	// private Funcionario adm = new Funcionario("admin", "admin");

	public TelaCadastraFunc(Connection conexao)  {

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
		layout = new GridLayout(7, 2, 0, 5); // (rows, cols, gapcols, gapRows)

		// COMPONENTES EM SI
		txtNome = new JLabel("Nome do Funcionário: ");
        inputNome = new JTextField();
        txtCodigo = new JLabel("Código de acesso do funcionário: ");
        inputCodigo = new JTextField();
        txtSenha = new JLabel("Senha do funcionário: ");
        inputSenha = new JTextField();

        botaoCadastrar = new JButton("Cadastrar");
        botaoVoltar = new JButton("Voltar");


		// FONTE
		Font fonte = new Font("Monospace", Font.BOLD, 12);
		txtNome.setFont(fonte);
        txtCodigo.setFont(fonte);
        txtSenha.setFont(fonte);


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
        painel.add(txtCodigo);  
        painel.add(inputCodigo);
        painel.add(txtSenha);
        painel.add(inputSenha);
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
            String senha = inputSenha.getText();
            int codigo = Integer.parseInt(inputCodigo.getText());
            if(nome.length() !=  0 && senha.length() != 0){
                cantina.getFuncDAO().adiciona(new Funcionario(codigo, nome ,senha));
                JOptionPane.showMessageDialog(painel, "Funcionário adicionado com sucesso!");
                cantina.atualizaCadastrados();
            }else{
                JOptionPane.showMessageDialog(painel, "Não é permitido campo vazio!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(painel, "Dados inválidos! Dados Corretos > Já existe esse código cadastrado!");
        } 
    }
}
