package app;
import myexceptions.ItemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ItemDAO {

	private Connection con;

	public ItemDAO (Connection conexao){
		this.con = conexao;
	}

	// METODO QUE ADICIONA ITENS AO BANDO DE DADOS
	public void adiciona(Item item) {
		String insercao = "insert into produto " +
				"(nome, descricao, preco_compra, preco_venda, qtd_comprada, estoque_min)" +
				" values (?,?,?,?,?,?)";
		try {

			PreparedStatement stmt = con.prepareStatement(insercao);

			// stmt.setInt(1, item.getCodigo());
			stmt.setString(1, item.getNome());
			stmt.setString(2, item.getDescricao());
			stmt.setFloat(3, item.getPreco_compra());
			stmt.setFloat(4, item.getPreco_venda());
			stmt.setInt(5, item.getQuantidade());
			stmt.setInt(6, item.getEstoque_min());

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
}
