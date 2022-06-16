package app;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class FuncionarioDAO {

	private Connection con;

	public FuncionarioDAO(Connection conexao) {
		this.con = conexao;
	}

	public void adiciona(Funcionario funcionario) {
		String insercao = "insert into funcionario " +
				"(codUnico, nome, senha)" +
				" values (?,?,?)";
		try {

			PreparedStatement stmt = con.prepareStatement(insercao);

			stmt.setInt(1, funcionario.getCodigoUnico());
			stmt.setString(2, funcionario.getNome());
			stmt.setString(3, funcionario.getSenha());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
	}


	// METODO PRA CHECAR SE EXISTE ALGUM FUNCIONARIO COM ESSE CODIGO
	public boolean checarFuncionario(int usuario){
		for(int indice = 0; indice<getLista().size(); indice++){
			if( getLista().get(indice).getCodigoUnico() == usuario ){
				return true;
			}
		}
		return false;
	}

	// CHECAR SENHA
	public boolean checarSenha(String senha) {
		for(int indice = 0; indice<getLista().size(); indice++){
			if( getLista().get(indice).getSenha().equals(senha)){
				return true;
			}
		}
		return false;
	}


	public ArrayList<Funcionario> getLista() {
        try {
            ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
            PreparedStatement stmt = this.con.prepareStatement("select * from funcionario");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // criando o objeto Funcionario
                Funcionario funcionario = new Funcionario();
				funcionario.setCodigoUnico(rs.getInt("codUnico"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setSenha(rs.getString("senha"));
                // adicionando o funcionario à lista
                funcionarios.add(funcionario);
            }
            rs.close();
            stmt.close();
            return funcionarios;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}