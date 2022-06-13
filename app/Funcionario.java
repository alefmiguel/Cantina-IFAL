package app;

public class Funcionario {
	private int codigoUnico;
	private String nome;
	private String senha;

	public Funcionario() {
	}

	public Funcionario(int codigo, String nome, String senha) {
		this.codigoUnico = codigo;
		this.nome = nome;
		this.senha = senha;
	}

	// SETS

	public void setCodigoUnico(int codigoUnico) {
		this.codigoUnico = codigoUnico;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	// GETS

	public int getCodigoUnico() {
		return codigoUnico;
	}

	public String getNome() {
		return nome;
	}

	public String getSenha() {
		return senha;
	}


	@Override
	public String toString() {
		
		return "Nome: "+this.nome+" | Código: "+this.codigoUnico;
	}
}