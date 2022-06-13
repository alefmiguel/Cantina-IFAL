import app.*;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaEscolha extends JFrame {

	private JButton botaoADM;
	private JButton botaoCliente;
	private JLabel texto;
	private Container painel;
	private GridLayout layout;
	private Image image = new ImageIcon("image/logo3.png").getImage();

	public TelaEscolha() {

		//
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			System.out.println("Look and Feel not set");
		}

		// LAYOUT E PAINEL
		painel = getContentPane();
		layout = new GridLayout(3, 1, 0, 3);

		// CRIANDO COMPONENTES
		botaoADM = new JButton("Administrador");
		botaoCliente = new JButton("Cliente");
		texto = new JLabel("Escolha um tipo", SwingConstants.CENTER);
		texto.setFont(new Font("Monospace", Font.BOLD, 20));

		// PADDINGS
		((JComponent) painel).setBorder(new EmptyBorder(25, 25, 25, 25));

		// SETANDO INFORMAÇÕES DA TELA
		setSize(400, 350);
		setTitle("Tela de Escolha");
		setVisible(true);
		setResizable(false);
		painel.setLayout(layout);
		setDefaultCloseOperation(TelaEscolha.EXIT_ON_CLOSE);
		setIconImage(image);
		setLocationRelativeTo(null);

		// ADICIONANDO ELEMENTOS
		painel.add(texto);
		painel.add(botaoADM);
		painel.add(botaoCliente);

		botaoADM.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						acaoBtnAdm(event);
					}
				});

		botaoCliente.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						acaoBtnCliente(event);
					}
				});

	}

	private void acaoBtnAdm(ActionEvent event) {
		JFrame telaLogin = new TelaLogin();
		this.dispose();
		telaLogin.setVisible(true);
	}

	private void acaoBtnCliente(ActionEvent event) {
		JFrame telaCliente = new TelaCliente();
		this.dispose();
		telaCliente.setVisible(true);
	}
}
