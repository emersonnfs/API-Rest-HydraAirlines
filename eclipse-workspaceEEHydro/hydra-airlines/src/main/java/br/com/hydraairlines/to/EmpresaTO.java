package br.com.hydraairlines.to;

public class EmpresaTO {
	private int id;
	private String nome;
	private String cnpj;
	private TelefoneEmpresaTO telefone;
	
	public EmpresaTO() {
		
	}

	public EmpresaTO(int id, String nome, String cnpj, TelefoneEmpresaTO telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.telefone = telefone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public TelefoneEmpresaTO getTelefone() {
		return telefone;
	}

	public void setTelefone(TelefoneEmpresaTO telefone) {
		this.telefone = telefone;
	}
	
	
}