package br.com.hydraairlines.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.hydraairlines.factory.ConnectionFactory;
import br.com.hydraairlines.to.TelefoneClienteTO;

public class TelefoneClienteDAO {
	private Connection conn = null;
	
	public TelefoneClienteDAO() {
		this.conn = new ConnectionFactory().getConnection();
	}
	
	public List<TelefoneClienteTO> select() throws SQLException {
		List<TelefoneClienteTO> telclientes = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		String query = "SELECT * FROM T_HA_TEL_CLIENTE ORDER BY ID_TELEFONE";
		
		try {
			preparedStatement = conn.prepareStatement(query);
			ResultSet result = preparedStatement.executeQuery();
			
			TelefoneClienteTO c = null;
			
			while(result.next()) {
				c = new TelefoneClienteTO();
				c.setId(result.getInt("id_telefone"));
				c.setDdd(result.getInt("nr_ddd"));
				c.setDdi(result.getInt("nr_ddi"));
				c.setNrTel(result.getInt("nr_telefone"));
				c.setIdCliente(result.getInt("T_HA_CLIENTE_ID_CLIENTE"));
				
				telclientes.add(c);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		conn.close();
		return telclientes;
	}
	
	public TelefoneClienteTO select(int id) {
		PreparedStatement preparedStatement = null;
		String query = "SELECT * FROM T_HA_TEL_CLIENTE WHERE ID_TELEFONE = ?";
		
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			
			if(result != null) {
			TelefoneClienteTO c = new TelefoneClienteTO();
			while(result.next()) {
				c.setId(result.getInt("id_telefone"));
				c.setDdd(result.getInt("nr_ddd"));
				c.setDdi(result.getInt("nr_ddi"));
				c.setNrTel(result.getInt("nr_telefone"));
				c.setIdCliente(result.getInt("T_HA_CLIENTE_ID_CLIENTE"));
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
	
	public boolean update(TelefoneClienteTO t) throws SQLException{
		PreparedStatement preparedStatement = null;
		String query = "update t_ha_tel_cliente set nr_ddd = ? , nr_ddi = ? , nr_telefone = ? where id_telefone = ?";
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
		String query = "delete from t_ha_tel_cliente where id_telefone = ?";
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
