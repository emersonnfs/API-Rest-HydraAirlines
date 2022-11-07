package br.com.hydraairlines.resource;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.hydraairlines.bo.TelefoneClienteBO;
import br.com.hydraairlines.to.TelefoneClienteTO;

@Path("/telclientes")
public class TelefoneClienteResource {
	TelefoneClienteBO cb = new TelefoneClienteBO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TelefoneClienteTO> buscar() throws SQLException {
		return cb.listar();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public TelefoneClienteTO buscar(@PathParam("id") int id) throws SQLException {
		return cb.listar(id);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizar(TelefoneClienteTO t, @PathParam("id") int id) throws SQLException{
		t.setId(id);
		return Response.status(200).entity(cb.atualiza(t)).build();
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response excluir(@PathParam("id") int id) throws SQLException{
		return Response.status(200).entity(cb.remover(id)).build();
	}
}