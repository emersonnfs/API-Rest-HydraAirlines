package br.com.hydraairlines.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.hydraairlines.factory.ConnectionFactory;
import br.com.hydraairlines.to.FuncionarioTO;

public class FuncionarioDAO {
	private Connection conn = null;
	
	public FuncionarioDAO() {
		this.conn = new ConnectionFactory().getConnection();
	}
	
	public FuncionarioTO loginFuncionarioDAO(FuncionarioTO l) {
		PreparedStatement preparedStatement = null;
		String query = "SELECT * FROM t_ha_login_funcionario WHERE NM_USUARIO = ? AND DS_SENHA = ?";
		
		try {
			preparedStatement = conn.prepareStatement(query);
			
			preparedStatement.setString(1, l.getUsuario());
			preparedStatement.setString(2, l.getSenha());
			
			ResultSet result = preparedStatement.executeQuery();
			FuncionarioTO lto = null;
			
			while(result.next() ) {
				lto = new FuncionarioTO();
				lto.setIdFuncionario(result.getInt(1));
				lto.setUsuario( result.getString(2) );
				lto.setSenha( result.getString(3) );
			}
			
			if( lto != null ) {
				result.close();
				preparedStatement.close();
				conn.close();
			}
				
				
			System.out.println("O usuário " + lto.getUsuario() + " Logou");
			return lto;
		
		} catch(SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		System.out.println("O usuário " + l.getUsuario() + " Não Logou");
		return null;
	}
}