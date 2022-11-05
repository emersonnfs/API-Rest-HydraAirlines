package br.com.hydraairlines.to;

public abstract class Telefone {
	private int id;
	private int ddd;
	private int ddi;
	private int nrTel;
	
	public Telefone() {
		
	}
	
	public Telefone(int id, int ddd, int ddi, int nrTel) {
		super();
		this.id = id;
		this.ddd = ddd;
		this.ddi = ddi;
		this.nrTel = nrTel;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDdd() {
		return ddd;
	}
	public void setDdd(int ddd) {
		this.ddd = ddd;
	}
	public int getDdi() {
		return ddi;
	}
	public void setDdi(int ddi) {
		this.ddi = ddi;
	}
	public int getNrTel() {
		return nrTel;
	}
	public void setNrTel(int nrTel) {
		this.nrTel = nrTel;
	}
	
	
	
}