import connections.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemVendidoDAO {
    
    
	private Connection con;

	public ItemVendidoDAO (){
		this.con = ConnectionFactory.getConnection();
	}


    // METODO PRA ADICIONAR O ITEM VENDIDO

	public void adicionaItemVendido(ItemVendido itemVendido) {
		String insercao = "insert into item_vendido " +
				"(id, cod_produto, cod_venda, quantidade)" +
				" values (?,?,?,?)";
		try {

			PreparedStatement stmt = con.prepareStatement(insercao);

			stmt.setInt(1, itemVendido.getId());
			stmt.setInt(2, itemVendido.getCod_prod());
			stmt.setInt(3, itemVendido.getCod_venda());
			stmt.setInt(4, itemVendido.getQuantidade());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
	}


    // METODO QUE RETORNA UMA LISTA DE OBJETOS DA TABELA PRODUTO
	public ArrayList<ItemVendido> getListaVendidos() {
        try {
            ArrayList<ItemVendido> vendidos = new ArrayList<ItemVendido>();
            PreparedStatement stmt = this.con.prepareStatement("select * from item_vendido");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // criando o objeto Funcionario
                ItemVendido itemVendido = new ItemVendido();
				itemVendido.setID(rs.getInt("id"));
				itemVendido.setCod_venda(rs.getInt("cod_venda"));
				itemVendido.setCod_prod(rs.getInt("cod_produto"));
				itemVendido.setQuantidade(rs.getInt("quantidade"));
                // adicionando o funcionario à lista
                vendidos.add(itemVendido);
            }
            rs.close();
            stmt.close();
            return vendidos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	// ATUALIZA QUANTIDADE DO ITEM DE MESMA COD_VENDA

	public void atualizarQuantidadeItemVendido(int cod_venda,int cod_prod, int quantidade) {

		String updateTotalVenda = 	"UPDATE item_vendido SET "+
									"quantidade = ? "+
									"WHERE cod_venda = ? and cod_produto = ? ";
		try {
			
			PreparedStatement stmt = con.prepareStatement(updateTotalVenda);
			stmt.setDouble(1, quantidade);
			stmt.setInt(2, cod_venda);
			stmt.setInt(3, cod_prod);
			
			stmt.executeUpdate();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
	}


	public int codigoUnico(int codigoUnico) {
        try {
			ArrayList<Integer> listaCodigos = new ArrayList<>();
            PreparedStatement stmt = this.con.prepareStatement("select id from item_vendido");
            ResultSet rs = stmt.executeQuery();
			int cod_existente;

            while (rs.next()) {
                // criando o objeto Funcionario
				cod_existente = rs.getInt("id");
				listaCodigos.add(cod_existente);
                // adicionando o funcionario à lista
            }
			while(listaCodigos.contains(codigoUnico)){
				codigoUnico += 1;
			}
            rs.close();
            stmt.close();
			return codigoUnico;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


	// RETORNA TODOS OS VENDIDOS DE DETERMINADO PRODUTO
	public ArrayList<ItemVendido> getListaVendidos(int cod_item) {
        try {
            ArrayList<ItemVendido> vendidos = new ArrayList<ItemVendido>();
            PreparedStatement stmt = this.con.prepareStatement(
			" SELECT item_vendido.* "+
			" from item_vendido, produto "+
			" where produto.cod_produto = ? and item_vendido.cod_produto = ? ");
			stmt.setInt(1, cod_item);
			stmt.setInt(2, cod_item);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // criando o objeto Funcionario
                ItemVendido itemVendido = new ItemVendido();
				itemVendido.setID(rs.getInt("id"));
				itemVendido.setCod_venda(rs.getInt("cod_venda"));
				itemVendido.setCod_prod(rs.getInt("cod_produto"));
				itemVendido.setQuantidade(rs.getInt("quantidade"));
                // adicionando o funcionario à lista
                vendidos.add(itemVendido);
            }
            rs.close();
            stmt.close();
            return vendidos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	// RETORNA TODOS OS ITENS VENDIDOS QUE ESTÃO EM UMA DETERMINADA VENDA
	public ArrayList<ItemVendido> getVendidoList(int cod_venda) {
		
        try {
            ArrayList<ItemVendido> vendidos = new ArrayList<ItemVendido>();
            PreparedStatement stmt = this.con.prepareStatement(	
				" SELECT item_vendido.* "+
				" from item_vendido, venda "+
				" where venda.cod_venda = ? "+
				" and item_vendido.cod_venda = venda.cod_venda ");

			stmt.setInt(1, cod_venda);
            ResultSet rs = stmt.executeQuery();
			
            while (rs.next()) {
                // criando o objeto Funcionario
                ItemVendido itemVendido = new ItemVendido();
				itemVendido.setID(rs.getInt("id"));
				itemVendido.setCod_venda(rs.getInt("cod_venda"));
				itemVendido.setCod_prod(rs.getInt("cod_produto"));
				itemVendido.setQuantidade(rs.getInt("quantidade"));
                // adicionando o funcionario à lista
                vendidos.add(itemVendido);
            }
            rs.close();
            stmt.close();
            return vendidos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
