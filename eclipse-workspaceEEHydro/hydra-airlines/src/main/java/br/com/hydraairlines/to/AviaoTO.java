package br.com.hydraairlines.to;

public class AviaoTO {
	private int id;
	private String nome;
	private char porte;
	private int qtdAssentos;
	
	public AviaoTO() {
		
	}

	public AviaoTO(int id, String nome, char porte, int qtdAssentos) {
		super();
		this.id = id;
		this.nome = nome;
		this.porte = porte;
		this.qtdAssentos = qtdAssentos;
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

	public char getPorte() {
		return porte;
	}

	public void setPorte(char porte) {
		this.porte = porte;
	}

	public int getQtdAssentos() {
		return qtdAssentos;
	}

	public void setQtdAssentos(int qtdAssentos) {
		this.qtdAssentos = qtdAssentos;
	}
	
	
	
}
