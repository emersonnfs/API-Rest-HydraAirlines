package br.com.hydraairlines.to;

public class TelefoneClienteTO extends Telefone {
	
	private int idCliente;
	
	public TelefoneClienteTO() {
		
	}

	public TelefoneClienteTO(int idCliente) {
		super();
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
}
