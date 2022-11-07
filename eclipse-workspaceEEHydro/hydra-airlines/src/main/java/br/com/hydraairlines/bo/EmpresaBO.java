package br.com.hydraairlines.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.hydraairlines.dao.EmpresaDAO;
import br.com.hydraairlines.to.EmpresaTO;

public class EmpresaBO {
	EmpresaDAO ed = null;
	
	public List<EmpresaTO> listar() throws SQLException{
		ed = new EmpresaDAO();
		
		return ed.select();
	}
	
	public EmpresaTO listar(int id) throws SQLException{
		ed = new EmpresaDAO();
		
		return ed.select(id);
	}
	
	public boolean cadastrar(EmpresaTO eto) throws SQLException{
		ed=new EmpresaDAO();
		return ed.insert(eto);
	}
	
	public boolean atualiza(EmpresaTO eto) throws SQLException{
		ed= new EmpresaDAO();
		return ed.uptdate(eto);
	}
	
	public boolean remover(int id) throws SQLException{
		ed= new EmpresaDAO();
		return ed.delete(id);
	}
}
