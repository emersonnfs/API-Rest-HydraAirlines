package br.com.hydraairlines.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.hydraairlines.bo.FuncionarioBO;
import br.com.hydraairlines.to.FuncionarioTO;

@Path("/login")
public class FuncionarioResource {
	FuncionarioBO ub = new FuncionarioBO();
	
	@POST
	@Consumes( MediaType.APPLICATION_JSON )
	@Produces( MediaType.APPLICATION_JSON )
	public Response loginFuncionario( FuncionarioTO f ) {
		return Response.status( 200 ).entity(ub.validacao(f)).build();
	}
}
