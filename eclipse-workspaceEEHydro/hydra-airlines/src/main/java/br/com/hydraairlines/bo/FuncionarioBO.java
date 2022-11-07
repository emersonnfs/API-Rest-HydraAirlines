package br.com.hydraairlines.bo;

import br.com.hydraairlines.dao.FuncionarioDAO;
import br.com.hydraairlines.to.FuncionarioTO;

public class FuncionarioBO {
	FuncionarioDAO ld = null;
	
	public FuncionarioTO validacao(FuncionarioTO l) {
		ld = new FuncionarioDAO();
		return ld.loginFuncionarioDAO(l);
	}
}
