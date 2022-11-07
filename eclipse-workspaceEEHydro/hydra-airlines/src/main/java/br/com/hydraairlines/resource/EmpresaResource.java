package br.com.hydraairlines.resource;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.hydraairlines.bo.EmpresaBO;
import br.com.hydraairlines.to.EmpresaTO;

@Path("/empresa")
public class EmpresaResource {
	EmpresaBO cb = new EmpresaBO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<EmpresaTO> buscar() throws SQLException {
		return cb.listar();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public EmpresaTO buscar(@PathParam("id") int id) throws SQLException {
		return cb.listar(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrar(EmpresaTO emp) throws SQLException{
		return Response.status( 200 ).entity(cb.cadastrar(emp)).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizar(EmpresaTO emp, @PathParam("id") int id) throws SQLException{
		emp.setId(id);
		return Response.ok().status(200).entity(cb.atualiza(emp)).build();
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response excluir(@PathParam("id") int id) throws SQLException{
		return Response.status(200).entity(cb.remover(id)).build();
	}
}
