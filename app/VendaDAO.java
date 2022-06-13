package app;

import connections.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class VendaDAO {
    private Connection con;

	public VendaDAO (){
		this.con = ConnectionFactory.getConnection();
	}


    // METODO QUE ADICIONA ITENS AO BANDO DE DADOS
	public void adiciona(Venda venda) {
		String insercao = "insert into venda " +
				"(cod_venda,data_venda, desconto, forma_pagamento)" +
				" values (?,?,?,?)";
		try {

			PreparedStatement stmt = con.prepareStatement(insercao);

			stmt.setInt(1, venda.getCod_venda());
			stmt.setDate(2, venda.getData_venda());
			stmt.setDouble(3, venda.getDesconto());
			stmt.setString(4, venda.getForma_pagamento());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
	}

	// ADICIONAR TOTAL DA VENDA 
	public void atualizarTotalVenda(int cod_venda, Double total_venda) {

		String updateTotalVenda = 	"UPDATE venda SET "+
									"total_venda = ? "+
									"WHERE cod_venda = ? ";
		try {
			
			PreparedStatement stmt = con.prepareStatement(updateTotalVenda);
			stmt.setDouble(1, total_venda);
			stmt.setInt(2, cod_venda);
			
			stmt.executeUpdate();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
	}


    // METODO QUE RETORNA UMA LISTA DE OBJETOS VENDA
    public ArrayList<Venda> getListaVenda() {
        try {
            ArrayList<Venda> listaVendidos = new ArrayList<Venda>();
            PreparedStatement stmt = this.con.prepareStatement("select * from venda");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // criando o objeto Funcionario
                Venda venda = new Venda();
				venda.setCod_venda(rs.getInt("cod_venda"));
				venda.setData_venda(rs.getDate("data_venda"));
				venda.setDesconto(rs.getDouble("desconto"));
				venda.setTotal_venda(rs.getDouble("total_venda"));
				venda.setForma_pagamento(rs.getString("forma_pagamento"));

                // adicionando o funcionario à lista
                listaVendidos.add(venda);
            }
            rs.close();
            stmt.close();
            return listaVendidos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	// VERIFICAR SE EXISTE UMA TABELA COM ESSE CODIGO
	public int codigoVenda(int codigo_venda) {
        try {
			ArrayList<Integer> listaCodigos = new ArrayList<>();
            PreparedStatement stmt = this.con.prepareStatement("select cod_venda from venda");
            ResultSet rs = stmt.executeQuery();
			int cod_existente;

            while (rs.next()) {
                // criando o objeto Funcionario
				cod_existente = rs.getInt("cod_venda");
				listaCodigos.add(cod_existente);
                // adicionando o funcionario à lista
            }
			while(listaCodigos.contains(codigo_venda)){
				codigo_venda += 1;
			}
            rs.close();
            stmt.close();
			return codigo_venda;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	

	// PRODUTOS DE DETERMINADA VENDA
	
	public ArrayList<Item> getProdutosVenda(int cod_venda) {
		
        try {
            ArrayList<Item> produtos = new ArrayList<Item>();
            PreparedStatement stmt = this.con.prepareStatement(
			"SELECT DISTINCT produto.* " +
			" from produto, item_vendido, venda "+
			" where venda.cod_venda = ? and "+
			" item_vendido.cod_venda = venda.cod_venda and "+
			" item_vendido.cod_produto = produto.cod_produto;"
			);
            stmt.setInt(1, cod_venda);
			
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
                produtos.add(item);
            }
			
            rs.close();
            stmt.close();
            return produtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	public void excluir(int cod_venda) {
		String insercao = "delete from venda where cod_venda = ? ";
		try {

			PreparedStatement stmt = con.prepareStatement(insercao);

			stmt.setInt(1, cod_venda);

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
	}
}
