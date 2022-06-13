import app.*;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TelaClienteCpm extends JFrame {

    private JButton adicionarCarrinho;
    private JButton verCarrinho;
    private JButton btnVoltar;
    private JButton btnFinalizar;
    private JLabel texto;
    private Container painel; 
    private GridLayout layout;
    private Image image = new ImageIcon("image/logo3.png").getImage();
    private Cantina cantina;
    private int cod_venda;
    private ArrayList<ItemVendido> carrinho;

    public TelaClienteCpm(int cod_venda, ArrayList<ItemVendido> carrinho) {
        
        this.cod_venda = cod_venda;
        this.cantina = new Cantina();
        this.carrinho = carrinho;
        
        // 
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (Exception e) {
            System.out.println("Look and Feel not set");
        }


        // LAYOUT E PAINEL
        painel = getContentPane();
        layout = new GridLayout(5,1,0,3);

        // CRIANDO COMPONENTES
        texto =  new JLabel("Meu Carrinho", SwingConstants.CENTER);
        adicionarCarrinho = new JButton("Adicionar no Carrinho");
        verCarrinho = new JButton("Ver Carrinho");
        btnVoltar = new JButton("Cancelar");
        btnFinalizar = new JButton("Finalizar");

        // PADDINGS 
        ((JComponent) painel).setBorder(new EmptyBorder(25,25,25,25));

        // SETANDO INFORMAÇÕES DA TELA
        texto.setFont(new Font("Monospace", Font.BOLD, 20));
        setSize(400, 350);
        setTitle("Carrinho");
        setVisible(true);
        setResizable(false);
        painel.setLayout(layout);
        setDefaultCloseOperation(TelaEscolha.EXIT_ON_CLOSE);
        setIconImage(image);
        setLocationRelativeTo(null);

        // ADICIONANDO ELEMENTOS
        painel.add(texto);
        painel.add(adicionarCarrinho);
        painel.add(verCarrinho);
        painel.add(btnFinalizar);
        painel.add(btnVoltar);
        

        // EVENTOS
        btnVoltar.addActionListener(
            new ActionListener(){
              public void actionPerformed(ActionEvent event){
                excluirTabela();
                acaoBtnVoltar();
              }
            }
          );

        adicionarCarrinho.addActionListener(
            new ActionListener(){
              public void actionPerformed(ActionEvent event){
                acaoBtnComprar(cod_venda, carrinho);
              }
            }
          );

        verCarrinho.addActionListener(
            new ActionListener(){
              public void actionPerformed(ActionEvent event){
                acaoVerCarrinho();
              }
            }
          );

        btnFinalizar.addActionListener(
            new ActionListener(){
              public void actionPerformed(ActionEvent event){
                acaoBtnFinalizar(carrinho, cod_venda);
                acaoBtnVoltar();
              }
            }
          );

    }

    private void acaoBtnVoltar(){
        JFrame telaCliente = new TelaCliente();
        this.dispose();
        telaCliente.setVisible(true);
    }

    private void acaoBtnComprar(int cod_venda, ArrayList<ItemVendido> carrinho){
        
        JFrame telaCompra = new TelaClienteEPrd(cod_venda, carrinho);
        this.dispose();
        telaCompra.setVisible(true);
    }

    private void acaoVerCarrinho(){
      JOptionPane.showMessageDialog(painel, this.carrinho.toString());
    }

    private void acaoBtnFinalizar(ArrayList<ItemVendido> carrinho, int cod_venda){
      if(carrinho.size() > 0){
        try {
          cantina.venderListaCarrinho(carrinho);
          cantina.atualizarTotalVenda(cod_venda);
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
      }else{
        JOptionPane.showMessageDialog(painel, "Carrinho Vazio, Compra não realizada!");
        excluirTabela();
      }
    }

    public void excluirTabela(){
      cantina.getEstoque().getVendaDAO().excluir(cod_venda);
    }
}
