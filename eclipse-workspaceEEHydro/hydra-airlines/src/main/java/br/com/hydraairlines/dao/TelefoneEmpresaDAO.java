package br.com.hydraairlines.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.hydraairlines.factory.ConnectionFactory;
import br.com.hydraairlines.to.TelefoneEmpresaTO;

public class TelefoneEmpresaDAO {
private Connection conn = null;
	
	public TelefoneEmpresaDAO() {
		this.conn = new ConnectionFactory().getConnection();
	}
	
	public List<TelefoneEmpresaTO> select() throws SQLException {
		List<TelefoneEmpresaTO> telempresas = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		String query = "SELECT * FROM T_HA_TEL_EMPRESA ORDER BY ID_TELEFONE";
		
		try {
			preparedStatement = conn.prepareStatement(query);
			ResultSet result = preparedStatement.executeQuery();
			
			TelefoneEmpresaTO c = null;
			
			while(result.next()) {
			c = new TelefoneEmpresaTO();
			c.setId(result.getInt("id_telefone"));
			c.setDdd(result.getInt("nr_ddd"));
			c.setDdi(result.getInt("nr_ddi"));
			c.setNrTel(result.getInt("nr_telefone"));
			c.setIdEmpresa(result.getInt("T_HA_EMPRESA_ID_EMPRESA"));
			
			telempresas.add(c);
			
		}
		}catch(SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		conn.close();
		return telempresas;
	}
	
	public TelefoneEmpresaTO select(int id) {
		PreparedStatement preparedStatement = null;
		String query = "SELECT * FROM T_HA_TEL_EMPRESA WHERE ID_TELEFONE = ?";
		
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			
			if(result != null) {
			TelefoneEmpresaTO c = new TelefoneEmpresaTO();
			while(result.next()) {
				c.setId(result.getInt("id_telefone"));
				c.setDdd(result.getInt("nr_ddd"));
				c.setDdi(result.getInt("nr_ddi"));
				c.setNrTel(result.getInt("nr_telefone"));
				c.setIdEmpresa(result.getInt("T_HA_EMPRESA_ID_EMPRESA"));
			}
			return c;
			
		}
		}catch(SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
	
	
	public boolean update(TelefoneEmpresaTO t) throws SQLException{
		PreparedStatement preparedStatement = null;
		String query = "update t_ha_tel_empresa set nr_ddd = ? , nr_ddi = ? , nr_telefone = ? where id_telefone = ?";
		try {
			preparedStatement = conn.prepareStatement(query);
			
			preparedStatement.setInt(1, t.getDdd());
			preparedStatement.setInt(2, t.getDdi());
			preparedStatement.setInt(3, t.getNrTel());
			preparedStatement.setInt(4, t.getId());
			
			preparedStatement.executeUpdate();
			//conn.commit();
			conn.close();
			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
		}
		conn.close();
		return false;
	}
	
	public boolean delete(int id) throws SQLException{
		PreparedStatement preparedStatement = null;
		String query = "delete from t_ha_tel_empresa where id_telefone = ?";
		try {
			preparedStatement = conn.prepareStatement(query);
			
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
			//conn.commit();
			conn.close();
			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
		}
		conn.close();
		return false;
	}
}
