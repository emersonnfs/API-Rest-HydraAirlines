package br.com.hydraairlines.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.hydraairlines.factory.ConnectionFactory;
import br.com.hydraairlines.to.ClienteTO;
import br.com.hydraairlines.to.TelefoneClienteTO;

public class ClienteDAO {
	private Connection conn = null;
	
	public ClienteDAO() {
		this.conn = new ConnectionFactory().getConnection();
	}
	
	public List<ClienteTO> select() throws SQLException {
		List<ClienteTO> clientes = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		String query = "SELECT * FROM T_HA_CLIENTE ORDER BY ID_CLIENTE";
		
		try {
			preparedStatement = conn.prepareStatement(query);
			ResultSet result = preparedStatement.executeQuery();
			ClienteTO c = null;
			
			while(result.next()) {
			c = new ClienteTO();
			c.setId(result.getInt("id_cliente"));
			c.setNome(result.getString("nm_cliente"));
			c.setEmail(result.getString("ds_email"));
			c.setData(result.getString("dt_nascimento"));
			
			clientes.add(c);
			
		}
		}catch(SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		conn.close();
		return clientes;
	}
	
	public ClienteTO select(int id) {
		PreparedStatement preparedStatement = null;
		String query = "SELECT * FROM T_HA_CLIENTE WHERE ID_CLIENTE = ?";
		
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			
			if(result != null) {
			ClienteTO c = new ClienteTO();;
			while(result.next()) {
				c.setId(result.getInt("id_cliente"));
				c.setNome(result.getString("nm_cliente"));
				c.setEmail(result.getString("ds_email"));
				c.setData(result.getString("dt_nascimento"));
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
	
	public boolean insert(ClienteTO c) throws SQLException{
		PreparedStatement preparedStatement = null;
		String query = "select max(id_cliente) as id_cliente from t_ha_cliente";
		int id_cliente=0;
		try {
			if(c.getId()==0) {
				preparedStatement = conn.prepareStatement(query);
		    	ResultSet result = preparedStatement.executeQuery();
		    	while(result.next()) {
		    		id_cliente = result.getInt("id_cliente");
		    	}
		    	id_cliente++;
		    	c.setId(id_cliente);
		    	
		    	preparedStatement = null;
		    	query ="insert into t_ha_cliente (id_cliente, nm_cliente, dt_nascimento, ds_email) values  (?,?,to_date(?,'DD/MM/YYYY'),?)";
				preparedStatement = conn.prepareStatement(query);
				
				preparedStatement.setInt(1, c.getId());
				preparedStatement.setString(2, c.getNome());
				preparedStatement.setString(3, String.valueOf(c.getData()));
				preparedStatement.setString(4, c.getEmail());
				
				preparedStatement.executeUpdate();
			}
			
			TelefoneClienteTO t = new TelefoneClienteTO();
			t.setDdd(c.getTelefone().getDdd());
			t.setDdi(c.getTelefone().getDdi());
			t.setNrTel(c.getTelefone().getNrTel());
			t.setIdCliente(c.getId());
			
			query=null;
			preparedStatement = null;
	    	query ="insert into t_ha_tel_cliente (id_telefone, t_ha_cliente_id_cliente, nr_ddi, nr_ddd, nr_telefone) values(SQ_HA_TEL_CLIENTE.nextval, ?, ?, ?, ?)";
	    	preparedStatement = conn.prepareStatement(query);
	    	
	    	preparedStatement.setInt(1, t.getIdCliente());
	    	preparedStatement.setInt(2, t.getDdi());
	    	preparedStatement.setInt(3, t.getDdd());
	    	preparedStatement.setInt(4, t.getNrTel());
	    	
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
	
	public boolean update(ClienteTO c) throws SQLException{
		PreparedStatement preparedStatement = null;
		String query = "update t_ha_cliente set nm_cliente = ? , dt_nascimento = to_date(?,'DD/MM/YYYY') , ds_email = ? where id_cliente = ?";
		try {
			preparedStatement = conn.prepareStatement(query);
			
			preparedStatement.setString(1, c.getNome());
			preparedStatement.setString(2, c.getData());
			preparedStatement.setString(3, c.getEmail());
			preparedStatement.setInt(4, c.getId());
			
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
		try {
			ClienteTO c = new ClienteTO();
			c.setId(id);
			List<TelefoneClienteTO> listaTelefones = new ArrayList<>();
			PreparedStatement preparedStatement = null;
			String query = "select * from t_ha_tel_cliente where t_ha_cliente_id_cliente = ?";

			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()) {
				TelefoneClienteTO t = new TelefoneClienteTO();
				t.setId(result.getInt("id_telefone"));
				listaTelefones.add(t);
			}
			for(TelefoneClienteTO tel:listaTelefones) {
				preparedStatement = null;
				query = "delete from t_ha_tel_cliente where id_telefone = ?";
				preparedStatement = conn.prepareStatement(query);
				preparedStatement.setInt(1, tel.getId());
				preparedStatement.executeUpdate();
			}
			preparedStatement = null;
			query = "delete from t_ha_cliente where id_cliente = ?";
			
			preparedStatement = conn.prepareStatement(query);
			
			preparedStatement.setInt(1, c.getId());
			
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