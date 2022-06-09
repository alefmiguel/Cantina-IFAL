import connections.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
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
