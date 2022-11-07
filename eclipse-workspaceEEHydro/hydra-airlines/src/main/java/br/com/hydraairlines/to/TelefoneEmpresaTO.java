package br.com.hydraairlines.to;

public class TelefoneEmpresaTO extends Telefone {
	private int idEmpresa;

	public TelefoneEmpresaTO() {
		
	}

	public TelefoneEmpresaTO(int idEmpresa) {
		super();
		this.idEmpresa = idEmpresa;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
}
