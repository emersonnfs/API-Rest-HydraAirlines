package br.com.hydraairlines.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.hydraairlines.dao.AviaoDAO;
import br.com.hydraairlines.to.AviaoTO;

public class AviaoBO {
	AviaoDAO ad = null;
	
	public List<AviaoTO> listar() throws SQLException{
		ad = new AviaoDAO();
		return ad.select();
	}
	
	public AviaoTO listar(int id) throws SQLException{
		ad = new AviaoDAO();
		return ad.select(id);
	}
	
	public boolean cadastrar(AviaoTO ato) throws SQLException{
		ad=new AviaoDAO();
		return ad.insert(ato);
	}
	
	public boolean atualiza(AviaoTO ato) throws SQLException{
		ad=new AviaoDAO();
		return ad.update(ato);
	}
	
	public boolean remover(int id) throws SQLException{
		ad=new AviaoDAO();
		return ad.delete(id);
	}
}
