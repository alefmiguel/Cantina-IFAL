package telas;
import app.*;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaAdm extends JFrame {

	private JButton btnCadastraProd;
	private JButton btnAumentaProd;
	private JButton btnMostraResumo;
	private JButton btnCadastraFunc;
	private JButton btnVerCadastrados;
	private JButton btnSair;
	private JLabel texto;
	private Container painel;
	private GridLayout layout;
	private Image image = new ImageIcon("image/logo3.png").getImage();
	private Cantina cantina;


	public TelaAdm() {

		cantina = new Cantina();

		//
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			System.out.println("Look and Feel not set");
		}

		// LAYOUT E PAINEL
		painel = getContentPane();
		layout = new GridLayout(7, 1, 0, 3);

		// CRIANDO COMPONENTES
		texto = new JLabel("OPÇÕES ADMINISTRADOR", SwingConstants.CENTER);
		btnCadastraProd = new JButton("Cadastrar Produto");
		btnAumentaProd = new JButton("Aumentar Estoque");
		btnMostraResumo = new JButton("Mostrar Resumo");
		btnCadastraFunc = new JButton("Cadastrar Funcionário");
		btnVerCadastrados = new JButton("Ver Funcionários");
		btnSair = new JButton("Sair");

		// PADDINGS
		((JComponent) painel).setBorder(new EmptyBorder(25, 25, 25, 25));

		// SETANDO INFORMAÇÕES DA TELA
		texto.setFont(new Font("Monospace", Font.BOLD, 20));
		setSize(400, 450);
		setTitle("ADMINISTRADOR");
		setVisible(true);
		setResizable(false);
		painel.setLayout(layout);
		setDefaultCloseOperation(TelaEscolha.EXIT_ON_CLOSE);
		setIconImage(image);
		setLocationRelativeTo(null);

		// ADICIONANDO ELEMENTOS
		painel.add(texto);
		painel.add(btnCadastraProd);
		painel.add(btnAumentaProd);
		painel.add(btnMostraResumo);
		painel.add(btnCadastraFunc);
		painel.add(btnVerCadastrados);
		painel.add(btnSair);

		btnSair.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						acaoBtnSair(event);
					}
				});

		btnCadastraProd.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						acaoBtnCadastra();
					}
				});

		btnAumentaProd.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						acaoBtnAumentaProd();
					}
				});

		btnMostraResumo.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						mostrarResumo();
					}
				});

		btnCadastraFunc.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						acaoBtnCadastraFunc();
					}
				});

		btnVerCadastrados.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						mostraCadastrados();
					}
				});
		
				
	}

	private void acaoBtnSair(ActionEvent event) {
		TelaLogin telaLogin = new TelaLogin();
			this.dispose();
			telaLogin.setVisible(true);
	}

	private void acaoBtnCadastra(){
		JFrame telaCadastra = new TelaCadastraProdutos();
		this.dispose();
		telaCadastra.setVisible(true);
	}

	private void acaoBtnAumentaProd(){
		JFrame telaAumenta = new TelaAumentarEstoque();
		this.dispose();
		telaAumenta.setVisible(true);
	}

	private void mostrarResumo(){
		JOptionPane.showMessageDialog(painel, cantina.resumoLucro());
	}

	private void acaoBtnCadastraFunc(){
		JFrame telaCadastraFunc = new TelaCadastraFunc();
		this.dispose();
		telaCadastraFunc.setVisible(true);
	}

	private void mostraCadastrados(){
		TelaMostraFunc telaMostraFunc = new TelaMostraFunc();
		telaMostraFunc.setVisible(true);
	}
}
