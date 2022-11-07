package br.com.hydraairlines.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.hydraairlines.factory.ConnectionFactory;
import br.com.hydraairlines.to.EmpresaTO;
import br.com.hydraairlines.to.TelefoneEmpresaTO;

public class EmpresaDAO {
	private Connection conn = null;
	
	public EmpresaDAO() {
		this.conn = new ConnectionFactory().getConnection();
	}
	
	public ArrayList<EmpresaTO> select() throws SQLException {
		ArrayList<EmpresaTO> empresas = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		String query = "SELECT * FROM T_HA_EMPRESA ORDER BY 2 ASC";
		
		try {
			preparedStatement = conn.prepareStatement(query);
			ResultSet result = preparedStatement.executeQuery();
			EmpresaTO emp = null;
			
			while(result.next()) {
				emp = new EmpresaTO();
				emp.setId(result.getInt("id_empresa"));;
				emp.setNome(result.getString("nm_empresa"));
				emp.setCnpj(result.getString("nr_cpnj"));
				preparedStatement = null;
				query = "SELECT * FROM T_HA_TEL_EMPRESA WHERE T_HA_EMPRESA_ID_EMPRESA = ?";
				preparedStatement = conn.prepareStatement(query);
				preparedStatement.setInt(1, emp.getId());
				ResultSet result2 = preparedStatement.executeQuery();
				ArrayList<TelefoneEmpresaTO> listaTel = new ArrayList<>();
				while(result2.next()) {
					TelefoneEmpresaTO t = new TelefoneEmpresaTO();
					t.setId(result2.getInt("id_telefone"));
					t.setIdEmpresa(result2.getInt("t_ha_empresa_id_empresa"));
					t.setDdi(result2.getInt("nr_ddi"));
					t.setDdd(result2.getInt("nr_ddd"));
					t.setNrTel(result2.getInt("nr_telefone"));
					listaTel.add(t);
				}
				emp.setTelefone(listaTel);
			
				empresas.add(emp);
			}
			conn.close();
			return empresas;
		}catch(SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		conn.close();
		return null;
	}
	
	public EmpresaTO select(int id) throws SQLException{
		PreparedStatement preparedStatement = null;
		String query = "SELECT * FROM T_HA_EMPRESA WHERE ID_EMPRESA = ?";
		
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			
			if(result != null) {
			EmpresaTO emp = new EmpresaTO();;
			while(result.next()) {
				emp.setId(result.getInt("id_empresa"));;
				emp.setNome(result.getString("nm_empresa"));
				emp.setCnpj(result.getString("nr_cpnj"));
				
				preparedStatement = null;
				query = "SELECT * FROM T_HA_TEL_EMPRESA WHERE T_HA_EMPRESA_ID_EMPRESA = ?";
				preparedStatement = conn.prepareStatement(query);
				preparedStatement.setInt(1, emp.getId());
				ResultSet result2 = preparedStatement.executeQuery();
				ArrayList<TelefoneEmpresaTO> listaTel = new ArrayList<>();
				while(result2.next()) {
					TelefoneEmpresaTO t = new TelefoneEmpresaTO();
					t.setId(result2.getInt("id_telefone"));
					t.setIdEmpresa(result2.getInt("t_ha_empresa_id_empresa"));
					t.setDdi(result2.getInt("nr_ddi"));
					t.setDdd(result2.getInt("nr_ddd"));
					t.setNrTel(result2.getInt("nr_telefone"));
					listaTel.add(t);
				}
				emp.setTelefone(listaTel);
			}
			conn.close();
			return emp;
			
		}
		}catch(SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
		}
		conn.close();
		return null;
	}
	
	public boolean insert(EmpresaTO emp) throws SQLException{
		PreparedStatement preparedStatement = null;
		String query = "select max(id_empresa) as id_empresa from t_ha_empresa";
		int id_empresa = 0;
		try {
			if(emp.getId()==0) {
				preparedStatement = conn.prepareStatement(query);
				ResultSet result = preparedStatement.executeQuery();
		    	while(result.next()) {
		    		id_empresa = result.getInt("id_empresa");
		    	}
		    	id_empresa++;
		    	
		    	preparedStatement = null;
		    	query ="insert into t_ha_empresa (id_empresa, nm_empresa, nr_cpnj) values  (?,?,?)";
				emp.setId(id_empresa);
				preparedStatement = conn.prepareStatement(query);
				
				preparedStatement.setInt(1, emp.getId());
				preparedStatement.setString(2, emp.getNome());
				preparedStatement.setString(3,emp.getCnpj());
				
				preparedStatement.executeUpdate();
			}
			
			for(TelefoneEmpresaTO tel: emp.getTelefone()) {
				TelefoneEmpresaTO t = new TelefoneEmpresaTO();
				t.setId(tel.getId());
				t.setIdEmpresa(emp.getId());
				t.setDdd(tel.getDdd());
				t.setDdi(tel.getDdi());
				t.setNrTel(tel.getNrTel());
				
				query=null;
				preparedStatement = null;
				query ="insert into t_ha_tel_empresa (id_telefone, t_ha_empresa_id_empresa, nr_ddi, nr_ddd, nr_telefone) values(SQ_HA_TEL_EMPRESA.nextval, ?, ?, ?, ?)";
				
				preparedStatement = conn.prepareStatement(query);
		    	
		    	preparedStatement.setInt(1, t.getIdEmpresa());
		    	preparedStatement.setInt(2, t.getDdi());
		    	preparedStatement.setInt(3, t.getDdd());
		    	preparedStatement.setInt(4, t.getNrTel());
		    	
		    	preparedStatement.executeUpdate();
			}
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
	
	public boolean uptdate(EmpresaTO emp) throws SQLException{
		PreparedStatement preparedStatement = null;
		String query = "update t_ha_empresa set nm_empresa = ? , nr_cpnj = ? where id_empresa = ?";
		try {
			preparedStatement = conn.prepareStatement(query);
			
			preparedStatement.setString(1, emp.getNome());
			preparedStatement.setString(2, emp.getCnpj());
			preparedStatement.setInt(3, emp.getId());
			
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
			EmpresaTO emp = new EmpresaTO();
			emp.setId(id);
			List<TelefoneEmpresaTO> listaTelefones = new ArrayList<>();
			PreparedStatement preparedStatement = null;
			String query = "select * from t_ha_tel_empresa where t_ha_empresa_id_empresa = ?";

			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()) {
				TelefoneEmpresaTO t= new TelefoneEmpresaTO();
				t.setId(result.getInt("id_telefone"));
				listaTelefones.add(t);
			}
			
			for(TelefoneEmpresaTO tel:listaTelefones) {
				preparedStatement = null;
				query = "delete from t_ha_tel_empresa where id_telefone = ?";
				preparedStatement = conn.prepareStatement(query);
				preparedStatement.setInt(1, tel.getId());
				preparedStatement.executeUpdate();
			}
			preparedStatement = null;
			query = "delete from t_ha_empresa where id_empresa = ?";
			
			preparedStatement = conn.prepareStatement(query);
			
			preparedStatement.setInt(1, emp.getId());
			
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