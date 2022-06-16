package telas;
import app.*;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;

public class TelaLoginNew extends JFrame {

	private JLabel txtUsuario, txtSenha;
	private JTextField inputUsuario;
	private JButton botaoEnviar, botaoVoltar;
	private Container painel;
	private GridBagLayout layout;
    private GridBagConstraints c;
	private JPasswordField inputSenha;
	private Image image = new ImageIcon("image/logo3.png").getImage();
	private Cantina cantina;


	public TelaLoginNew()  {


		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			System.out.println("Look and Feel not set");
		}

		// LAYOUT E PAINEL
		painel = getContentPane();
		layout = new GridBagLayout();
        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LINE_START;
        // PADDINGS
		// ((JComponent) painel).setBorder(new EmptyBorder(25, 25, 25, 25));

		// Configurações padrões da tela
		setSize(400, 350);
		setTitle("Login");
		setVisible(true);
		setResizable(false);
		painel.setLayout(layout);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(image);
		setLocationRelativeTo(null);

		// LABEL USUARIO

		txtUsuario = new JLabel("Usuário");
        c.gridx = 0; // COLS 
        c.gridy = 0; // ROWS
        painel.add(txtUsuario, c);
		// INPUT SÓ COM NÚMEROS
	

		inputUsuario = new JTextField(6);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        painel.add(inputUsuario, c);


        txtSenha = new JLabel("Senha");
        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.NONE;
        painel.add(txtSenha, c);


        inputSenha = new JPasswordField(6);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        painel.add(inputSenha, c);

		// botaoEnviar = new JButton("Entrar");
		// botaoVoltar = new JButton("Voltar");
		// cantina = new Cantina(this.conexao);
		// // FONTE
		// Font fonte = new Font("Monospace", Font.BOLD, 12);
		// txtUsuario.setFont(fonte);
		// txtSenha.setFont(fonte);

		

		// Adicionando componentes

		// botaoEnviar.addActionListener(
		// 		new ActionListener() {
		// 			public void actionPerformed(ActionEvent e) {
		// 				botaoLogarActionPerformed(e);
		// 			}
		// 		});

	// 	botaoVoltar.addActionListener(
	// 			new ActionListener() {
	// 				public void actionPerformed(ActionEvent e) {
	// 					voltarActionPerformed(e);
	// 				}
	// 			});

		
		// https://stackhowto.com/how-to-make-jtextfield-accept-only-numbers/
		inputUsuario.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();  // if it's not a number, ignore the event
				}
			}
		});

	// }

	// private void voltarActionPerformed(ActionEvent evt) {
	// 	JFrame telalogin = new TelaEscolha();
	// 	this.dispose();
	// 	telalogin.setVisible(true);
	// }

	// private void botaoLogarActionPerformed(ActionEvent evt) {
	// 	String usuario = inputUsuario.getText();
	// 	String senha = String.valueOf(inputSenha.getPassword());

	// 	if(usuario.length() != 0 && senha.length() != 0){
	// 		if (cantina.getFuncDAO().checarFuncionario(Integer.parseInt(usuario)) && cantina.getFuncDAO().checarSenha(senha)) {
	// 			JFrame telaAdm = new TelaAdm(this.conexao);
	// 			this.dispose();
	// 			telaAdm.setVisible(true);
	// 		} else {
	// 			JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos");
	// 		}
	// 	}
	// }
}
}