package br.com.hydraairlines.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.hydraairlines.dao.TelefoneEmpresaDAO;
import br.com.hydraairlines.to.TelefoneEmpresaTO;

public class TelefoneEmpresaBO {
	
	TelefoneEmpresaDAO ed = null;
	
	public List<TelefoneEmpresaTO> listar() throws SQLException{
		ed = new TelefoneEmpresaDAO();
		
		return ed.select();
	}
	
	public TelefoneEmpresaTO listar(int id){
		ed = new TelefoneEmpresaDAO();
		
		return ed.select(id);
	}
	
	public boolean atualiza(TelefoneEmpresaTO tto) throws SQLException{
		ed = new TelefoneEmpresaDAO();
		return ed.update(tto);
	}
	
	public boolean remover(int id) throws SQLException{
		ed= new TelefoneEmpresaDAO();
		return ed.delete(id);
	}
}
