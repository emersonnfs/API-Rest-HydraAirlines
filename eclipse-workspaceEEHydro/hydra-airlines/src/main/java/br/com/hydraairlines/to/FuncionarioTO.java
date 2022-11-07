package br.com.hydraairlines.to;

public class FuncionarioTO {
	private int idFuncionario;
	private String usuario;
	private String senha;
	
	public FuncionarioTO() {
		
	}

	public FuncionarioTO(int idFuncionario, String usuario, String senha) {
		super();
		this.idFuncionario = idFuncionario;
		this.usuario = usuario;
		this.senha = senha;
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}