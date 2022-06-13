package telas;
import app.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.text.ParseException;

public class TelaLogin extends JFrame {

	private JLabel txtUsuario;
	// private JTextField inputUsuario;
	private JTextField inputUsuario;
	private JButton botaoEnviar;
	private Container painel;
	private GridLayout layout;
	private JLabel txtSenha;
	private JPasswordField inputSenha;
	private JButton botaoVoltar;
	private Image image = new ImageIcon("image/logo3.png").getImage();
	private FuncionarioDAO funcDAO;
	// private Funcionario adm = new Funcionario("admin", "admin");

	public TelaLogin()  {

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			System.out.println("Look and Feel not set");
		}

		// LAYOUT E PAINEL
		painel = getContentPane();
		layout = new GridLayout(7, 1, 0, 3); // (rows, cols, gapcols, gapRows)

		// COMPONENTES EM SI
		txtUsuario = new JLabel("Usuário");

		// INPUT SÓ COM NÚMEROS
	
		inputUsuario = new JTextField(6);
		txtSenha = new JLabel("Senha");
		inputSenha = new JPasswordField(6);
		botaoEnviar = new JButton("Entrar");
		botaoVoltar = new JButton("Voltar");
		funcDAO = new FuncionarioDAO();
		// FONTE
		Font fonte = new Font("Monospace", Font.BOLD, 12);
		txtUsuario.setFont(fonte);
		txtSenha.setFont(fonte);

		// PADDINGS
		((JComponent) painel).setBorder(new EmptyBorder(25, 25, 25, 25));

		// Configurações padrões da tela
		setSize(400, 350);
		setTitle("Login");
		setVisible(true);
		setResizable(false);
		painel.setLayout(layout);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(image);
		setLocationRelativeTo(null);

		// Adicionando componentes
		painel.add(txtUsuario);
		painel.add(inputUsuario);
		painel.add(txtSenha);
		painel.add(inputSenha);
		painel.add(new JLabel(""));
		painel.add(botaoEnviar);
		painel.add(botaoVoltar);

		botaoEnviar.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						botaoLogarActionPerformed(e);
					}
				});

		botaoVoltar.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						voltarActionPerformed(e);
					}
				});

		
		// https://stackhowto.com/how-to-make-jtextfield-accept-only-numbers/
		inputUsuario.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();  // if it's not a number, ignore the event
				}
			}
		});

	}

	private void voltarActionPerformed(ActionEvent evt) {
		JFrame telaLogin = new TelaEscolha();
		this.dispose();
		telaLogin.setVisible(true);
	}

	private void botaoLogarActionPerformed(ActionEvent evt) {
		String usuario = inputUsuario.getText();
		String senha = String.valueOf(inputSenha.getPassword());

		if(usuario.length() != 0 && senha.length() != 0){
			if (funcDAO.checarFuncionario(Integer.parseInt(usuario)) && funcDAO.checarSenha(senha)) {
				JFrame telaAdm = new TelaAdm();
				this.dispose();
				telaAdm.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos");
			}
		}
	}
}
