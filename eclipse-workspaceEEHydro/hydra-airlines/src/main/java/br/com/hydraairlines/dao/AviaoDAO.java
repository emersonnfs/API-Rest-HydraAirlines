package br.com.hydraairlines.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.hydraairlines.factory.ConnectionFactory;
import br.com.hydraairlines.to.AviaoTO;

public class AviaoDAO {
private Connection conn = null;
	
	public AviaoDAO() {
		this.conn = new ConnectionFactory().getConnection();
	}
	
	public List<AviaoTO> select() throws SQLException {
		List<AviaoTO> avioes = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		String query = "SELECT * FROM T_HA_AVIOES ORDER BY ID_AVIAO";
		
		try {
			preparedStatement = conn.prepareStatement(query);
			ResultSet result = preparedStatement.executeQuery();
			
			AviaoTO c = null;
			
			while(result.next()) {
			c = new AviaoTO();
			c.setId(result.getInt("id_aviao"));
			c.setNome(result.getString("nm_aviao"));
			c.setPorte(result.getString("ds_porte_aviao").charAt(0));
			c.setQtdAssentos(result.getInt("nr_assentos"));
			
			avioes.add(c);
			
		}
		}catch(SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		conn.close();
		return avioes;
	}
	
	public AviaoTO select(int id) {
		PreparedStatement preparedStatement = null;
		String query = "SELECT * FROM T_HA_AVIOES WHERE ID_AVIAO = ?";
		
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			
			if(result != null) {
			AviaoTO c = new AviaoTO();
			while(result.next()) {
				c.setId(result.getInt("id_aviao"));
				c.setNome(result.getString("nm_aviao"));
				c.setPorte(result.getString("ds_porte_aviao").charAt(0));
				c.setQtdAssentos(result.getInt("nr_assentos"));
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
	
	public boolean insert(AviaoTO a) throws SQLException {
		PreparedStatement preparedStatement = null;
		String query = "select max(id_aviao) as id_aviao from t_ha_avioes";
		preparedStatement = conn.prepareStatement(query);
    	int id_aviao = -1;
    	ResultSet result = preparedStatement.executeQuery();
    	while(result.next()){
    		id_aviao = result.getInt("id_aviao");
         }
        id_aviao++;
    	preparedStatement = null;
		query ="insert into t_ha_avioes (id_aviao, nm_aviao, ds_porte_aviao, nr_assentos) values (?,?,?,?)";
		try {
	    	a.setId(id_aviao);
			preparedStatement = conn.prepareStatement(query);
			
			preparedStatement.setInt(1, a.getId());
			preparedStatement.setString(2,a.getNome());
			preparedStatement.setString(3, String.valueOf(a.getPorte()));
			preparedStatement.setInt(4, a.getQtdAssentos());
			
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
	
	public boolean update(AviaoTO a) throws SQLException{
		PreparedStatement preparedStatement = null;
		String query = "update t_ha_avioes set nm_aviao = ? , ds_porte_aviao = ? , nr_assentos = ? where id_aviao = ?";
		try {
			preparedStatement = conn.prepareStatement(query);
			
			preparedStatement.setString(1, a.getNome());
			preparedStatement.setString(2, String.valueOf(a.getPorte()));
			preparedStatement.setInt(3, a.getQtdAssentos());
			preparedStatement.setInt(4, a.getId());
			
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
		String query = "delete from t_ha_avioes where id_aviao = ?";
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
