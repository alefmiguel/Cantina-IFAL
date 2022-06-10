import connections.*;
import myexceptions.ItemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ItemDAO {

	private Connection con;

	public ItemDAO (){
		this.con = ConnectionFactory.getConnection();
	}

	// METODO QUE ADICIONA ITENS AO BANDO DE DADOS
	public void adiciona(Item item) {
		String insercao = "insert into produto " +
				"(cod_produto, nome, descricao, preco_compra, preco_venda, qtd_comprada, estoque_min)" +
				" values (?,?,?,?,?,?,?)";
		try {

			PreparedStatement stmt = con.prepareStatement(insercao);

			// stmt.setInt(1, item.getCodigo());
			stmt.setString(2, item.getNome());
			stmt.setString(3, item.getDescricao());
			stmt.setFloat(4, item.getPreco_compra());
			stmt.setFloat(5, item.getPreco_venda());
			stmt.setInt(5, item.getQuantidade());
			stmt.setInt(7, item.getEstoque_min());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
	}

	// METODO QUE RETORNA UMA LISTA DE OBJETOS DA TABELA PRODUTO
	public ArrayList<Item> getLista() {
        try {
            ArrayList<Item> produto = new ArrayList<Item>();
            PreparedStatement stmt = this.con.prepareStatement("select * from produto");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // criando o objeto Funcionario
                Item item = new Item();
				item.setCodigo(rs.getInt("cod_produto"));
				item.setNome(rs.getString("nome"));
				item.setDescricao(rs.getString("descricao"));
				item.setPreco_compra(rs.getFloat("preco_compra"));
				item.setPreco_venda(rs.getFloat("preco_venda"));
				item.setQuantidade(rs.getInt("qtd_comprada"));
				item.setEstoque_min(rs.getInt("estoque_min"));
                // adicionando o funcionario à lista
                produto.add(item);
            }
            rs.close();
            stmt.close();
            return produto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	public void atualizarQuantidadeSQL(int codProduto, int quantidade) {

		String updateQuantidade = 	"UPDATE produto SET "+
									"qtd_comprada = ? "+
									"WHERE cod_produto = ? ";
		try {
			
			PreparedStatement stmt = con.prepareStatement(updateQuantidade);
			stmt.setInt(1, quantidade);
			stmt.setInt(2, codProduto);
			
			stmt.executeUpdate();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
	}


	public String atualizaQuantidade(int codProduto, int quantidade) throws ItemException{
		
		try {
            PreparedStatement stmt = this.con.prepareStatement( 	"select * "+
																	"from produto "+
																	"where cod_produto = ?");
			stmt.setInt(1, codProduto);
            ResultSet rs = stmt.executeQuery();
			
			Item item = new Item();
            while (rs.next()) {
				
                // criando o objeto Funcionario
				item.setCodigo(rs.getInt("cod_produto"));
				item.setNome(rs.getString("nome"));
				item.setDescricao(rs.getString("descricao"));
				item.setPreco_compra(rs.getFloat("preco_compra"));
				item.setPreco_venda(rs.getFloat("preco_venda"));
				item.setQuantidade(rs.getInt("qtd_comprada"));
				item.setEstoque_min(rs.getInt("estoque_min"));
                

            }
			
            rs.close();
            stmt.close();
			int resultado = item.getQuantidade() + quantidade;
			
            if((resultado) >= 0){
				atualizarQuantidadeSQL(item.getCodigo(), resultado);
				return "\nItem atualizado!";
			}else{
				throw new ItemException("\nQuantidade insuficiente!");
			}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

	}
	// public static void insert(Item item) {
	// 	Connection conn = null;
	// 	PreparedStatement stmt = null;
	// 	try {
	// 		conn = Connectio.getConnection();
	// 		stmt = conn.prepareStatement("INSERT INTO item (item_name, item_description, item_price, item_image) VALUES (?, ?, ?, ?)");
	// 		stmt.setString(1, item.getItem_name());
	// 		stmt.setString(2, item.getItem_description());
	// 		stmt.setDouble(3, item.getItem_price());
	// 		stmt.setString(4, item.getItem_image());
	// 		stmt.executeUpdate();
	// 	} catch (SQLException e) {
	// 		e.printStackTrace();
	// 	} finally {
	// 		ConnectionManager.close(conn, stmt, null);
	// 	}
	// }

	// public static void delete(int item_id) {
	// 	Connection conn = null;
	// 	PreparedStatement stmt = null;
	// 	try {
	// 		conn = ConnectionManager.getConnection();
	// 		stmt = conn.prepareStatement("DELETE FROM item WHERE item_id = ?");
	// 		stmt.setInt(1, item_id);
	// 		stmt.executeUpdate();
	// 	} catch (SQLException e) {
	// 		e.printStackTrace();
	// 	} finally {
	// 		ConnectionManager.close(conn, stmt, null);
	// 	}
	// }


    // public List<Item> getLista() {
    //     try {
    //         List<Item> funcionarios = new ArrayList<Item>();
    //         PreparedStatement stmt = this.con.prepareStatement("select * from funcionario");
    //         ResultSet rs = stmt.executeQuery();

    //         while (rs.next()) {
    //             // criando o objeto Funcionario
    //             Item item = new Item();
	// 			funcionario.setCodigoUnico(rs.getInt("codUnico"));
	// 			funcionario.setNome(rs.getString("nome"));
	// 			funcionario.setSenha(rs.getString("senha"));
    //             // adicionando o funcionario à lista
    //             funcionarios.add(funcionario);
    //         }
    //         rs.close();
    //         stmt.close();
    //         return funcionarios;
    //     } catch (SQLException e) {
    //         throw new RuntimeException(e);
    //     }
    // }
}
