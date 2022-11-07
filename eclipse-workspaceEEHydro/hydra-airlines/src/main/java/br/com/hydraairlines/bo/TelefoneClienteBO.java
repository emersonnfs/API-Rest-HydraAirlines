package br.com.hydraairlines.bo;

import java.sql.SQLException;
import java.util.List;
import br.com.hydraairlines.dao.TelefoneClienteDAO;
import br.com.hydraairlines.to.TelefoneClienteTO;

public class TelefoneClienteBO {
	TelefoneClienteDAO ed = null;
	
	public List<TelefoneClienteTO> listar() throws SQLException{
		ed = new TelefoneClienteDAO();
		
		return ed.select();
	}
	
	public TelefoneClienteTO listar(int id){
		ed = new TelefoneClienteDAO();
		
		return ed.select(id);
	}
	
	public boolean atualiza(TelefoneClienteTO tto) throws SQLException{
		ed= new TelefoneClienteDAO();
		return ed.update(tto);
	}
	
	public boolean remover(int id) throws SQLException{
		ed= new TelefoneClienteDAO();
		return ed.delete(id);
	}
}