package br.com.hydraairlines.to;

import java.util.ArrayList;

public class ClienteTO {
	private int id;
	private String nome;
	private String data;
	private String email;
	private ArrayList<TelefoneClienteTO> telefone;
	
	public ClienteTO() {
		
	}

	public ClienteTO(int id, String nome, String data, String email, ArrayList<TelefoneClienteTO> telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.data = data;
		this.email = email;
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<TelefoneClienteTO> getTelefone() {
		return telefone;
	}

	public void setTelefone(ArrayList<TelefoneClienteTO> telefone) {
		this.telefone = telefone;
	}
	
}	