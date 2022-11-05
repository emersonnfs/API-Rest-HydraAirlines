package br.com.hydraairlines.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.hydraairlines.dao.ClienteDAO;
import br.com.hydraairlines.to.ClienteTO;

public class ClienteBO {
	ClienteDAO cd = null;
	
	public List<ClienteTO> listar() throws SQLException{
		cd = new ClienteDAO();
		
		return cd.select();
	}
	
	public ClienteTO listar(int id) {
		cd = new ClienteDAO();
		
		return cd.select(id);
	}
	
	public boolean cadastrar(ClienteTO cto) throws SQLException{
		cd=new ClienteDAO();
		return cd.insert(cto);
	}
	
	public boolean atualiza(ClienteTO cto) throws SQLException{
		cd= new ClienteDAO();
		return cd.update(cto);
	}
	
	public boolean remover(int id) throws SQLException{
		cd= new ClienteDAO();
		return cd.delete(id);
	}
}